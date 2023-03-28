import 'package:flutter/material.dart';

import 'package:get/get.dart';

import '../controllers/home_controller.dart';

import '../../../routes/app_pages.dart';

class HomeView extends GetView<HomeController> {
  const HomeView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('首页'),
        centerTitle: true,
      ),
      body: Center(
        child: ButtonBar(
          children: [
            TextButton(
                onPressed: () {
                  // 跳转到
                  Get.toNamed(Routes.BOOK);
                },
                child: const Text("文档")),
          ],
        ),
      ),
    );
  }
}
