const cheerio = require('cheerio');
const rp = require('request-promise')
const $ = cheerio.load('<h2 class="title">Hello world</h2>')

export default class Webscraper {
    // url roots

    private academicCalendarRoot: string;
    private courseOfferingRoot: string

    constructor(calendarUrl: string, offeringRoot: string) {
        this.academicCalendarRoot = calendarUrl;
        this.courseOfferingRoot = offeringRoot;
    }
    
    private visitedStudies: Array<string> = [];
    private visitedDepartments: Array<string> = [];

    courseScrape() {
        const options: Object = {
            uri: this.academicCalendarRoot,
            transform: function (body) {
                return $.load(body);
            }
        }

        // outer context
        const outer = this;

        rp(options)
            .then(($) => {
                $('h1').each(function (index, el1) {
                    if ($(this).text() == 'Areas of Study/Courses @ Laurier (Direct Links)') {
                        const areasOfStudyTable = $(this).next().next();
                        areasOfStudyTable.find('a').each(function (i, e) {
                            const areaOfStudy = $(this);
                            if (areaOfStudy.attr('href').startsWith('https://academic-calendar.wlu.ca')) {
                                console.log(areaOfStudy.text());
                                console.log(areaOfStudy.attr('href'));

                            /* 
                            - Using this URL (href), fetch the resulting page and check if 'Course Offerings' is listed ...
                                If ( 'Course Offerings' is listed ), then the Area of Study is valid ...

                                -> Check if the Area of Study name is stored in visitedStudies
                                    -> If it hasn't been visited / stored, scrape the courses listed AND append the area of study name
                                        found at the top of the page in red
                            
                                Else ...

                                -> See if 'Departments' is listed
                                    -> If so, iterate through all Departments and observe whether they have been visited / stored in visitedDepartments
                                    -> If a department hasn't been visited / stored, fetch the resulting page and check if 'Course Offerings' is listed
                                        -> If it is listed, complete the steps above for 'If 'Course Offerings' is listed'
                                        -> If not, go back to 'See if 'Departments' is listed'
                                -> If 'Departments' is NOT listed, iterate to the NEXT area of study on the root url
                            */
                            }
                            
                        });

                        console.log(outer.visitedStudies);
                    }
                });
            })
            .catch((err) => {
                console.log(err);
            });

    }

    programScrape() {
        // use the academic calendar root
        console.log('program scrape');
    }

    courseOfferingScrape() {
        // use the course offering root
        console.log('course offering scrape');
    }
}