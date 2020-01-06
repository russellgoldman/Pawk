import 'package:flutter/material.dart';
import 'package:flutter_frontend/colours.dart';

class TitleBar extends StatefulWidget {
  final String title;
  final bool showShadow;
  final Function searchCallback;

  TitleBar({ this.title, this.showShadow, this.searchCallback });

  @override
  _TitleBarState createState() => _TitleBarState();
}

class _TitleBarState extends State<TitleBar> {
  bool searchTap = false;

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
              padding: EdgeInsets.only(bottom: 12.5),
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
                  Expanded(child: SizedBox()),
                  GestureDetector(
                    onTap: () {
                      if (searchTap) widget.searchCallback('');
                      setState(() => searchTap = !searchTap);
                    },
                    child: AnimatedContainer(
                      height: 40,
                      width: searchTap ? 150 : 40,
                      duration: Duration(milliseconds: 300),
                      decoration: BoxDecoration(
                        shape: BoxShape.rectangle,
                        borderRadius: BorderRadius.circular(25),
                        color: grey_button
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget> [
                          SizedBox(height: 40, width: 10),
                          Expanded(child: searchTap ? TextField(
                            style: TextStyle(
                              color: Colors.black54,
                              fontFamily: 'Avenir',
                              fontSize: 16,
                              letterSpacing: 0.5
                            ),
                            autofocus: true,
                            textCapitalization: TextCapitalization.characters,
                            decoration: InputDecoration(
                              contentPadding: const EdgeInsets.only(left: 5, bottom: 10),
                              border: InputBorder.none,
                              hintText: 'CP104',
                            ),
                            cursorColor: Colors.grey,
                            onChanged: (String searchText) {
                              widget.searchCallback(searchText);
                            }
                          ) : Container()),
                          Image(
                            image: AssetImage('assets/images/search.png'),
                            height: 20,
                            width: 20
                          ),
                          SizedBox(height: 40, width: 10),
                        ]
                      )
                    ),
                  ),
                  SizedBox(width: 10),
                  GestureDetector(
                    onTap: () {
                      print('tapped filters');
                    },
                    child: Container(
                      height: 40,
                      width: 40,
                      decoration: BoxDecoration(
                        shape: BoxShape.rectangle,
                        borderRadius: BorderRadius.circular(25),
                        color: grey_button
                      ),
                      child: Image(
                        image: AssetImage('assets/images/filter.png'),
                        height: 20,
                        width: 20
                      )
                    ),
                  ),
                  SizedBox(width: 35)
                ]
              ),
            ),
          ),
        ],
      )
    );
  }
}