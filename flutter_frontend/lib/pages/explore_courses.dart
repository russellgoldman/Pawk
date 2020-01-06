import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/dummy_data/dummy_courses.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_list_item.dart';
import 'package:flutter_frontend/widgets/explore_courses/title_bar.dart';

class ExploreCourses extends StatefulWidget {
  @override
  _ExploreCoursesState createState() => _ExploreCoursesState();
}

class _ExploreCoursesState extends State<ExploreCourses>  {
  ScrollController scrollController;
  double scrollPosition;

  DummyCourses dummyCourses = DummyCourses();
  List<DummyCourse> courses;

  String courseToExpand;
  bool showDropShadow = false;
  String getCourseToExpand() => courseToExpand;
  void setCourseToExpand(String course) => setState(() => courseToExpand = course);

  @override
  void initState() {
    super.initState();
    scrollController = ScrollController();
    scrollController.addListener(updateDropShadow);

    courses = dummyCourses.getDummyCourses();
  }

  void updateDropShadow() {
    if (!showDropShadow && scrollController.position.pixels > 7) {
      print(true);
      setState(() {
        showDropShadow = true;
      });
    } else if (showDropShadow && scrollController.position.pixels < 7) {
      print(false);
      setState(() {
        showDropShadow = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: light_grey_background,
      body: Padding(
        padding: const EdgeInsets.fromLTRB(0, 20, 0, 0),
        child: SafeArea(
          child: Column(
            children: <Widget>[
              TitleBar(title: 'Courses', showShadow: showDropShadow,),
              Expanded(child: ListView.builder(
                controller: scrollController,
                itemCount: courses.length,
                itemBuilder: (context, index) {
                  return CourseListItem(
                    code: courses[index].code,
                    name: courses[index].name,
                    rating: courses[index].rating,
                    description: courses[index].description,
                    getCourseToExpand: getCourseToExpand,
                    setCourseToExpand: setCourseToExpand,
                  );
                }
              ))
            ],
          ),
        ),
      )
    );
  }
}