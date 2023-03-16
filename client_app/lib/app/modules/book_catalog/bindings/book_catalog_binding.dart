import 'package:get/get.dart';

import '../controllers/book_catalog_controller.dart';

class BookCatalogBinding extends Bindings {
  @override
  void dependencies() {
    Get.lazyPut<BookCatalogController>(
      () => BookCatalogController(),
    );
  }
}
