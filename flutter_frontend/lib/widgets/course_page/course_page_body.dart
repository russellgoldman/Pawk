import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/widgets/course_page/requisite_body.dart';
import 'package:flutter_svg/svg.dart';

class CoursePageBody extends StatelessWidget {
  final String code;
  final double rating;
  final String description;

  CoursePageBody({ this.code, this.rating, this.description });

  @override
  Widget build(BuildContext context) {
    int maxRating = 5;

    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Container(
          margin: EdgeInsets.only(top: 30),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Container(
                margin: EdgeInsets.only(left: 40),
                child: Text(
                  code,
                  style: TextStyle(
                    fontFamily: 'Avenir',
                    color: dark_grey_header,
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                  )
                )
              ),
              Expanded(child: SizedBox()),
              Container(
                margin: EdgeInsets.only(right: 40),
                decoration: BoxDecoration(
                  color: grey_rating_bar,
                  borderRadius: BorderRadius.all(Radius.circular(9))
                ),
                padding: EdgeInsets.fromLTRB(9, 6, 9, 7),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: () {
                    List<Widget> ratingStars = List<Widget>();
                    for (int i = 0; i < maxRating; i++) {
                      i < rating.floor() ? ratingStars.add(
                        SvgPicture.asset(
                          'assets/images/selected_rating_star.svg',
                          width: 15,
                          height: 15
                        )
                      ) : ratingStars.add(
                        SvgPicture.asset(
                          'assets/images/leftover_rating_star.svg',
                          width: 15,
                          height: 15
                        )
                      );
                    }
                    return ratingStars;
                  }(),
                ) 
              ),
            ],
          ),
        ),
        Container(
          margin: EdgeInsets.fromLTRB(40, 10, 40, 0),
          child: Text(
            description,
            textAlign: TextAlign.justify,
            style: TextStyle(
              fontFamily: 'Avenir',
              color: light_grey_description,
              fontSize: 14,
              height: 1.5,
            ),
          ),
        ),
        Container(
          margin: EdgeInsets.fromLTRB(40, 30, 40, 0),
          child: Text(
            'Currently 90 students registered',
            textAlign: TextAlign.justify,
            style: TextStyle(
              fontFamily: 'Avenir',
              color: main_purple,
              fontSize: 14,
              height: 1.5,
            ),
          ),
        ),
        RequisiteBody()
      ],
    );
  }
}