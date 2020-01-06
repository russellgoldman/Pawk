import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';

class TitleBar extends StatefulWidget {
  final String title;
  final bool showShadow;

  TitleBar({ this.title, this.showShadow });

  @override
  _TitleBarState createState() => _TitleBarState();
}

class _TitleBarState extends State<TitleBar> {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 50,
      padding: EdgeInsets.fromLTRB(0, 0, 0, 0),
      child: Stack(
        alignment: AlignmentDirectional.bottomCenter,
        children: <Widget>[
          AnimatedContainer(
            duration: Duration(milliseconds: 500),
            height: 0,
            decoration: BoxDecoration(
              boxShadow: [
                BoxShadow(blurRadius: 10, color: Colors.grey, spreadRadius: widget.showShadow ? 3 : 0)
              ]
            ),
          ),
          Container(
            color: light_grey_background,
            child: Padding(
              padding: EdgeInsets.only(bottom: 17.5),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  SizedBox(width: 35),
                  Text(
                    widget.title,
                    style: TextStyle(
                      color: main_purple,
                      fontFamily: 'Avenir',
                      fontSize: 20,
                      fontWeight: FontWeight.bold
                    )
                  ),
                  Expanded(child: SizedBox())
                ]
              ),
            ),
          ),
          
        ],
      )
    );
  }
}