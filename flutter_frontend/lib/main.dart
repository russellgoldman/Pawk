import 'package:flutter/material.dart';
import 'package:flutter_frontend/pages/course_page.dart';
import 'package:flutter_frontend/pages/explore_courses.dart';

void main() => runApp(MaterialApp(
  initialRoute: '/explore_courses',
  routes: {
    '/explore_courses': (context) => ExploreCourses(),
    '/course_page': (context) => CoursePage()
  }
));
