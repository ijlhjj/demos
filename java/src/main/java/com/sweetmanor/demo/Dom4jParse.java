package com.sweetmanor.demo;

import com.sweetmanor.common.Const;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Dom4j示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class Dom4jParse {
    private static final Logger logger = LogManager.getLogger();

    // 解析元素
    public static void parse(Element ele) {
        parseAttribute(ele); // 处理当前元素的属性
        List<Element> el = ele.elements(); // 获取所有子元素
        for (Element element : el) {
            if (!element.isTextOnly()) { // 如果该元素内容不是只有字符串，递归调用
                parse(element);
            } else {
                parseAttribute(element);
                logger.info("{} --> {}", element.getQualifiedName(), element.getText());
            }
        }
    }

    // 解析元素的属性
    public static void parseAttribute(Element ele) {
        List<Attribute> attList = ele.attributes(); // 获取所有属性
        for (Attribute attr : attList) {
            logger.info("{} 元素的 {} 属性值为：{}", ele.getQualifiedName(), attr.getQualifiedName(), attr.getValue());
        }
    }

    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(Const.classPath, "log4j2.xml"));
        Element root = document.getRootElement();
        parse(root);
    }

}
