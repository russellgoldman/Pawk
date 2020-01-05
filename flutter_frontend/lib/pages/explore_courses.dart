import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/dummy_data/dummy_courses.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_list_item.dart';

class ExploreCourses extends StatefulWidget {
  @override
  _ExploreCoursesState createState() => _ExploreCoursesState();
}

class _ExploreCoursesState extends State<ExploreCourses>  {
  DummyCourses dummyCourses = DummyCourses();
  List<DummyCourse> courses;

  String courseToExpand;
  String getCourseToExpand() => courseToExpand;
  void setCourseToExpand(String course) => setState(() => courseToExpand = course);

  @override
  void initState() {
    super.initState();
    courses = dummyCourses.getDummyCourses();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: light_grey_background,
      body: Padding(
        padding: const EdgeInsets.fromLTRB(0, 20, 0, 0),
        child: SafeArea(
          child: ListView.builder(
            itemCount: courses.length,
            itemBuilder: (context, index) {
              return CourseListItem(
                course: courses[index].code,
                rating: courses[index].rating,
                description: courses[index].description,
                getCourseToExpand: getCourseToExpand,
                setCourseToExpand: setCourseToExpand,
              );
            }
          ),
        ),
      )
    );
  }
}