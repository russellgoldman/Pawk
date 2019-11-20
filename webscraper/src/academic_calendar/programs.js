const rp = require('request-promise');
const $ = require('cheerio');
const url = 'https://academic-calendar.wlu.ca/program.php?cal=1&d=2068&p=4475&s=932&y=79';


var progName; //string
var descrip; //string
var elective_credits; //array [string]
var years; //int
var required_courses; //array [string]
var co_op_count; //int
var co_op_terms; //array [string]
var progression_requirements; //array [string]
var yearCount=0;
var courses;
rp(url)
  .then(function(html) {
    progName = $('h1',"#postercontent",html).html();
    console.log(progName)
	test = 'test';
	
	header = $('p',"#postercontent",html).first();

	description = header.next().text();
	console.log(description);
	
	headerProg = header.next().text();
	


	year = $('b',"#postercontent", html).text();

	
	year = year.split(' ');
	yearCount = year.length-1;
	


	console.log(yearCount);
	var coursesl = $('p>a',html).length;

	length = $('p>a',"#postercontent",html).length;
	console.log(length);
	select = $('p>a',html).text();

//	console.log(select);
	for (let i = 0; i < length; i++) {
		console.log(i);
		links =$('p>a',html)[i].attribs.href;//get the href then check it contains course
		console.log(links);
		if(links.includes("course.php?c=")){
		//	console.log(links.text());
			console.log('suc');
		//	courses.push(links.text()+links);
		}
	}//for
	console.log(courses);
   
  })
  .catch(function(err) {
    //handle error
  });