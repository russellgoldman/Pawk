import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_info_card.dart';

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
                        child: Stack(
                          children: <Widget>[
                            AnimatedContainer(
                              duration: Duration(milliseconds: 800),
                              child: Container(
                                color: light_grey_background,
                                height: 13,
                                width: 9
                              )
                            ),
                            AnimatedContainer(
                              duration: Duration(milliseconds: 300),
                              width: courseToShow == courses[index].code ? 9 : 0,
                              height: 13,
                              decoration: BoxDecoration(
                                color: main_purple
                              ),
                            ),
                          ],
                        ),
                        
                      ),
                      Expanded(
                        child: InkWell(
                          onTap: () {
                            String code;
                            if (courseToShow != courses[index].code) {
                              code = courses[index].code;
                            }
                            setState(() {
                              courseToShow = code;
                            });
                          },
                          borderRadius: BorderRadius.circular(5),
                          child: Padding(
                            padding: const EdgeInsets.fromLTRB(10, 20, 10, 10),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: <Widget>[
                                Expanded(
                                  child: Text(
                                    courses[index].code,
                                    style: TextStyle(
                                      color: grey_header,
                                      fontFamily: 'Avenir',
                                      fontSize: 16
                                    )
                                  )
                                ),
                                Image(
                                  image: AssetImage('assets/images/rating_star.png'),
                                  height: 20.0,
                                  width: 20.0,
                                ),
                                SizedBox(width: 10),
                                Text(
                                  '${courses[index].rating}',
                                  style: TextStyle(
                                    color: bolded_grey_header,
                                    fontSize: 16,
                                    fontFamily: 'Avenir',
                                    fontWeight: FontWeight.bold,
                                  )
                                ),
                                SizedBox(width: 30)
                              ],
                            ),
                          ),
                        ),
                      ),
                    ]
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(35, 5, 35, 0),
                    child: AnimatedContainer(
                      duration: Duration(milliseconds: 300),
                      height: courseToShow == courses[index].code ? 170 : 0,
                      child: CourseInfoCard(code: courses[index].code, description: courses[index].description)
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