import 'package:flutter/material.dart';

import 'package:get/get.dart';

import '../controllers/book_catalog_controller.dart';
import 'package:flutter_treeview/flutter_treeview.dart';

///
/// 书籍目录
///
class BookCatalogView extends GetView<BookCatalogController> {
  const BookCatalogView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('BookCatalogView'),
        centerTitle: true,
      ),
      body: const Center(
        child: Text(
          'BookCatalogView is working',
          style: TextStyle(fontSize: 20),
        ),
      ),
    );
  }
}

class BookCatalog extends GetView<BookCatalogController> {
  const BookCatalog({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Text("出门"),
    );
  }
}
