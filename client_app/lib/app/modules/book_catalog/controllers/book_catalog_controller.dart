import 'package:get/get.dart';
import 'package:flutter_treeview/flutter_treeview.dart' as TreeView;
import 'package:flutter/material.dart';

class BookCatalogController extends GetxController {
  //TODO: Implement BookCatalogController

  final count = 0.obs;
  bool docsOpen = true;

  @override
  void onInit() {
    super.onInit();
  }

  @override
  void onReady() {
    super.onReady();
  }

  @override
  void onClose() {
    super.onClose();
  }

  void increment() => count.value++;
}
