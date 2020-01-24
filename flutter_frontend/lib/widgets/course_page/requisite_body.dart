import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';
import 'package:flutter_frontend/dummy_data/dummy_courses.dart';
import 'package:flutter_frontend/widgets/course_page/requisite_card.dart';

enum requisiteType {
  prerequisite,
  corequisite,
  exclusion,
  note 
}

class RequisiteBody extends StatelessWidget {
  final requisiteType type;
  final String code;
  final String name;
  final double rating;
  final String description;
  final List<DummyCourse> requisiteCourses;
  final String requisiteDescription;

  RequisiteBody({ 
    this.type,
    this.code,
    this.name,
    this.rating,
    this.description,
    this.requisiteCourses,
    this.requisiteDescription
  });

  String _requisiteTypeToString(bool plural) {
    var requisiteString = 'Note';

    switch (type) {
      case requisiteType.prerequisite:
        requisiteString = 'Prerequisite';
        break;
      case requisiteType.corequisite:
        requisiteString = 'Corequisite';
        break;
      case requisiteType.exclusion:
        requisiteString = 'Exclusion';
        break;
      case requisiteType.note:
        requisiteString = 'Note';
        break;
    }

    return requisiteString = plural ? requisiteString + 's' : requisiteString;
  }

  _renderRequisiteInformation() {
    if (this.requisiteDescription != '') {
      return Container(
        margin: EdgeInsets.only(left: 40),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            SizedBox(height: 20),
            Text(
              _requisiteTypeToString(false) + ' Information',
              style: TextStyle(
                fontFamily: 'Avenir',
                color: dark_grey_header,
                fontSize: 16
              )
            ),
            SizedBox(height: 5),
            Text(
              this.requisiteDescription,
              style: TextStyle(
                fontFamily: 'Avenir',
                color: dark_grey_header,
                fontSize: 12
              )
            )
          ]
        )
      );
    }
    return SizedBox();
  }

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
            _requisiteTypeToString(true),
            style: TextStyle(
              fontFamily: 'Avenir',
              color: dark_grey_header,
              fontSize: 20,
              fontWeight: FontWeight.bold,
            )
          ),
        ),
        SizedBox(height: 10),
        Container(
          height: 105,
          child: ListView.builder(
            shrinkWrap: true,
            scrollDirection: Axis.horizontal,
            itemCount: requisiteCourses.length,
            itemBuilder: (context, index) {
              EdgeInsets leftInsets = EdgeInsets.only(left: 16);
              EdgeInsets rightInsets = EdgeInsets.only(right: 0);

              if (index == requisiteCourses.length - 1) {
                leftInsets = EdgeInsets.only(left: 16);
                rightInsets = EdgeInsets.only(right: 40);
              }
              if (index == 0) {
                leftInsets = EdgeInsets.only(left: 40);
                rightInsets = EdgeInsets.only(right: 0);
              }

              return Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget> [
                    Container(margin: leftInsets),
                    RequisiteCard(
                      code: requisiteCourses[index].code,
                      name: requisiteCourses[index].name,
                      rating: requisiteCourses[index].rating,
                      description: requisiteCourses[index].description,
                    ),
                    Container(margin: rightInsets)
                  ]
              );
            }
          ),
        ),
        _renderRequisiteInformation(),
        SizedBox(height: 5),
      ],
    );
  }
}