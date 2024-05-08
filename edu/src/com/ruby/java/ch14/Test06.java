package com.ruby.java.ch14;

interface MyFunc<T> {
	T modify(T t);
}
//제네릭 인터페이스에 대한 이해가 필요함
public class Test06 {

	public static void main(String[] args) {

		MyFunc<String> mf1 = (str) -> str.toUpperCase() + ":" + str.length();
		System.out.println(mf1.modify("java"));					
		System.out.println(mf1.modify("java programming"));

		MyFunc<Integer> mf2 = (n) -> n * 2;
		System.out.println(mf2.modify(23));    //(23.0)을 쓰면 double이라 에러
		System.out.println(mf2.modify(42));
	}
}