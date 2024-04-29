package com.ruby.java.ch07.abstraction;

public class MessengerTest {
	public class MessengerTest {
		public static void main(String[] args) {
			
			IPhoneMessenger iphone = new IPhoneMessenger();
			iphone.getMessage(); //같은 인터페이스 사용
			GalaxyMessenger galaxy = new GalaxyMessenger();
			galaxy.getMessage(); //같은 인터페이스 사용
		}
	}
}
