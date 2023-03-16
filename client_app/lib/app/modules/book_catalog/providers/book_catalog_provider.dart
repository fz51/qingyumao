import 'package:get/get.dart';

import '../book_catalog_model.dart';

class BookCatalogProvider extends GetConnect {
  @override
  void onInit() {
    httpClient.defaultDecoder = (map) {
      if (map is Map<String, dynamic>) return BookCatalog.fromJson(map);
      if (map is List)
        return map.map((item) => BookCatalog.fromJson(item)).toList();
    };
    httpClient.baseUrl = 'YOUR-API-URL';
  }

  Future<BookCatalog?> getBookCatalog(int id) async {
    final response = await get('bookcatalog/$id');
    return response.body;
  }

  Future<Response<BookCatalog>> postBookCatalog(
          BookCatalog bookcatalog) async =>
      await post('bookcatalog', bookcatalog);

  Future<Response> deleteBookCatalog(int id) async =>
      await delete('bookcatalog/$id');
}
