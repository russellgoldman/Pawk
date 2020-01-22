import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/dummy_data/dummy_courses.dart';
import 'package:flutter_frontend/route_models.dart';
import 'package:flutter_frontend/widgets/common/title_bar_container.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_list_item.dart';

class ExploreCourses extends StatefulWidget {
  @override
  _ExploreCoursesState createState() => _ExploreCoursesState();
}

class _ExploreCoursesState extends State<ExploreCourses>  {
  // course data
  DummyCourses dummyCourses = DummyCourses();
  List<DummyCourse> courses;
  List<DummyCourse> searchedCourses;

  // course_info_expanded controls
  String courseToExpand;
  String getCourseToExpand() => courseToExpand;
  void setCourseToExpand(String course) => setState(() => courseToExpand = course);

  // title bar drop shadow
  bool showDropShadow = false;
  ScrollController scrollController;
  double scrollPosition;

  // title bar filter
  void updateCourseListFromSearch(String searchText) {
    bool isSelectedCourseWithinSearch = false;
    setState(() {
      searchedCourses = courses.where((DummyCourse course) {
        if (course.code.startsWith(searchText)) {
          if (course.code == courseToExpand) isSelectedCourseWithinSearch = true;
          return true;
        }
        return false;
      }).toList();
      if (!isSelectedCourseWithinSearch) courseToExpand = null;
    }); //apples
  }

  @override
  void initState() {
    super.initState();
    scrollController = ScrollController();
    scrollController.addListener(updateDropShadow);

    courses = dummyCourses.getDummyCourses();
    searchedCourses = courses;
  }

  void updateDropShadow() {
    if (!showDropShadow && scrollController.position.pixels > 7) setState(() => showDropShadow = true);
    else if (showDropShadow && scrollController.position.pixels < 7) setState(() => showDropShadow = false);
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
              TitleBarContainer(
                route: ExploreCoursesRoute,
                title: 'Courses',
                showShadow: showDropShadow,
                searchCallback: updateCourseListFromSearch
              ),
              Expanded(child: ListView.builder(
                controller: scrollController,
                itemCount: searchedCourses.length,
                itemBuilder: (context, index) {
                  return CourseListItem(
                    code: searchedCourses[index].code,
                    name: searchedCourses[index].name,
                    rating: searchedCourses[index].rating,
                    description: searchedCourses[index].description,
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