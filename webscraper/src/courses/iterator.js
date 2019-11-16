const rp = require('request-promise');
const $ = require('cheerio');
const url = 'https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79';
var links;
const help = []

rp(url)
  .then(function(html){
    //success!
    const wikiUrls = [];
    var length = $('tbody td > a',"#postermain", html).length;
    console.log(length);
    for (let i = 0; i < length; i++) {
        links=$('tbody td > a',"#postermain", html)[i].attribs.href;
            if(links.charAt(0)=="/" && links.substring(0,2)!="/g"){
                wikiUrls.push($('tbody td > a',"#postermain", html)[i].attribs.href);
            }
    }
  help =  runner(wikiUrls);
  console.log(help.values());   

  })
  .catch(function(err){
    //handle error
  });
function runner(items){
   var len = items.length;
    var select;
    const courses = [];
    for (let i = 0; i < len; i++) {

        newurl = "https://academic-calendar.wlu.ca" + items[i];
    
        rp(newurl)
        .then(function(html){
            select = $('caption', html).text();
            if(select =="Course Offerings"){
                len = $('td>a',".zebra",html).length;
                for(let i = 0; i < len;i++){
                   // select1 = $('td>a',".zebra",html)[i].attribs.href;
                    select2 =$('td>a',html)[i].attribs.href;
                   // console.log
                    if(!courses.includes("https://academic-calendar.wlu.ca/" + select2) && select2.charAt(0)=="c"){
                        //console.log("https://academic-calendar.wlu.ca/" + select2)
                        courses.push("https://academic-calendar.wlu.ca/" + select2);
                    }
                }
      
              
               
            }

        }).catch(function(err){

        });
    }
    console.log(courses.length);
    }