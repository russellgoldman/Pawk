const rp = require('request-promise');
const $ = require('cheerio');
const potusParse = require('./potusParse');
const url = 'https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79';
var fs = require("fs");
var file;
file = fs.createWriteStream("data.txt");

rp(url)
  .then(function(html) {
    //success!
    const urls = [];
    for (let i = 0; i < 45; i++) {
      urls.push($('td > a', html)[i].attribs.href);
    }
    return Promise.all(
      urls.map(function(url) {
        return potusParse('https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79' + url);
      })
    );
  })
  .then(function(presidents) {
    //console.log(presidents);
	file.on('error', function(err) { Console.log(err) });
	presidents.forEach(value => file.write(`${value}\r\n`));
	file.end();
  })
  .catch(function(err) {
    //handle error
    console.log(err);
  });