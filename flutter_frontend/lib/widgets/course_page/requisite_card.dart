import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/route_models.dart';
import 'package:flutter_svg/svg.dart';

class RequisiteCard extends StatelessWidget {
  final String code;
  final String name;
  final double rating;
  final String description;

  RequisiteCard({ this.code, this.name, this.rating, this.description });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        print('tapped $code');
        Navigator.pushNamed(context, CoursePageRoute, arguments: CoursePageArguments(
          code: code,
          name: name,
          rating: rating,
          description: description
        ));
      },
      child: Container(
        margin: EdgeInsets.fromLTRB(0, 10, 0, 10),
        width: 75,
        decoration: BoxDecoration(
          color: light_grey_card,
          borderRadius: BorderRadius.circular(10),
          boxShadow: [BoxShadow(
            color: Colors.black26,
            blurRadius: 4,
            offset: Offset(0, 4)
          )]
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              code,
              style: TextStyle(
                color: dark_grey_header,
                fontSize: 14,
                fontFamily: 'Avenir',
                fontWeight: FontWeight.bold
              )
            ),
            SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                SvgPicture.asset(
                  'assets/images/rating_star.svg',
                  height: 15.0,
                  width: 15.0,
                ),
                SizedBox(width: 7),
                Text(
                  '$rating',
                  style: TextStyle(
                    color: bolded_grey_header,
                    fontSize: 14,
                    fontFamily: 'Avenir',
                    fontWeight: FontWeight.bold,
                  )
                ),
              ]
            )
          ]
      )),
    );
  }
}