import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_info_tile.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_info_expanded.dart';
import 'package:flutter_frontend/widgets/explore_courses/sliding_box.dart';

class CourseListItem extends StatefulWidget {
  final String code;
  final String name;
  final double rating;
  final String description;
  final Function getCourseToExpand;
  final Function setCourseToExpand;

  CourseListItem({
    this.code,
    this.name,
    this.rating,
    this.description,
    this.getCourseToExpand,
    this.setCourseToExpand
  });

  @override
  CourseListItemState createState() => CourseListItemState();
}

class CourseListItemState extends State<CourseListItem> with SingleTickerProviderStateMixin {
  AnimationController courseInfoExpandController;
  Animation<double> courseInfoExpandAnimation; 

  @override
  void initState() {
    super.initState();
    setupAnimation();
    shouldExpandOrCollapse();
  }

  void setupAnimation() {
    courseInfoExpandController = AnimationController(
      vsync: this,
      duration: Duration(milliseconds: 500)
    );
    courseInfoExpandAnimation = CurvedAnimation(
      parent: courseInfoExpandController,
      curve: Curves.easeInOut,
    );
  }

  void shouldExpandOrCollapse() {
    if (widget.getCourseToExpand() == widget.code) {
      courseInfoExpandController.forward();
    } else {
      courseInfoExpandController.reverse();
    }
  }

  @override
  void didUpdateWidget(CourseListItem oldWidget) {
    super.didUpdateWidget(oldWidget);
    shouldExpandOrCollapse();
  }

  @override
  void dispose() {
    courseInfoExpandController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Row(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Padding(
              padding: EdgeInsets.fromLTRB(0, 24, 16, 0),
              child: SlidingBox(
                id: widget.code,
                selectedId: widget.getCourseToExpand(),
                foregroundColor: main_purple,
              )
            ),
            Expanded(
              child: CourseInfoTile(
                code: widget.code,
                name: widget.name,
                rating: widget.rating,
                updateSelectedCourse: (String code) {
                  String toSet;
                  if (widget.getCourseToExpand() != widget.code) toSet = widget.code;
                  widget.setCourseToExpand(toSet);
                },
              ),
            ),
            SizedBox(width: 30)
          ]
        ),
        Padding(
          padding: const EdgeInsets.fromLTRB(35, 0, 35, 0),
          child: SizeTransition(
            sizeFactor: courseInfoExpandAnimation,
            child: CourseInfoExpanded(
              code: widget.code,
              description: widget.description,
            )
          ),
        ),
        Padding(
          padding: const EdgeInsets.fromLTRB(35, 0, 35, 0),
          child: Divider(
            color: grey_divider,
            thickness: 1,
            height: 0,
          ),
        ),
      ],
    );
  }
}