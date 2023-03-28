import 'package:flutter/material.dart';

import 'package:get/get.dart';

import '../controllers/book_controller.dart';

class BookView extends GetView<BookController> {
  const BookView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Row(
        children: [
          Container(
            width: 260,
            color: Colors.grey.shade200,
            child: Column(
              children: [
                Container(
                  height: 30,
                  child: Row(
                    children: [
                      TextButton(
                          onPressed: () {
                            Get.back();
                          },
                          child: Text("返回")),
                      IconButton(
                          onPressed: () {
                            print('创建按钮');
                          },
                          icon: Icon(Icons.add))
                    ],
                  ),
                )
              ],
            ),
          ),
          Expanded(
              child: Container(
            child: Text("内容"),
          ))
        ],
      ),
    );
  }
}
