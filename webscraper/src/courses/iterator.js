const rp = require('request-promise');
const $ = require('cheerio');
const courseParse = require('./justCourses.js');
const url = 'https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79';
var links;
const help = []

rp(url)
.then(function(html) {
    //success!
    var wikiUrls = [];
    var length = $('tbody td > a',"#postermain", html).length;
    //console.log(length);
    for (let i = 0; i < length; i++) {
        links = $('tbody td > a',"#postermain", html)[i].attribs.href;
        if (links.charAt(0)=="/" && links.substring(0,2)!="/g") {
            wikiUrls.push($('tbody td > a',"#postermain", html)[i].attribs.href);
        }
    }

    // predefined callback function
    const afterCourseLinksRetrived = (courses) => {
        console.log("Course Links successly retrieved")
        console.log(courses.length);

        courses.forEach((el, index) => {
            //console.log(el);
            courseParse(el);
        })
    }

    getCourseLinks(wikiUrls, afterCourseLinksRetrived)
})

function getCourseLinks(items, callback) {
    var itemsLength = items.length;
    var select;
    var courses = []
    var requests = 0;

    for (var i = 0; i < itemsLength; i++) {
        newurl = "https://academic-calendar.wlu.ca" + items[i];
    
        rp(newurl)
        .then(function(html) {
            requests++
            
            select = $('caption', html).text();
            if (select =="Course Offerings") {
                len = $('td>a',".zebra",html).length;
                for(let i = 0; i < len; i++){
                   // select1 = $('td>a',".zebra",html)[i].attribs.href;
                    select2 =$('td>a',html)[i].attribs.href;
                    if (!courses.includes("https://academic-calendar.wlu.ca/" + select2) && select2.charAt(0)=="c"){
                        //console.log("https://academic-calendar.wlu.ca/" + select2)
                        courses.push("https://academic-calendar.wlu.ca/" + select2);
                    }
                }         
            }
            
            // once all the requests are done, initiate the callback function
            if (requests === itemsLength-1) {
                console.log(requests);
                console.log(courses.length)
                callback(courses)
            }
        }).catch(function(err) {

        });
    }
}