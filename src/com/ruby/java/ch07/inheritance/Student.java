package com.ruby.java.ch07.inheritance;

public class Student extends Person{
//	private String name;
//	private int age;
	private String major;
	
	public Student() {
		super();
		System.out.println("Student 생성자 실행");
	}

//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getAge() {
//		return age;
//	}
//	public void setAge(int age) {
//		this.age = age;
//	}	
	
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	public String toString() {
//		return  getName() + "," + getAge() +","+ major; 
		return super.toString() + ","+ major; 
	}  
}