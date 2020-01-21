import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/route_models.dart';
import 'package:flutter_frontend/widgets/course_page/title_bar.dart';

class CoursePage extends StatefulWidget {
  final String code;
  final String name;
  final double rating;
  final String description;

  CoursePage({
    this.code,
    this.name,
    this.rating,
    this.description
  });

  @override
  _CoursePageState createState() => _CoursePageState();
}

class _CoursePageState extends State<CoursePage> {
  @override
  Widget build(BuildContext context) {
    final CoursePageArguments args = ModalRoute.of(context).settings.arguments;
    print(args.code);
    print(args.name);
    print(args.rating);
    print(args.description);

    return Scaffold(
      backgroundColor: light_grey_background,
      body: Padding(
        padding: const EdgeInsets.fromLTRB(0, 20, 0, 0),
        child: SafeArea(
          child: Column(
            children: <Widget>[
              TitleBar()
            ]
          )
        )
      )
    );
  }
}