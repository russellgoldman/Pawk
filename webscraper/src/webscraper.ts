const $ = require('cheerio');
const rp = require('request-promise')

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
    private visitedCourses: Array<string> = [];

    async getAreasOfStudyTable(): Promise<any> {
        const options: Object = {
            uri: this.academicCalendarRoot,
            transform: function (body) {
                return $.load(body);
            }
        }

        return rp(options)
            .catch((err) => {
                // if there is an issue with the rp fetch, reject it now
                return Promise.reject(new Error(`Request to academic calendar root url failed`));
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

    async requestAreaOfStudy(areaOfStudy: string) {
        const root = this;

        const options: Object = {
            uri: areaOfStudy,
            transform: function (body) {
                return $.load(body);
            }
        }

        rp(options)
            .catch((err) => {
                // if there is an issue with the rp fetch, reject it now
                return Promise.reject(new Error(`Request to area of study page failed`));
            })
            .then(($) => {
                let foundCourses = false;
                let foundDepartments = false;

                $('caption').each(function(index, el) {
                    if ($(this).text() === 'Course Offerings') {
                        $(this).next().find('a').each(function(index, el) {
                            if ($(this).attr('href').startsWith('course')) {
                                let courseUrl = 'https://academic-calendar.wlu.ca/' + $(this).attr('href');
                                console.log(courseUrl);

                                if (!root.visitedCourses.includes(courseUrl)) {
                                    if (!foundCourses) foundCourses = true;
                                    root.visitedCourses.push(courseUrl);
                                    // scrape the course here
                                    root.scrapeCourse(courseUrl);
                                }
                            }
                        })
                    }
                })

                if (!foundCourses) console.log('\nNo courses found\n');

                // see if there are Departments listed
                if ($('#facultytable').children().first().text() === 'Departments') {
                    $('#facultytable').children().first().next().next().find('a').each(function(index, el) {
                        if ($(this).attr('href').startsWith('department')) {
                            let departmentUrl = 'https://academic-calendar.wlu.ca/' + $(this).attr('href');
                            console.log(departmentUrl);

                            if (!root.visitedDepartments.includes(departmentUrl)) {
                                if (!foundDepartments) foundDepartments = true;
                                root.visitedDepartments.push(departmentUrl);
                                // call the same function recursively, base case is when no departments AND no courses are present
                                // root.requestAreaOfStudy(departmentUrl)
                            }
                        }
                    })
                }

                if (!foundDepartments) console.log('\nNo departments found\n');
                if (!foundCourses && !foundDepartments) Promise.resolve();  // search the next Area of Study
            })
    }

    async scrapeCourse(courseUrl: string) {
        // scrape all possible values and mutate to GraphQL
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

        root.areasOfStudy.forEach(async function(areaOfStudy: string, index: number) {
            if (index == 5) {
                await root.requestAreaOfStudy(areaOfStudy)
            }
            return;
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