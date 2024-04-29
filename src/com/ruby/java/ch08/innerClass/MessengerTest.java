package com.ruby.java.ch08.innerClass;

public class MessengerTest {
	public static void main(String[] args) {
		
		Messenger test = new Messenger() {

			@Override
			public String getMassage() {
				return "test";
			}

			@Override
			public void setMassage(String msg) {
				System.out.println("test에서 메세지를 설정합니다:" + msg);
				
			}
			
			
		};
			
		System.out.println(test.getMassage());
		test.setMassage("have a niceday!");
			
	}
}
