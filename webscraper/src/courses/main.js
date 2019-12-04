
const rp = require('request-promise');
const $ = require('cheerio');
const url = 'https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79';

rp(url)
  .then(function(html){
    //success!
    const wikiUrls = [];
    for (let i = 0; i < 78; i++) {
      wikiUrls.push($('td > a', html)[i].attribs.href);
    }
    console.log(wikiUrls);
  })
  .catch(function(err){
    //handle error
  });