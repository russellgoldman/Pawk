import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/dummy_data/dummy_courses.dart';
import 'package:flutter_frontend/route_models.dart';
import 'package:flutter_frontend/widgets/course_page/requisite_card.dart';

class RequisiteBody extends StatelessWidget {
  final List<DummyCourse> courses = DummyCourses().getDummyCourses();

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Container(
          margin: EdgeInsets.only(top: 20, left: 40, right: 40, bottom: 20),
          color: grey_divider,
          height: 1,
        ),
        Container(
          margin: EdgeInsets.only(left: 40, right: 40),
          child: Text(
            'Prerequisites',
            style: TextStyle(
              fontFamily: 'Avenir',
              color: dark_grey_header,
              fontSize: 20,
              fontWeight: FontWeight.bold,
            )
          ),
        ),
        SizedBox(height: 20),
        Container(
          height: 105,
          child: ListView.builder(
            shrinkWrap: true,
            scrollDirection: Axis.horizontal,
            itemCount: courses.length,
            itemBuilder: (context, index) {
              EdgeInsets insets = EdgeInsets.only(right: 16);

              if (index == courses.length - 1) {
                insets = EdgeInsets.only(right: 40);
              }
              if (index == 0) {
                insets = EdgeInsets.only(left: 40, right: 16);
              }

              return InkResponse(
                onTap: () {
                  print('tapped ${courses[index].code}');
                  Navigator.pushNamed(context, CoursePageRoute, arguments: CoursePageArguments(
                    code: courses[index].code,
                    name: courses[index].name,
                    rating: courses[index].rating,
                    description: courses[index].description
                  ));
                },
                child: Container(
                  margin: insets,
                  child: RequisiteCard(
                    code: courses[index].code,
                    rating: courses[index].rating
                  )
                )
              );
            }
          ),
        )
      ],
    );
  }
}