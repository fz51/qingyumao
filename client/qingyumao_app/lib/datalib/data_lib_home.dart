import 'package:flutter/material.dart';

/// 首页
class DataLibHome extends StatefulWidget {
  const DataLibHome({Key? key}) : super(key: key);

  @override
  State<DataLibHome> createState() => _DataLibHomeState();
}

class _DataLibHomeState extends State<DataLibHome> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(left: 25.0, right: 25.0),
      child: Column(
        children: [
          const SizedBox(
            height: 20,
          ),
          Row(
            children: const [
              CardButton(
                title: "新建文档",
                subtitle: "文档、表格、画板、数据表",
                iconData: Icons.book,
              ),
              CardButton(
                title: "新建知识库",
                subtitle: "使用知识库整理知识",
                iconData: Icons.library_books,
              ),
              CardButton(
                title: "模版中心",
                subtitle: "从模版中获取灵感",
                iconData: Icons.temple_buddhist_outlined,
              ),
            ],
          ),
          SizedBox(
            height: 20,
            child: Text("最近", style: Theme.of(context).textTheme.bodyLarge),
          ),
          Expanded(
            child: Scrollbar(
              child: SingleChildScrollView(
                child: DataTable(
                  columns: const <DataColumn>[
                    DataColumn(
                        label: SizedBox(width: 300,child: SizedBox(),)),
                    DataColumn(
                      label: SizedBox(
                        width: 30,
                      ),
                    ),
                    DataColumn(
                      label: SizedBox(
                        width: 20,
                      ),
                    ),
                  ],
                  rows: List<DataRow>.generate(
                    100,
                    (index) => DataRow(
                      cells: <DataCell>[
                        DataCell(
                          Text(
                            'Row $index',
                          ),
                          onTap: () {
                            Navigator.pushNamed(context, "/book/catalog_content");
                          },
                        ),
                        const DataCell(
                          Text('fangzhi/工作备忘'),
                          placeholder: true,
                        ),
                        const DataCell(
                          Text('2012-10-20'),
                          placeholder: true,
                        )
                      ],
                    ),
                  ),
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}

class CardButton extends StatelessWidget {
  final String title;
  final String subtitle;

  final IconData iconData;

  const CardButton(
      {Key? key,
      required this.title,
      required this.subtitle,
      required this.iconData})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        side: BorderSide(
          color: Theme.of(context).colorScheme.secondary,
        ),
        borderRadius: const BorderRadius.all(Radius.circular(12)),
      ),
      child: SizedBox(
        width: 260,
        child: ListTile(
          leading: Icon(iconData),
          title: Text(title),
          subtitle: Text(
            subtitle,
            style: TextStyle(color: Colors.grey.withOpacity(0.8)),
          ),
        ),
      ),
    );
  }
}
