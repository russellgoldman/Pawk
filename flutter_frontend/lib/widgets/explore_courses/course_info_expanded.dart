import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';

class CourseInfoExpanded extends StatelessWidget {
  final String code;
  final String description;
  CourseInfoExpanded({ this.code, this.description });
    
  @override
  Widget build(BuildContext context) {
    return Wrap(
      children: <Widget>[
        Row(children: <Widget>[
          Flexible(child: Text(
            description,
            maxLines: 4,
            overflow: TextOverflow.ellipsis,
            style: TextStyle(
              color: light_grey_description,
              fontFamily: 'Avenir',
              fontWeight: FontWeight.w200,
              fontSize: 14,
            )
          )),
        ]),
        Padding(
          padding: const EdgeInsets.only(top: 15, bottom: 15),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              InkResponse(
                onTap: () {
                  print('favourite $code');
                },
                radius: 25,
                child: Image(
                  image: AssetImage('assets/images/favourite_course.png'),
                  height: 40.0,
                  width: 30.0,
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(top: 3, left: 5),
                child: InkResponse(
                  onTap: () {
                    print('add $code');
                  },
                  radius: 25,
                  child: Image(
                    image: AssetImage('assets/images/add_course.png'),
                    height: 40.0,
                    width: 40.0,
                  ),
                ),
              ),
              Expanded(child: SizedBox()),
              FlatButton( 
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(25)),
                onPressed: () {
                  print('learn more about $code');
                },
                color: main_purple,
                textColor: Colors.white,
                padding: EdgeInsets.fromLTRB(17, 6, 17, 6),
                child: Text(
                  'Learn More',
                  style: TextStyle(
                    fontFamily: 'Avenir',
                    fontSize: 15,
                    fontWeight: FontWeight.bold
                  )
                )
              ),
            ],
          ),
        )
      ]
    );
  }
}