import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_svg/svg.dart';

class RequisiteCard extends StatelessWidget {
  final String code;
  final double rating;

  RequisiteCard({ this.code, this.rating });

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 10, bottom: 10),
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
    ));
  }
}