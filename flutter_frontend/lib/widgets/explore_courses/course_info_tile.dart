import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';

class CourseInfoTile extends StatelessWidget {
  final String course;
  final double rating;
  final Function updateSelectedCourse;

  CourseInfoTile({ this.course, this.rating, this.updateSelectedCourse });

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        // courseToSet null by default, if selected then set
        updateSelectedCourse(course);
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
                course,
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
              '$rating',
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
    );
  }
}