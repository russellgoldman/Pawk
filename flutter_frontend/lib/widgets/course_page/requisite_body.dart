import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';

class RequisiteBody extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 40, right: 40),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            margin: EdgeInsets.only(top: 20, bottom: 20),
            color: grey_divider,
            height: 1,
          ),
          Text(
            'Prerequisites',
            style: TextStyle(
              fontFamily: 'Avenir',
              color: dark_grey_header,
              fontSize: 20,
              fontWeight: FontWeight.bold,
            )
          )
        ],
      ),
    );
  }
}