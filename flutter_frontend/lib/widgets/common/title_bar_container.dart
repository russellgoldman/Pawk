import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/widgets/course_page/course_page_title_bar.dart';
import 'package:flutter_frontend/widgets/explore_courses/explore_courses_title_bar.dart';
import 'package:flutter_frontend/route_models.dart';

class TitleBarContainer extends StatefulWidget {
  final String route;
  final String title;
  final bool showShadow;
  final Function searchCallback;
  final String code;

  TitleBarContainer({
    this.route,
    this.title,
    this.showShadow,
    this.searchCallback,
    this.code
  });

  @override
  _TitleBarContainerState createState() => _TitleBarContainerState();
}

class _TitleBarContainerState extends State<TitleBarContainer> {
  renderContainer() {
    switch(widget.route) {
      case ExploreCoursesRoute:
        return ExploreCoursesTitleBar(
          title: widget.title,
          searchCallback: widget.searchCallback
        );
        break;
      case CoursePageRoute:
        return CoursePageTitleBar(
          code: widget.code,
          title: widget.title,
          titleChange: widget.showShadow,
        );
        break;
      default:
        return SizedBox();
        break;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 50,
      child: Stack(
        alignment: AlignmentDirectional.bottomCenter,
        children: <Widget>[
          AnimatedContainer(
            duration: Duration(milliseconds: 200),
            height: 0,
            decoration: BoxDecoration(
              boxShadow: [
                BoxShadow(blurRadius: 10, color: Colors.grey, spreadRadius: widget.showShadow ? 3 : 0)
              ]
            ),
          ),
          Container(
            color: light_grey_background,
            child: Padding(
              padding: EdgeInsets.only(bottom: 12.5),
              child: renderContainer()
            )
          )
        ],
      )
    );
  }
}