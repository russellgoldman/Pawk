const String ExploreCoursesRoute = '/explore_courses';
const String CoursePageRoute = '/course_page';

class CoursePageArguments {
  final String code;
  final String name;
  final double rating;
  final String description;

  CoursePageArguments({
    this.code,
    this.name,
    this.rating,
    this.description
  });
}