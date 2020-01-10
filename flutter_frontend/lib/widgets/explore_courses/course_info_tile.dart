import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_svg/flutter_svg.dart';

class CourseInfoTile extends StatelessWidget {
  final String code;
  final String name;
  final double rating;
  final Function updateSelectedCourse;

  CourseInfoTile({ this.code, this.name, this.rating, this.updateSelectedCourse });

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        // courseToSet null by default, if selected then set
        updateSelectedCourse(code);
      },
      borderRadius: BorderRadius.circular(5),
      child: Padding(
        padding: const EdgeInsets.fromLTRB(10, 20, 10, 20),
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
                    code,
                    style: TextStyle(
                      color: grey_header,
                      fontFamily: 'Avenir',
                      fontSize: 16
                    )
                  )
                ),
                SvgPicture.asset(
                  'assets/images/rating_star.svg',
                  height: 15.0,
                  width: 15.0,
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
              ]
            ),
            SizedBox(height: 5),
            Row(
              children: <Widget>[
              Flexible(
                child: Text(
                  name,
                  style: TextStyle(
                    color: grey_header,
                    fontFamily: 'Avenir',
                    fontSize: 15,
                  )
                ),
              ),
              SizedBox(width: 60)
            ])
          ],
        ),
      ),
    );
  }
}