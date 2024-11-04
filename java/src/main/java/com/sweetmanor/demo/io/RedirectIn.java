package com.sweetmanor.demo.io;

import com.sweetmanor.common.Const;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 输入重定向示例
 *
 * @author wenz
 * @version 1.0 2016-06-15
 */
public class RedirectIn {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(Const.classPath + "log4j2.xml")) {
            System.setIn(fis);// 将标准输入重定向到fis输入流
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");// 只把回车作为分隔符
            while (sc.hasNext()) {
                System.out.print(sc.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
