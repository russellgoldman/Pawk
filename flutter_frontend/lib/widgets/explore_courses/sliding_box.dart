import 'package:flutter/material.dart';

class SlidingBox extends StatelessWidget {
  final String id;
  final String selectedId;
  final Color foregroundColor;
  final Color backgroundColor;

  SlidingBox({ this.id, this.selectedId, this.foregroundColor, this.backgroundColor });

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
        AnimatedContainer(
          duration: Duration(milliseconds: 800),
          child: Container(
            color: backgroundColor,
            height: 13,
            width: 9
          )
        ),
        AnimatedContainer(
          duration: Duration(milliseconds: 300),
          width: selectedId == id ? 9 : 0,
          height: 13,
          decoration: BoxDecoration(
            color: foregroundColor
          ),
        ),
      ],
    );
  }
}