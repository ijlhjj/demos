package com.sweetmanor.demo.io.serializable;

import com.sweetmanor.common.Const;
import com.sweetmanor.common.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 对象反序列化示例 <br />
 * 反序列化无需调用构造器即可生成对象。 <br />
 * 如果一个文件中写入了多个对象，反序列化时必须按写入顺序读取
 *
 * @author wenz
 * @version 1.0 2016-06-18
 */
public class ObjectInputStreamDemo1 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        try (FileInputStream fis = new FileInputStream(Const.classPath + "person.txt"); // 文件输入流
             ObjectInputStream ois = new ObjectInputStream(fis);// 定义对象输入处理流
        ) {
            Person p1 = (Person) ois.readObject();// 读取Person对象
            Person p2 = (Person) ois.readObject();// 再次读取Person对象，其实第二个Person对象的属性已改变，但序列化时会忽略

            logger.info(p1);// 没有序列化的字段将赋予默认值，此处性别字段为null
            logger.info(p2);
            logger.info(p1 == p2);// 验证多次序列化的是否同一个对象
        }
    }

}
