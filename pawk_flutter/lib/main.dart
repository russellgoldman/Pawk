import 'package:flutter/material.dart';
import 'package:pawk_flutter/pages/explore_courses.dart';

void main() => runApp(MaterialApp(
    initialRoute: '/',
    routes: {
      '/': (context) => ExploreCourses()
    }
));
