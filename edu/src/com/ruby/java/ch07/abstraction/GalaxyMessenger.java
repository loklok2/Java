package com.ruby.java.ch07.abstraction;

public class GalaxyMessenger implements Messenger, WorkFile {
	public String getMessage() {
		return "galaxy";
	}
	public void setMessage(String msg) {
		System.out.println("Galaxy에서 메세지를 설정합니다:" + msg);
	}
	@Override
	public void fileUpload() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fileDownload() {
		// TODO Auto-generated method stub
		
	}

}
