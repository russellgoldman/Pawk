const rp = require('request-promise');
const $ = require('cheerio');
//const url = 'https://academic-calendar.wlu.ca/course.php?c=51919&cal=1&d=2022&s=931&y=79';
//const url = 'https://academic-calendar.wlu.ca/course.php?c=53062&cal=1&d=2070&s=932&y=79'
//const url = 'https://academic-calendar.wlu.ca/course.php?c=53069&cal=1&d=2070&s=932&y=79';
const url = 'https://academic-calendar.wlu.ca/course.php?c=53500&cal=1&d=2107&s=934&y=79';
var code;
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
var courses;
var preCourses;
var coCourses;
var exCourses;


rp(url)
  .then(function(html) {
    code = $('h1',"#postercontent",html).html().split("<br>");
    realcode = code[0];
    console.log(realcode)
    nametemp = code[1].split('<span style="font-weight:normal;">');
    nametemp2 = nametemp[1].split("</span");
    realname = nametemp2[0];
    console.log(realname)
    creditstemp = code[2].split('<span style="font-weight:normal; color:#000000;">');
    creditstemp2 = creditstemp[1].split("</span");
    realcredits = creditstemp2[0].substring(0,3);
    console.log(realcredits);
    initialhours = $("li",".hours",html).text();
    if(initialhours.substring(0,3)==("Lec")){
      lecturehours = initialhours.substring(20,21);
      if(initialhours.substring(21,24)=="Lab"){
        labhours = initialhours.substring(26,30);
      }else{
        labhours = null;
      }

    }else if(initialhours.substring(0,3)==("Lab")){
      lecturehours = null;
      labhours = lecturehours.split(" ")[2]
    }else{
      lecturehours = null;
      labhours = null;
    }
    console.log(lecturehours);
    console.log(labhours);
    description = $('p',"#postercontent",html).text();
    console.log(description);
    reqs = $('dt',".reqs",html).text().split(/(?=[A-Z])/);
    console.log(reqs);
// part of the old way
 // courses = $('dd',".reqs",html).text().split(".");
  //courses = $('.reqs .required',html);

   console.log(courses);

$('.reqs .required',html).children().each(
  function(index, element) {
    $(element).each(function(i,elm){
      console.log( $(elm).text());
    });
  if (element == 'a'){
  
  } 
})
   /* Old Setup that wasent ideal
    if (reqs.length == 3){
        preCourses = courses[0];
        coCourses = courses[1];
        exCourses = courses[2];     
    }else if (reqs.length == 2){
      if(reqs[0].substring(0,2)=="Pr"){
        preCourses = courses[0]
        if(reqs[1].substring(0,2)=="Co"){
          coCourses = courses[1];
          exCourses = null;
        }else{
          coCourses = null;
          exCourses = courses[1];
        }
      }else if(reqs[1].substring(0,2)=="Co"){
        coCourses = courses[0]
        preCourses = null;
        exCourses = courses[1];
      }
    }else if (reqs.length ==1){
      if(reqs[0].substring(0,2)=="Pr"){
        preCourses = courses[0];
        coCourses = null;
        exCourses = null;
      }else if(reqs[0].substring(0,2)=="Co"){
        coCourses = courses[0];
        preCourses = null;
        exCourses = null;
      }else{
        exCourses = courses[0];
        coCourses = null;
        preCourses = null;
      }
    }else{
      exCourses = null;
      coCourses = null;
      preCourses = null;
    }
    
    console.log(preCourses);
    console.log(coCourses);
    console.log(exCourses);
    */

  })
  .catch(function(err) {
    //handle error
  });