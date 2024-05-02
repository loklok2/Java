package com.ruby.java.ch09;

public class Test01 {

	public static void main(String[] args) {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();
		
		System.out.println(obj1.hashCode());
		System.out.println(obj2.hashCode());
		System.out.println(obj3.hashCode());
		
		Class c = obj1.getClass();
		
		System.out.println(c.getName());
		
//		MyObject obj4 = new MyObject(); 			
//		MyObject obj5 = new MyObject();
//		System.out.println(obj4);
//		if(obj4.equals(obj5)) {						//매서드 오버라이딩을 안해서,  Object의 equals매서드가 실행됨
//			System.out.println("동일객체이다");		//오버라이딩 안하니까 ==와 같이 작동함
//		} else {
//			System.out.println("다른객체이다");
//		}
//		
//		if(obj4 == obj5) {
//			System.out.println("동일객체이다");
//		} else {
//			System.out.println("다른객체이다");
//		}
		MyObject obj4 = new MyObject(123); 			
		MyObject obj5 = new MyObject(123);
		
		if(obj4.equals(obj5)) {						//매서드 오버라이딩을 안해서,  Object의 equals매서드가 실행됨
		System.out.println("동일객체이다");		//오버라이딩 안하니까 ==와 같이 작동함
			} else {
		System.out.println("다른객체이다");
		}
	
		if(obj4 == obj5) {
		System.out.println("동일객체이다");
		} else {
		System.out.println("다른객체이다");
		}
		
		
		
		
	}

}
