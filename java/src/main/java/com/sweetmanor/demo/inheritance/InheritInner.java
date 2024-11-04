package com.sweetmanor.demo.inheritance;

/**
 * 继承内部类： <br />
 * <p>
 * 当继承内部类时，默认的构造器是行不通的，必须使用如下语法传递一个外围类对象的引用，并在构造器中调用外围类的构造器。
 *
 * @author ijlhjj
 * @version 1.0 2022-06-19
 */
public class InheritInner extends WithInner.Inner {
    InheritInner(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}

class WithInner {
    class Inner {
    }
}
