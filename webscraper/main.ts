import Webscraper from './src/webscraper'
const prompts = require('prompts');

console.log(`Web Scraping Options\n1: Courses\n2: Programs\n3: Course Offerings`)
console.log('Enter numbers you want to scrape\n(e.g. 13 is Courses and Course Offerings)\n')

class Option {
    name: string;
    defaultToggle: boolean = false;

    constructor(name: string) {
        this.name = name;
    }
}

let options: Array<Option> = [
    new Option('Courses'),
    new Option('Programs'),
    new Option('Course Offerings'),
];

(async () => {
    // url roots
    var academicCalendarRoot: string = 'https://academic-calendar.wlu.ca/section.php?cal=1&s=939&y=79';
    var courseOfferingRoot: string = '';    // TODO
    
    const webscraper = new Webscraper(academicCalendarRoot, courseOfferingRoot);

    await prompts({
        type: 'text',
        name: 'value',
        message: 'Selected options:'
    }).then((input: any) => {
        let val: string = input.value;
        options.forEach((option: Option, index: number) => {
            if (val.includes(`${index + 1}`)) {
                switch(option.name) {
                    case 'Courses':
                        webscraper.courseScrape();
                        break;
                    case 'Programs':
                        webscraper.programScrape();
                        break;
                    case 'Course Offerings':
                        webscraper.courseOfferingScrape();
                        break;
                }

                console.log(`-> ${option.name}`)
            }
        });
    });
})();