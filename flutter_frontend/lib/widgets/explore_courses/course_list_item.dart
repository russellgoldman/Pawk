import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_info_tile.dart';
import 'package:flutter_frontend/widgets/explore_courses/course_info_expanded.dart';
import 'package:flutter_frontend/widgets/explore_courses/sliding_box.dart';

class CourseListItem extends StatefulWidget {
  final String course;
  final double rating;
  final String description;
  final Function getCourseToExpand;
  final Function setCourseToExpand;

  CourseListItem({
    this.course,
    this.rating,
    this.description,
    this.getCourseToExpand,
    this.setCourseToExpand
  });

  @override
  CourseListItemState createState() => CourseListItemState();
}

class CourseListItemState extends State<CourseListItem> with SingleTickerProviderStateMixin {
  AnimationController expandController;
  Animation<double> animation; 

  @override
  void initState() {
    super.initState();
    setupAnimation();
    shouldExpandOrCollapse();
  }

  void setupAnimation() {
    expandController = AnimationController(
      vsync: this,
      duration: Duration(milliseconds: 500)
    );
    animation = CurvedAnimation(
      parent: expandController,
      curve: Curves.easeInOut,
    );
  }

  void shouldExpandOrCollapse() {
    if (widget.getCourseToExpand() == widget.course) {
      expandController.forward();
    } else {
      expandController.reverse();
    }
  }

  @override
  void didUpdateWidget(CourseListItem oldWidget) {
    super.didUpdateWidget(oldWidget);
    shouldExpandOrCollapse();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Row(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Padding(
              padding: EdgeInsets.fromLTRB(0, 0, 16, 0),
              child: SlidingBox(
                id: widget.course,
                selectedId: widget.getCourseToExpand(),
                foregroundColor: main_purple,
                backgroundColor: light_grey_background,
              )
            ),
            Expanded(
              child: CourseInfoTile(
                course: widget.course,
                rating: widget.rating,
                updateSelectedCourse: (String course) {
                  String toSet;
                  if (widget.getCourseToExpand() != widget.course) toSet = widget.course;
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
            sizeFactor: animation,
            child: CourseInfoExpanded(
              code: widget.course,
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