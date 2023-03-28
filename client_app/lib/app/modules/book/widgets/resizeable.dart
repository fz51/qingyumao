/// 可边的
///
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class Resizeable extends StatefulWidget {
  final List<Widget> children;

  const Resizeable({super.key, required this.children});

  @override
  _ResizeableState createState() => _ResizeableState();
}

class _ResizeableState extends State<Resizeable> {
  double _dragPosition = 0.0;
  final double _containerWidth = 300;
  final List<double> componentWidth = [150,150];

  Widget _buildResizeable(List<Widget> w, int index) {
    if (w.length == 1) {
      return w[0];
    }

    return Row(
      children: [
        Container(
          width: componentWidth[index],
          child: w[0],
        ),
        GestureDetector(
          onHorizontalDragUpdate: (details) {
            setState(() {
              _dragPosition += details.delta.dx;
              if (_dragPosition < 0) {
                _dragPosition = 0;
              } else if (_dragPosition > _containerWidth - 10) {
                _dragPosition = _containerWidth - 10;
              }
              componentWidth[index] = _dragPosition;
              componentWidth[index+1] = _containerWidth - _dragPosition - 10;
            });
          },
          child: Container(
            width: 10,
            color: Colors.grey,
          ),
        ),
        Container(
          width: componentWidth[index+1],
          child: _buildResizeable(w.sublist(1), index + 1),
        ),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> children = super.widget.children;
    if (children.length == 1) {
      return children[0];
    }
    return _buildResizeable(children,0);
  }
}
