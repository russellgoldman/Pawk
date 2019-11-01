const rp = require('request-promise');
const $ = require('cheerio');

const potusParse = function(url) {
  return rp(url)
    .then(function(html) {
      return {
        program: $('.firstHeading', html).text(),
      };
    })
    .catch(function(err) {
      //handle error
    });
};

module.exports = potusParse; 
