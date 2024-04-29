package com.ruby.java.ch06;

public class StaticMethodTest {
	int num = 123;//>인스턴스 필드만 생성됨>인스턴드 객체 생성후 사용 가능
	static int count = 0 ;
	public static void main(String[] args) {
		StaticMethodTest.print1();
		StaticMethodTest smt = new StaticMethodTest();
		smt.print2();//객체 생성후 사용됨
//		StaticMethodTest.print2();

	}
	
	public static void print1() { //클래스 메서드
		int num2 = num;//객체가 생성되지 않았는데 사용됨
		System.out.println("hello");
	}
	
	public void print2() { //인스턴스 메서드
		int num2 = num;//인스턴스 메서드는 반드시 객체 생성 후 사용되기 때문에 가능
		System.out.println("java");
	}
}
