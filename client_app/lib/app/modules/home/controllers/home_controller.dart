import 'package:get/get.dart';

class HomeController extends GetxController {
  final count = 0.obs;

  List<MainMenuModel> mainMenu = const [
    MainMenuModel("message", "消息"),
    MainMenuModel("doc", "文档"),
  ];

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

class MainMenuModel {
  final String key;
  final String name;

  const MainMenuModel(this.key, this.name);
}
