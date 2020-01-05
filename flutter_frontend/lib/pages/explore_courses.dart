import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_info_tile.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_info_expanded.dart';
import 'package:flutter_frontend/widgets/explore_courses/sliding_box.dart';

class ExploreCourses extends StatefulWidget {
  @override
  _ExploreCoursesState createState() => _ExploreCoursesState();
}

class Course {
  String code;
  double rating;
  String description;

  Course({ this.code, this.rating, this.description });
}

class _ExploreCoursesState extends State<ExploreCourses> {
  final List<Course> courses = [
    Course(code: 'BU111', rating: 4.7, 
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus dignissim sodales tortor, a luctus est. Pellentesque sed orci dolor. Orci varius natoque penatibus et.' 
    ),
    Course(code: 'BU121', rating: 4.4, description: 'BU121' ),
    Course(code: 'BU127', rating: 3.8, description: 'BU127' ),
    Course(code: 'CP104', rating: 5.0, description: 'CP104' ),
    Course(code: 'CP164', rating: 4.6, description: 'CP164' ),
    Course(code: 'CP212', rating: 4.2, description: 'CP212' ),
    Course(code: 'CP213', rating: 4.5, description: 'CP213' ),
    Course(code: 'CP264', rating: 3.7, description: 'CP264' ),
    Course(code: 'CP470', rating: 4.8, description: 'CP470' )
  ];

  String courseToShow;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: light_grey_background,
      body: SafeArea(
        child: ListView.builder(
          itemCount: courses.length,
          itemBuilder: (context, index) {
            return Padding(
              padding: EdgeInsets.fromLTRB(0, 20, 0, 0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
                      Padding(
                        padding: EdgeInsets.fromLTRB(0, 10, 16, 0),
                        child: SlidingBox(
                          id: courses[index].code,
                          selectedId: courseToShow,
                          foregroundColor: main_purple,
                          backgroundColor: light_grey_background,
                        )
                      ),
                      Expanded(
                        child: CourseInfoTile(
                          course: courses[index].code,
                          rating: courses[index].rating,
                          updateSelectedCourse: (String course) {
                            String toSet;
                            if (courseToShow != courses[index].code) toSet = courses[index].code;
                            setState(() {
                              courseToShow = toSet;
                            });
                          },
                        ),
                      ),
                    ]
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(35, 5, 35, 0),
                    child: AnimatedContainer(
                      duration: Duration(milliseconds: 300),
                      height: courseToShow == courses[index].code ? 170 : 0,
                      child: CourseInfoExpanded(code: courses[index].code, description: courses[index].description)
                    ),
                  ),
                  Divider(
                    color: grey_divider,
                    thickness: 1,
                    height: 0,
                  ),
                ],
              ),
            );
          }
        ),
      )
    );
  }
}