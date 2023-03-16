class BookCatalog {
  String? id;
  String? name;
  List<BookCatalog>? children;

  BookCatalog({required this.id, required this.name, this.children});

  BookCatalog.fromJson(Map<String, dynamic> json) {
    id = json['id']!;
    name = json['name']!;
    children = json['children'].cast<BookCatalog>();
  }

  Map<String, dynamic> toJson() {
    final data = <String, dynamic>{};
    data['id'] = id;
    data['name'] = name;
    data['children'] = children;
    return data;
  }
}
