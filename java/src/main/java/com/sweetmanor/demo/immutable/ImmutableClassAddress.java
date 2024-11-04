package com.sweetmanor.demo.immutable;

/**
 * 不可变类示例
 * <p>
 * 1) private final 修饰；<br>
 * 2) 构造器中初始化；<br>
 * 3) 只提供get方法。 <br>
 * 不可变类的Feild必须也是不可变类型，否则需要做保护
 *
 * @author ijlhjj
 * @version 1.0 2014-11-20
 */
public final class ImmutableClassAddress {
    private final String detail;// String类型是不可变类
    private final String postCode;

    // 无参构造器一般不提供，除非有系统默认值
    public ImmutableClassAddress() {
        this.detail = "";
        this.postCode = "";
    }

    public ImmutableClassAddress(String detail, String postCode) {
        this.detail = detail;
        this.postCode = postCode;
    }

    public String getDetail() {
        return detail;
    }

    public String getPostCode() {
        return postCode;
    }

}
