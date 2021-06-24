package com.sweetmanor.demo.io.serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.sweetmanor.common.Const;
import com.sweetmanor.common.Person;

/**
 * 对象反序列化示例 <br />
 * 反序列化无需调用构造器即可生成对象。 <br />
 * 如果一个文件中写入了多个对象，反序列化时必须按写入顺序读取
 * 
 * @version 1.0 2016-06-18
 * @author wenz
 */
public class ObjectInputStreamDemo1 {

	public static void main(String[] args) throws Exception {
		try (FileInputStream fis = new FileInputStream(Const.classPath + "person.txt"); // 文件输入流
				ObjectInputStream ois = new ObjectInputStream(fis);// 定义对象输入处理流
		) {
			Person p1 = (Person) ois.readObject();// 读取Person对象
			Person p2 = (Person) ois.readObject();// 再次读取Person对象，其实第二个Person对象的属性已改变，但序列化时会忽略

			System.out.println(p1);// 没有序列化的字段将赋予默认值，此处性别字段为null
			System.out.println(p2);
			System.out.println(p1 == p2);// 验证多次序列化的是否同一个对象
		}
	}

}
