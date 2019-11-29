const rp = require('request-promise');
const $ = require('cheerio');
const mutator = require('./mutator');

//const url = 'https://academic-calendar.wlu.ca/course.php?c=51919&cal=1&d=2022&s=931&y=79';
const url = 'https://academic-calendar.wlu.ca/course.php?c=53062&cal=1&d=2070&s=932&y=79'
//const url = 'https://academic-calendar.wlu.ca/course.php?c=53069&cal=1&d=2070&s=932&y=79';
//const url = 'https://academic-calendar.wlu.ca/course.php?c=53500&cal=1&d=2107&s=934&y=79';
//const url = "https://academic-calendar.wlu.ca/program.php?cal=1&d=2068&p=4475&s=932&y=79";
var code;
var name ;
var realcode;
var nametemp;
var nametemp2
var realname;
var creditstemp;
var creditstemp2;
var realcredits;
var initialhours
var lecturehours;
var labhours;
var description;
var reqs;
var prerequisiteDescription = null
var prerequisiteCourses = null
var corequisiteDescription = null
var corequisiteCourses = null
var exclusionsDescription = null
var exclusionCourses = null
var notes = null


const courseParse = function(url) {
  try {
    var options = {
      method: 'GET',
      url: url,
      json: true,
      headers: {
          'Connection': 'keep-alive',
          'Accept-Encoding': '',
          'Accept-Language': 'en-US,en;q=0.8'
      }
    };

    console.log(url)
    rp(options)
      .then(function(html) {
        console.log("hey")
        code = $('h1',"#postercontent",html).html().split("<br>");
        realcode = code[0];
      // console.log(realcode)
        nametemp = code[1].split('<span style="font-weight:normal;">');
        nametemp2 = nametemp[1].split("</span");
        realname = nametemp2[0];
        //console.log(realname)
        creditstemp = code[2].split('<span style="font-weight:normal; color:#000000;">');
        creditstemp2 = creditstemp[1].split("</span");
        realcredits = creditstemp2[0].substring(0,3);
    // console.log(realcredits);
        initialhours = $("li",".hours",html).text();
        if(initialhours.substring(0,3)==("Lec")){
          lecturehours = initialhours.substring(20,21);
          if(initialhours.substring(21,24)=="Lab"){
            labhours = initialhours.substring(26,30);
          }else{
            labhours = null;
          }

        } else if(initialhours.substring(0,3)==("Lab")){
          lecturehours = null;
          labhours = lecturehours.split(" ")[2]
        } else{
          lecturehours = null;
          labhours = null;
        }
      // console.log(lecturehours);
      // console.log(labhours);
        description = $('p',"#postercontent",html).text();
      // console.log(description);
        reqs = $('dt',".reqs",html).text().split(/(?=[A-Z])/);
        //console.log(reqs);
        length = $('dt', '.reqs', html).children().length
        //console.log(length);
        header = $('dt', '.reqs', html).first()

        for (i = 0; i < length * 2; i++) {
          headerCourses = header.next()

          switch (header.text()) {
            case "Prerequisites":
              prerequisiteDescription = headerCourses.text()
              prerequisiteCourses = []
              headerCourses.children('a').each(function(i, elem) {
                prerequisiteCourses.push($(this).text());
              })
              
              //console.log(prerequisiteDescription)
            //  console.log(prerequisiteCourses)
              break
            case "Co-requisites":
              corequisiteDescription = headerCourses.text()
              corequisiteCourses = []
              headerCourses.children('a').each(function(i, elem) {
                corequisiteCourses.push($(this).text());
              })
              
            // console.log(corequisiteDescription)
            // console.log(corequisiteCourses)
              break
            case "Exclusions":
              exclusionsDescription = headerCourses.text()
              exclusionCourses = []
              headerCourses.children('a').each(function(i, elem) {
                exclusionCourses.push($(this).text());
              })

              //console.log(exclusionsDescription)
              //console.log(exclusionCourses)
              break
            case "Notes":
              notes = headerCourses.text()

            //  console.log(notes)
              break
          }
          
          header = headerCourses.next()
        }
        description += notes;

        course = {
          code: realcode,
          name: realname,
          credits: parseFloat(realcredits),
          lecture_hours: parseFloat(lecturehours),
          lab_hours:parseFloat(labhours),
          description,
          prereq_desc: prerequisiteDescription,
          prereq_courses: prerequisiteCourses,
          coreq_desc: corequisiteDescription,
          coreq_courses: corequisiteCourses,
          exc_desc: exclusionsDescription,
          exc_courses: exclusionCourses
        };

        mutator.createCourse(course)
      //  console.log(description);
      })
      .catch(function(err) {
        //handle error
      });
  } catch (err) {
    //console.log(err)
  }
}

module.exports = courseParse;
