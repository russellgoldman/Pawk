const cheerio = require('cheerio')
const $ = cheerio.load('<h2 class="title">Hello world</h2>');
const prompts = require('prompts');

import academicCalendar from './src/academic_calendar/main'
import courseOfferings from './src/course_offerings/main'

console.log(`Web Scraping Options\n1: Courses\n2: Programs\n3: Course Offerings`)
console.log('Enter numbers you want to scrape\n(e.g. 13 is Courses and Course Offerings)\n')

// scraping permission toggles
// each index + 1 represents the choose option
let optionNames: Array<String> = ['Courses', 'Programs', 'Course Offerings'];
let optionToggles: Array<Boolean> = [false, false, false];

(async () => {
    await prompts({
        type: 'text',
        name: 'value',
        message: 'Selected options:'
    }).then((input: any) => {
        let val: string = input.value;
        optionToggles.forEach((_, index: number) => {
            if (val.includes(`${index + 1}`)) {
                optionToggles[index] = true
                console.log(`-> ${optionNames[index]}`)
            }
        });
    });

    academicCalendar(optionToggles[0], optionToggles[1])
    courseOfferings(optionToggles[2])
})();