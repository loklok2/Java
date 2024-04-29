package com.ruby.java.ch06;

public class Member {
	private String name;
	private int age;
	

	public Member() {
		this("guest"); //리팩토링
//		name = "guest"
//		age = 0;
	}

	public Member(String name) {
		this(name, 0);// 이함수는 Member(name, age)를 호출한다// 리팩토링
//		this.name = name;
//		age = 0; //여기 this 안하는 이유는 위에서 age가 로컬변수로 사용되지 않았기 때문이다
	}

	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String toString() { //object 에 있는 toString은 안쓰고, 내 toString 쓸꺼야
		return name + ":" +age;
	}

	public void setName(String name) {
	}

	public static void main(String[] args) {
		Member m1 = new Member();
		Member m2 = new Member("Amy");
		Member m3 = new Member("Amy", 23);
		System.out.println(m1.toString());
		System.out.println(m2.toString());
		System.out.println(m3.toString());
	}
}