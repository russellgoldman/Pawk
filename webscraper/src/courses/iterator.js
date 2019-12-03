const rp = require('request-promise');
const $ = require('cheerio');
const courseParse = require('./justCourses.js');
const Promise = require('bluebird');

const url = 'https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79';
var links;
const help = [];

rp(url)
.then(function(html) {
    //success!
    var wikiUrls = [];
    var data = {
        courses: [],
        departmentsFounds: 0
    };
    var length = $('tbody td > a',"#postermain", html).length;

    for (let i = 0; i < length; i++) {
        links = $('tbody td > a',"#postermain", html)[i].attribs.href;
     
        if (links.charAt(0)=="h" && links.substring(0,2)!="/g") {
            course = $('tbody td > a',"#postermain", html)[i].attribs.href;
            if (!course.startsWith("http://info.wlu.ca")) {
                wikiUrls.push(course);
            }
        }
    }

    Promise.mapSeries(wikiUrls, async function(url, index, length) {
        getCourseLink(url, data)
        await sleep(200)
    }).then(async function(result) {
        await sleep(1000)
        console.log("\nGot all course links");
        afterCourseLinksRetrived(data["courses"])
    })

    // predefined callback function
    const afterCourseLinksRetrived = (courses) => {
        console.log("Course Links successly retrieved")
        console.log(courses.length + "\n");

        Promise.mapSeries(courses, async function(course, index, length) {
            await sleep(500)
            courseParse(course)
        }).then(async function(result) {
            await sleep(1000)
            console.log("Done all mutations");
        })
    }
})

function getCourseLink(url, data) {
    var select;
    var first = true

    rp(url)
    .then(function(html) {
        select = $('caption', html).text();
        
        if (select == "Course Offerings") {
            len = $('td>a',".zebra",html).length;

            for (let i = 0; i < len; i++){
                // select1 = $('td>a',".zebra",html)[i].attribs.href;
                select2 =$('td>a',html)[i].attribs.href;
                //console.log(select2);
                if (
                    !data["courses"].includes("https://academic-calendar.wlu.ca/" + select2)
                    && 
                    select2.charAt(0)=="c"
                ) {
                    if (first) {
                        console.log(`\nDepartment ${data["departmentsFounds"] + 1}`)
                        data["departmentsFounds"] += 1
                        first = false
                    }

                    courseLink = "https://academic-calendar.wlu.ca/" + select2
                    data["courses"].push(courseLink);
                    console.log(courseLink)
                }
            }         
        }
    }).catch(function(err) {
        console.log(err)
    });
}

function sleep(delay) {
    return new Promise(resolve => {
        setTimeout(resolve, delay)
    })
}