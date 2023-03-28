import 'package:flutter/material.dart';

import 'package:get/get.dart';

import '../controllers/book_catalog_controller.dart';

class TreeNode {
  final String label;
  final List<TreeNode> children;

  TreeNode(this.label, [this.children = const <TreeNode>[]]);
}

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
      body: PageView(
        children: [
          Container(
            child: Text("页面1"),
          ),
          Container(
            child: Text("页面2"),
          )
        ],
      ),
    );
  }
}

class BookCatalog extends GetView<BookCatalogController> {
  const BookCatalog({Key? key}) : super(key: key);



  @override
  Widget build(BuildContext context) {
    final  TreeNode node = TreeNode('root', [
      TreeNode('child1', [
        TreeNode('grandchild1'),
        TreeNode('grandchild2'),
      ]),
      TreeNode('child2'),
    ]);
    return Container(
      child: TreeView(root: node),
    );
  }
}

class TreeView extends StatefulWidget {
  final TreeNode root;

  TreeView({required this.root});

  @override
  _TreeViewState createState() => _TreeViewState();
}

class _TreeViewState extends State<TreeView> {
  bool _expanded = false;

  void _toggle() {
    setState(() {
      _expanded = !_expanded;
    });
  }

  Widget _buildTree(TreeNode node) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        GestureDetector(
          onTap: () {
            if (node.children.isNotEmpty) {
              _toggle();
            }
          },
          child: Row(
            children: [
              node.children.isNotEmpty
                  ? Icon(
                      _expanded ? Icons.expand_more : Icons.arrow_right,
                      size: 20.0,
                    )
                  : SizedBox(width: 20.0),
              Text(node.label),
            ],
          ),
        ),
        if (_expanded)
          ...node.children.map((child) => Padding(
                padding: const EdgeInsets.only(left: 16.0),
                child: _buildTree(child),
              )),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return _buildTree(widget.root);
  }
}
