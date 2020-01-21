import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_svg/flutter_svg.dart';

class TitleBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        SvgPicture.asset(
          'assets/images/prev_page_arrow.svg',
          height: 20,
          width: 20
        ),
        Text(
          'Courses',
          style: TextStyle(
            color: main_purple,
            fontFamily: 'Avenir',
            fontSize: 20,
            fontWeight: FontWeight.bold
          ),
        )
      ],
    );
  }
}