package com.ruby.java.ch07.abstraction;

public class IPhoneMessenger implements Messenger {
	public String getMessage() {
		return "IPhone";
	}
	public void setMessage(String msg) {
		System.out.println("iPhone에서 메세지를 설정합니다:" + msg);
	}
}
class IPhone15 extends IPhoneMessenger{ //extends가 상속
	
}
