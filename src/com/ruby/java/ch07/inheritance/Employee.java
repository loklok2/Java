package com.ruby.java.ch07.inheritance;

public class Employee extends Person{ //extends가 상속을 표현
	//private String name;
	//private int age;
//	private String dept;
	public Employee() {
		super();
		System.out.println("Employee 생성자 실행");
	}//default 생성자 자동생성 즉, 메모리영역에 생성은 되나 값이 0이거나 null
	public Employee(String name, int age, String dept) {
//		this.name = name;
//		this.age = age;
		super(name, age);//보무
		this.dept = dept;
		System.out.println("Employee(name, age, dept) 생성자 실행"); 
	}
//	public String getName() {
//		return name;
//	}
//	
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String toString() {
//		return  getName() + "," + getAge() +","+ dept;  
		return super.toString() + ","+dept;
	}
}