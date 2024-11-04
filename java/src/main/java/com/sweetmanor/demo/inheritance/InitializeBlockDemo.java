package com.sweetmanor.demo.inheritance;

/**
 * 初始化块调用流程示例： <br />
 * <p>
 * 从输出结构中可以清晰的看到初始化块的调用流程
 *
 * @author ijlhjj
 * @version 1.0 2014-11-04
 */
class Root {

    static {
        System.out.println("Root的静态初始化块");
    }

    {
        System.out.println("Root的普通初始化块");
    }

    public Root() {
        System.out.println("Root的无参构造器");
    }
}

class Mid extends Root {

    static {
        System.out.println("Mid的静态初始化块");
    }

    {
        System.out.println("Mid的普通初始化块");
    }

    public Mid() {
        System.out.println("Mid的无参构造器");
    }

    public Mid(String msg) {
        this();
        System.out.println("Mid的一个参数构造器，参数值：" + msg);
    }
}

class Leaf extends Mid {

    static {
        System.out.println("Leaf的静态初始化块");
    }

    {
        System.out.println("Leaf的普通初始化块");
    }

    public Leaf() {
        super("疯狂Java讲义");
        System.out.println("Leaf的无参构造器");
    }
}

public class InitializeBlockDemo {

    public static void main(String[] args) {
        new Leaf();
        System.out.println();
        new Leaf();
    }

}
