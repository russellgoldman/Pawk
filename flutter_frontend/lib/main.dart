import 'package:flutter/material.dart';
import 'package:flutter_frontend/pages/explore_courses.dart';

void main() => runApp(MaterialApp(
  initialRoute: '/',
  routes: {
    '/': (context) => ExploreCourses()
  }
));
