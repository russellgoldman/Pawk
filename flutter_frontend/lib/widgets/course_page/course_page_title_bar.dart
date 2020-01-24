import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_svg/flutter_svg.dart';

class CoursePageTitleBar extends StatelessWidget {
  final String code;
  final String title;
  final bool titleChange;

  CoursePageTitleBar({ this.code, this.title, this.titleChange });

  @override
  Widget build(BuildContext context) {
    return Stack(
      alignment: AlignmentDirectional.center,
      children: <Widget>[
        Align(
          alignment: Alignment.centerLeft,
          child: Container(
            padding: EdgeInsets.all(10),
            margin: EdgeInsets.only(left: 31),
            child: GestureDetector(
              onTap: () {
                Navigator.pop(context);
              },
              child: SvgPicture.asset(
                'assets/images/prev_page_arrow.svg',
                height: 10,
                width: 10
              ),
            ),
          ),
        ),
        Stack(
          alignment: AlignmentDirectional.center,
          children: <Widget>[
            AnimatedOpacity(
              opacity: titleChange ? 0 : 1,
              duration: Duration(milliseconds: titleChange ? 100 : 700),
              child: Text(
                title,
                style: TextStyle(
                  color: main_purple,
                  fontFamily: 'Avenir',
                  fontSize: 20,
                  fontWeight: FontWeight.bold
                ),
              ),
            ),
            AnimatedOpacity(
              opacity: titleChange ? 1 : 0,
              duration: Duration(milliseconds: titleChange ? 700 : 100),
              child: Text(
                code,
                style: TextStyle(
                  color: main_purple,
                  fontFamily: 'Avenir',
                  fontSize: 20,
                  fontWeight: FontWeight.bold
                ),
              ),
            )
          ],
        )
      ],
    );
  }
}