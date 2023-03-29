package cn.qingyumao.helper.generate;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.XmlUtil;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.xpath.XPathConstants;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 模块生成
 */
@ShellComponent("模块生成器")
public class ModuleGenerate {
    /**
     * /Users/fangzhi/Dev/qingyumao/github/qingyumao/server/modules
     *
     * @param a
     * @param b
     */
    @ShellMethod(value = "创建模块", key = {"sum", "addition"})
    public void add(int a, int b) {
        int sum = a + b;
        System.out.println(String.format("%d + %d = %d", a, b, sum));
    }

    public Set<String> findExistedModules(File file) {
        if (FileUtil.exist(file)) {
            final File pomFile = FileUtil.file(file.getPath() + File.separator + "pom.xml");
            if (FileUtil.exist(pomFile)) {
                final Document document = XmlUtil.readXML(pomFile);
                final Element rootElement = XmlUtil.getRootElement(document);
                final Object modulesElement = XmlUtil.getByXPath("//project/modules/module", document, XPathConstants.STRING);
                if (modulesElement != null) {
                    /*final List<Element> module = XmlUtil.getElements(modulesElement, "module");
                    for (Element element : module) {
                        System.out.println(element.getTextContent());
                    }*/
                }
            }
        }
        return Collections.emptySet();
    }

    public static void main(String[] args) {
        final ModuleGenerate moduleGenerate = new ModuleGenerate();
        moduleGenerate.findExistedModules(FileUtil.file("/Users/fangzhi/Dev/qingyumao/github/qingyumao/server/modules"));
    }
}
