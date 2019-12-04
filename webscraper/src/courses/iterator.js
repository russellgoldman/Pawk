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

    // predefined callback function
    const afterCourseLinksRetrived = (courses) => {
        console.log("Course Links successly retrieved")
        console.log(courses.length);

        Promise.mapSeries(courses, async function(course, index, length) {
            await sleep(500)
            courseParse(course)
        }).then(function(result) {
            console.log("Done all mutations");
        })

        function sleep(delay) {
            return new Promise(resolve => {
                setTimeout(resolve, delay)
            })
        }
    }

    getCourseLinks(wikiUrls, afterCourseLinksRetrived)
})

function getCourseLinks(items, callback) {
    var itemsLength = items.length;
    var select;
    var courses = []
    var requests = 0;    

    for (var i = 0; i < itemsLength; i++) {
        newurl = items[i];
     
        rp(newurl)
        .then(function(html) {
            requests++

            department = $('div[style="padding: 0px 20px;"]', html).children().first().text();
            console.log(department);
            select = $('caption', html).text();
          
            if (select =="Course Offerings") {
                len = $('td>a',".zebra",html).length;
                for (let i = 0; i < len; i++) {
                    select2 = $('td>a',html)[i].attribs.href;

                    if (!courses.includes("https://academic-calendar.wlu.ca/" + select2) && select2.charAt(0)=="c"){
                        courses.push("https://academic-calendar.wlu.ca/" + select2);
                    }
                }         
            }
            // once all the requests are done, initiate the callback function
            if (requests === 122) {
                callback(courses)
            }
        }).catch(function(err) {
            console.log(err)
        });
    }
}