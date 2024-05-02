package com.ruby.java.ch07.inheritance;

public class Person {
	private String name;  //protected는 서브 클래스만 접근가능하다
	private int age;		//클래스 밑에서 퍼블릭으로 설정하게되면 메인뿐만아니라 다른 곳에서도 접근이 가능하니 비추
	
	public Person() {
		System.out.println("Person 생성자 실행");
	}
		
	public Person(String name, int age) {
		this.name;
		this.age;
	}			
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return "[" + name + ", " + age + "]";  //ex) [홍길동, 10]
	}
}