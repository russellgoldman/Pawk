const puppeteer = require('puppeteer');
const $ = require('cheerio');
const url = 'https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79';

puppeteer
  .launch()
  .then(function(browser) {
    return browser.newPage();
  })
  .then(function(page) {
    return page.goto(url).then(function() {
      return page.content();
    });
  })
  .then(function(html) {
    $('td > a', html).each(function() {
      console.log($(this).text());
    });
  })
  .catch(function(err) {
    //handle error
  });
