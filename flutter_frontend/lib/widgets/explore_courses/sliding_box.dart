import 'package:flutter/material.dart';

class SlidingBox extends StatelessWidget {
  final String id;
  final String selectedId;
  final Color foregroundColor;

  SlidingBox({ this.id, this.selectedId, this.foregroundColor });

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
        Container(
          height: 13,
          width: 9
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