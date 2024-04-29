package com.ruby.java.ch06;
class Student{
	String name; //default선언 > 현 자바파일에서는 접근가능, 외부에서는 private
	private int age;
	private String getName() {
		return name;
	}
	public Student() {} //java compiler가 자동으로 추가 해줌
	public Student(String name) {
		this.name = name;
	}
}
public class ThisTest2 {
	private String name;

	public void setName(String name) {
		this.name = name; 
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) {
		Student s = new Student("lok");
		s.age = 11;
		s.getName();
		ThisTest2 exam = new ThisTest2();
		exam.setName("Amy");
		
		System.out.println(exam.getName());
	}

}