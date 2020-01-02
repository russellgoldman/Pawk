import 'package:flutter/material.dart';
import 'package:pawk_flutter/colours.dart';

class ExploreCourses extends StatefulWidget {
  @override
  _ExploreCoursesState createState() => _ExploreCoursesState();
}

class Course {
  String code;
  double rating;

  Course({ this.code, this.rating });
}

class _ExploreCoursesState extends State<ExploreCourses> {
  List<Course> courses = [
    Course(code: 'BU111', rating: 4.7),
    Course(code: 'BU121', rating: 4.4),
    Course(code: 'BU127', rating: 3.8),
    Course(code: 'CP104', rating: 5.0),
    Course(code: 'CP164', rating: 4.6),
    Course(code: 'CP212', rating: 4.2),
    Course(code: 'CP213', rating: 4.5),
    Course(code: 'CP264', rating: 3.7)
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: light_grey_background,
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.fromLTRB(40.0, 20, 40.0, 0),
          child: ListView.builder(
            itemCount: courses.length,
            itemBuilder: (context, index) {
              return Padding(
                padding: const EdgeInsets.fromLTRB(0, 10, 0, 10),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: <Widget>[
                        Expanded(
                          child: Text(
                            courses[index].code,
                            style: TextStyle(
                              color: grey_header,
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
                            fontWeight: FontWeight.bold,
                          )
                        )
                      ],
                    ),
                    SizedBox(height: 10),
                    Divider(
                      color: grey_divider,
                      thickness: 1
                    ),
                  ],
                )
              );
            }
          ),
        ),
      )
    );
  }
}