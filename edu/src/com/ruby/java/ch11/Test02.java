package com.ruby.java.ch11;

public class Test02 {
	public static void main(String[] args) {
		try {									//자동완성 기능 사용가능 ctrl+space
			System.out.println("1");
			String s = null;
			s.length();
			System.out.println("2");
			System.out.println("3");
		} catch(Exception e) {
			System.out.println("오류발생");       
		} finally {
			System.out.println("OK");
		}
		System.out.println("4");
	}	
}
