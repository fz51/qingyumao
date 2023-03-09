import 'package:flutter/material.dart';
import 'package:qingyumao_app/datalib/document.dart';

import 'datalib/data_lib.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: {
        "/book/catalog_content": (context) {
          return const BookEditIndex();
        },
      },
      theme: ThemeData(
        useMaterial3: true,
        // brightness: Brightness.dark,
        primaryColor: Colors.lightBlue[800],
        textTheme: const TextTheme(
          displayLarge: TextStyle(fontSize: 72.0, fontWeight: FontWeight.bold),
          titleLarge: TextStyle(fontSize: 28.0),
          bodyMedium: TextStyle(fontSize: 14.0),
          titleSmall: TextStyle(fontSize: 24.0),
          //subtitle1: TextStyle(fontSize: 10.0),
        ),
      ),
      home: const DataLibPage(),
    );
  }
}
