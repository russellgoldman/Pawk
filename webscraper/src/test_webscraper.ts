import Webscraper from './webscraper';

var academicCalendarRoot: string = 'https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79';
var courseOfferingRoot: string = '';    // TODO

const webscraper = new Webscraper(academicCalendarRoot, courseOfferingRoot);

webscraper.courseScrape();