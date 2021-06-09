package com.sweetmanor.demo.immutable;

/**
 * 不可变类示例：不可变类包含可变Field时，需要做特殊处理
 * 
 * @version 1.0 2014-11-20
 * @author ijlhjj
 */
public class ImmutableClass_Person {
	private final Name name;

	// 不可变类在用到可变Field时需要做特殊处理
	public ImmutableClass_Person(Name name) {
		// 错误处理
		// this.name = name;
		// 正确处理
		this.name = new Name(name.getFirstName(), name.getLastName());
	}

	public Name getName() {
		// 错误处理
		// return name;
		// 正确处理
		return new Name(name.getFirstName(), name.getLastName());
	}

	public static void main(String[] args) {
		Name name = new Name("悟空", "孙");
		ImmutableClass_Person person = new ImmutableClass_Person(name);

		// 以下两种设置应该引用了不同的对象，如果在特殊处理时没有考虑，也可能引用同一个对象
		name.setFirstName("八戒");
		System.out.println(person.getName().getFirstName());

		person.getName().setFirstName("八戒");
		System.out.println(person.getName().getFirstName());
	}

}

/*
 * 可变类，做不可变类的可变Field
 */
class Name {
	private String firstName;
	private String lastName;

	public Name() {
	}

	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
