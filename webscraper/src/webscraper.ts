const $ = require('cheerio');
const rp = require('request-promise');
const delay = require('delay');
const bluebird = require('bluebird');

import { Course } from './models'
export default class Webscraper {
    // url roots
    private academicCalendarRoot: string;
    private courseOfferingRoot: string

    constructor(calendarUrl: string, offeringRoot: string) {
        this.academicCalendarRoot = calendarUrl;
        this.courseOfferingRoot = offeringRoot;
    }
    
    // strings are the urls
    private areasOfStudy: Array<string> = [];
    private visitedStudies: Array<string> = [];
    private visitedDepartments: Array<string> = [];
    private courses: Array<string> = [];

    async getAreasOfStudyTable(): Promise<any> {
        const root = this;

        const options: Object = {
            uri: this.academicCalendarRoot,
            transform: function (body) {
                return $.load(body);
            }
        }

        return rp(options)
            .catch((err) => {
                // if there is an issue with the rp fetch, reject it now
                return Promise.reject(new Error(`Request to academic calendar root ${root.academicCalendarRoot} failed`));
            })
            .then(($) => {
                // otherwise parse the result
                let areasOfStudyTable;

                $('h1').each(function (index, el) {
                    if ($(this).text() == 'Areas of Study/Courses @ Laurier (Direct Links)') {
                        areasOfStudyTable = $(this).next().next();                        
                    }
                });

                if (areasOfStudyTable !== null || typeof areasOfStudyTable !== 'undefined') {
                    return Promise.resolve(areasOfStudyTable);
                } else {
                    return Promise.reject(new Error(`Area of Study Table not found`));
                }
            })
    }

    async requestAreaOfStudy(areaOfStudy: string, index: number, subindex: number): Promise<any> {
        const root = this;

        const options: Object = {
            uri: areaOfStudy,
            transform: function (body) {
                return $.load(body);
            }
        }

        if (subindex === -1) {
            console.log(`Area of Study ${index}`)
        } else {
            console.log(`Area of Study ${index}.${subindex}`)
        }
        console.log(`total courses: ${root.courses.length}`)

        return rp(options)
            .catch((err) => {
                // if there is an issue with the rp fetch, reject it now
                return Promise.reject(new Error(`Request to area of study ${areaOfStudy} failed`));
            })
            .then(async ($) => {
                let localDepartments: Array<string> = [];

                $('caption').each(function(index, el) {
                    if ($(this).text() === 'Course Offerings') {
                        $(this).next().find('a').each(async function(index, el) {
                            if ($(this).attr('href').startsWith('course')) {
                                let courseUrl = 'https://academic-calendar.wlu.ca/' + $(this).attr('href');
                                //console.log(courseUrl);

                                if (!root.courses.includes(courseUrl)) {
                                    root.courses.push(courseUrl);
                                }
                            }
                        })
                    }
                })

                // see if there are Departments listed
                if ($('#facultytable').children().first().text() === 'Departments') {
                    $('#facultytable').children().first().next().next().find('a').each(function(index, el) {
                        if ($(this).attr('href').startsWith('department')) {
                            let departmentUrl = 'https://academic-calendar.wlu.ca/' + $(this).attr('href');
                            // console.log(departmentUrl);

                            if (!root.visitedDepartments.includes(departmentUrl)) {
                                localDepartments.push(departmentUrl);
                                root.visitedDepartments.push(departmentUrl);
                            }
                        }
                    })
                }

                if (localDepartments.length !== 0) {
                    await bluebird.mapSeries(localDepartments, async function(departmentUrl, i) {
                        await delay(1000).then(async function() {
                            await root.requestAreaOfStudy(departmentUrl, index, i + 1)
                        });
                    });
                } else {
                    return Promise.resolve()
                }
            })
    }

    async scrapeCourse(courseUrl: string): Promise<any> {
        const root = this;

        let code: string | null = null;
        let name: string | null = null;
        let credits: number | null = null;
        let lectureHours: number | null = null;
        let labHours: number | null = null;
        let tutorialHours: number | null = null;
        let description: string | null = null;
        let prerequisiteDescription: string | null = null;
        let prerequisiteCourses: Array<string> | null = null;
        let corequisiteDescription: string | null = null;
        let corerequsiteCourses: Array<string> | null = null;
        let exclusionsDescription: string | null = null;
        let exclusionsCourses: Array<string> | null = null;
        let notesDescriptions: string | null = null;

        const options: Object = {
            uri: courseUrl,
            transform: function (body) {
                return $.load(body);
            }
        }

        return rp(options)
            .catch((err) => {
                // if there is an issue with the rp fetch, reject it now
                return Promise.reject(new Error(`Request to course page ${courseUrl} failed`));
            })
            .then(function($) {
                let headerInfo = $('div[style="padding: 0px 20px;"]').children().first();
                let reg = /.+?(?=<br>)/;
                let arr = reg.exec(headerInfo.html());

                // Code
                if (arr !== null && arr !== undefined) {
                    code = reg.exec(headerInfo.html())[0] as string;    // e.g. LL201<br>..., LL201 is caught
                }

                // Name and Credits
                headerInfo.children().each(function(index, el) {
                    // span contains our name and credits
                    if ($(this).is('span')) {
                        if (index === 1) name = $(this).text();
                        if (index === 3) credits = +$(this).text().split(' ')[0];    // i.e. 0.5 Credit is split into ['0.5', 'Credit']
                    }
                });

                // Lecture Hours, Lab Hours, and Tutorial Hours
                $('div .hours').children().last().children().each(function(index, el) {
                    let data = $(this).text().split(' ')
                    switch (data[0]) {
                        case 'Lecture/Discussion:':
                            lectureHours = data[1];
                            break;
                        case 'Lab:':
                            labHours = data[1];
                            break;
                        case 'Tutorial/Seminar:':
                            tutorialHours = data[1];
                            break;
                    }
                });

                // Description
                let parent = $('div[style="padding: 0px 20px;"]').children();
                let cursor = $('div[style="padding: 0px 20px;"]').children().first();
                
                //checks if cursor element is a p tag and has text greater than 0
                while (!cursor.is('p') || cursor.text().length === 0) {
                    if (cursor.html() === parent.last().html()) {
                        // no more siblings to search, no description
                        break;
                    }
                    cursor = cursor.next();
                }

                // if no description found, try special case with span as description (e.g. AR252)
                if (cursor.html() === parent.last().html()) {
                    description = $('span[style="font-size: 11.1999998092651px;"]').text();
                } else {
                    description = cursor.text();
                }

                // Requisites
                $('div .heading').each(function(index, el) {
                    if ($(this).text() === 'Additional Course Information') {
                        $(this).next().children().each(function(index, el) {
                            if (index % 2 === 0) {
                                let requisiteData = $(this).next();
                                //console.log(requisiteData.html())
                                let crossListCheck: string | null = requisiteData.text();
                                let crossListings: Array<string> = [];

                                let start: number = 0;
                                let end: number = -1;
                                let crossList: boolean = false;
                                for (let i: number = 0; i < crossListCheck.length; i++) {
                                    if (start === -1 && crossListCheck[i] == ' ') {
                                        // begin
                                        start = i + 1;  // the next character is the start of a cross-listing
                                        continue;
                                    } 
                                    if (start !== 1 &&  
                                        (   
                                            crossListCheck[i] === ' '
                                            || 
                                            crossListCheck[i] === ',' 
                                            ||
                                            crossListCheck[i] === ')'
                                            ||
                                            crossListCheck[i] === ')'
                                        )
                                    ) {
                                        end = i;

                                        if (crossList) crossListings.push(crossListCheck.slice(start, end));
                                        start = -1;
                                        end = -1;
                                        crossList = false;
                                    } else if (start !== 1 && i === crossListCheck.length - 1) {
                                        end = crossListCheck.length;

                                        if (crossList) crossListings.push(crossListCheck.slice(start, crossListCheck.length));
                                        start = -1;
                                        end = -1;
                                        crossList = false;
                                    } else if (crossListCheck[i] === '/') {
                                        crossList = true;
                                    }
                                }

                                let temp: string = crossListings.join(' ');
                                let tempCrossList: string = '';
                                let requisiteCourses: Array<string> = [];
                                requisiteData.find('a').each(function(index, el) {
                                    if (!temp.includes($(this).text())) {
                                        requisiteCourses.push($(this).text());
                                    } else {
                                        if (tempCrossList === '') {
                                            tempCrossList = $(this).text();
                                        } else {
                                            tempCrossList += `/${$(this).text()}`;
                                            requisiteCourses.push(tempCrossList);
                                        }
                                    }
                                });

                                switch ($(this).text()) {
                                    case 'Prerequisites':
                                        prerequisiteDescription = requisiteData.text();
                                        prerequisiteCourses = requisiteCourses;
                                        break;
                                    case 'Corequisites':
                                        corequisiteDescription = requisiteData.text();
                                        corerequsiteCourses = requisiteCourses;
                                        break;
                                    case 'Exclusions':
                                        exclusionsDescription = requisiteData.text();
                                        exclusionsCourses = requisiteCourses;
                                        break;
                                    case 'Notes':
                                        notesDescriptions = requisiteData.text();
                                        break;
                                }
                            }
                        });
                    }
                });

                return Promise.resolve({
                    code,
                    name,
                    credits,
                    lectureHours,
                    labHours,
                    tutorialHours,
                    description,
                    prerequisiteDescription,
                    prerequisiteCourses,
                    corequisiteDescription,
                    corerequsiteCourses,
                    exclusionsDescription,
                    exclusionsCourses,
                    notesDescriptions
                });
            })
    }

    async courseScrapeMain() {
        const root = this;

        const areasOfStudyTable = await this.getAreasOfStudyTable()
            .then(function(val) {
                return val;
            })
            .catch((err) => {
                // by not returning anything, the value of areasOfStudyTable is set to undefined
                console.log(err);
            });

        if (areasOfStudyTable === null || typeof areasOfStudyTable === 'undefined') {
            return
        }

        areasOfStudyTable.find('a').each(function (index, el) {
            const areaOfStudy = $(this);
            if (
                areaOfStudy.attr('href').startsWith('https://academic-calendar.wlu.ca')
                &&
                !areaOfStudy.attr('href').includes('glossary')
                &&
                !areaOfStudy.attr('href').includes('program')
                &&
                !root.areasOfStudy.includes(areaOfStudy.attr('href'))
            ) {
                root.areasOfStudy.push(areaOfStudy.attr('href'));
            }
        });

        await bluebird.mapSeries(root.areasOfStudy, async function(areaOfStudyUrl: string, index: number) {
            await delay(2000).then(async() => {
                await root.requestAreaOfStudy(areaOfStudyUrl, index, -1)
            })
        });

        await bluebird.mapSeries(root.courses, async function(courseUrl, index, arrayLength) {
            // rate limiter before scrape
            await delay(1000).then(async() => {
                console.log(`Course ${index + 1}`)
                await root.scrapeCourse(courseUrl)
                    .then((courseData) => {
                        console.log(courseData);
                    });
            })

        });

        /* 
        - Using this URL (href), fetch the resulting page and check if 'Course Offerings' is listed ...
            If ( 'Course Offerings' is listed ), then the Area of Study is valid ...

            -> Check if the Area of Study name is stored in visitedStudies
                -> If it hasn't been visited / stored, scrape the courses listed AND append the area of study name
                   found at the top of the page in red
                -> If the page ALSO has 'Departments' listed, then there may be more courses to scrape on the subsequent page,
                   complete the steps for 'See if Departments is listed' below
                   e.g. https://academic-calendar.wlu.ca/department.php?cal=1&d=2038&s=931&y=79
        
            Else ...

            -> See if 'Departments' is listed
                -> If so, iterate through all Departments and observe whether they have been visited / stored in visitedDepartments
                -> If a department hasn't been visited / stored, fetch the resulting page and check if 'Course Offerings' is listed
                    -> If it is listed, complete the steps above for 'If 'Course Offerings' is listed'
                    -> If not, go back to 'See if 'Departments' is listed'
            -> If 'Departments' is NOT listed, iterate to the NEXT area of study on the root url
        */
        
    }

    programScrapeMain() {
        // use the academic calendar root
        console.log('program scrape');
    }

    courseOfferingScrapeMain() {
        // use the course offering root
        console.log('course offering scrape');
    }
}