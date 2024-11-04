package com.sweetmanor.demo.inheritance;

/**
 * 父类构造器调用子类重写方法报错示例： <br />
 *
 * <pre>
 *     初始化子类时的调用流程：
 *         1、先调用子类构造器；
 *         2、子类构造器先调用父类构造器实例化父类；
 *         3、然后初始化子类属性域；（是设置初始值，不是分配内存空间。此处的属性设置初始值和初始化块是处于同一个优先级的，谁在前谁先执行）
 *         4、最后执行子类构造器
 * </pre>
 *
 * @author ijlhjj
 * @version 1.0 2014-11-04
 */
class Base {
    public Base() {
        test();// 子类重写此方法后，初始化子类将调用子类的方法
    }

    public void test() {
        System.out.println("父类的test方法");
    }
}

class Sub extends Base {
    private String name = "Java";

    public void test() {
        System.out.println("子类的test方法");
        System.out.println("name长度为：" + name.length());// 此时的name属性还没有初始化，将报空指针异常
    }
}

public class InheritanceIssueDemo1 {

    public static void main(String[] args) {
        new Base();
        new Sub();
    }

}
