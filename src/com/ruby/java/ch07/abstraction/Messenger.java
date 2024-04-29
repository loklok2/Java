package com.ruby.java.ch07.abstraction;

public interface Messenger {
	//field 공부만                                     //static 변수 객체가 없어도 값이 존재
	int MIN_SIZE = 1;								//static 메소드 변수 객체가 없어도 값이 존재, 호출
	int MAX_SIZE = 104857600;
	//Method 추상메소드
	public abstract String getMessage();

	public abstract void setMessage(String msg);			//인터페이스 단일상속, 다중상속
	//default 메소드
	public default void setLogin(boolean login) {
		log();
		if (login) {
			System.out.println("�α��� ó���մϴ�.");
		} else {
			System.out.println("�α׾ƿ� ó���մϴ�");
		}
	}
	//static 메소드
	public static void getConnection() {
		System.out.println("network�� �����մϴ�.");
	}

	private void log() {
		System.out.println("start job!");
	}
}