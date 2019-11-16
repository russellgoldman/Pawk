const rp = require('request-promise');
const $ = require('cheerio');
const url = 'https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79';
var links;


rp(url)
  .then(function(html){
    //success!
    var wikiUrls = [];
    var length = $('tbody td > a',"#postermain", html).length;
    console.log(length);
    for (let i = 0; i < length; i++) {
        links=$('tbody td > a',"#postermain", html)[i].attribs.href;
            if(links.charAt(0)=="/" && links.substring(0,2)!="/g"){
                wikiUrls.push($('tbody td > a',"#postermain", html)[i].attribs.href);
            }
    }
   runner(wikiUrls);
  })
  .catch(function(err){
    //handle error
  });
function runner(items){
    var courses = [];
    var select;
    for (let i = 0; i < 2; i++) {
        newurl = "https://academic-calendar.wlu.ca" + items[i];
        console.log(newurl);
        rp(newurl)
        .then(function(html){
            select = $('caption', html).text();
            if(select =="Course Offerings"){
                select = $('td>a',".zebra",html).length;
                console.log(select);

            }

        }).catch(function(err){

        });
    }
    
}