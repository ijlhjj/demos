package com.sweetmanor.demo.io.serializable;

import com.sweetmanor.common.Const;
import com.sweetmanor.common.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 对象序列化示例
 *
 * @author wenz
 * @version 1.0 2016-06-18
 */
public class ObjectOutputStreamDemo1 {

    public static void main(String[] args) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(Const.classPath + "person.txt"); // 文件输出流
             ObjectOutputStream oos = new ObjectOutputStream(fos);// 定义对象输出处理流
        ) {
            Person p = new Person("孙悟空", 500, "男");// 可序列化对象Person，其中性别是忽略字段
            oos.writeObject(p);// 把Person对象写入文件
            p.setName("猪八戒");// 改变Person对象的可变属性

            /*
             * 再次把Person对象写入磁盘。 Java序列化机制对此的处理是：只在第一次时把对象的字节数组进行序列化，
             * 之后的序列化将只写一个指向第一次序列化的地址码，在ObjectInputStreamDemo1中可看到效果
             */
            oos.writeObject(p);
        }
    }

}
