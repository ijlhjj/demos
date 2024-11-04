package com.sweetmanor.demo.java;

import java.io.Serial;

/**
 * 自定义异常类示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class MyExceptionDemo {
    public static void firstMethod() throws MyException {
        secondMethod();
    }

    public static void secondMethod() throws MyException {
        thirdMethod();
    }

    public static void thirdMethod() throws MyException {
        throw new MyException("自定义异常");
    }

    public static void main(String[] args) throws MyException {
        firstMethod();
    }
}

class MyException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    MyException() {
    }

    MyException(String msg) {
        super(msg);
    }
}
