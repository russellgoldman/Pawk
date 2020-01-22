import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/route_models.dart';
import 'package:flutter_frontend/widgets/common/title_bar_container.dart';
import 'package:flutter_frontend/widgets/course_page/course_page_body.dart';

class CoursePage extends StatefulWidget {
  @override
  _CoursePageState createState() => _CoursePageState();
}

class _CoursePageState extends State<CoursePage> {
  @override
  Widget build(BuildContext context) {
    final CoursePageArguments args = ModalRoute.of(context).settings.arguments;

    return Scaffold(
      backgroundColor: light_grey_background,
      body: Padding(
        padding: const EdgeInsets.fromLTRB(0, 20, 0, 0),
        child: SafeArea(
          child: Column(
            children: <Widget>[
              TitleBarContainer(
                route: CoursePageRoute,
                title: 'Courses',
                showShadow: false,
              ),
              CoursePageBody(
                code: args.code,
                rating: args.rating,
                description: args.description
              )
            ]
          )
        )
      )
    );
  }
}