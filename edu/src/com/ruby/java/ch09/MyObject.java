package com.ruby.java.ch09;

public class MyObject {
//	public String toString(){
//		return "MyObject";
		
		int num;
		
		MyObject(int num){
			this.num = num;
		}
		
		public String toString() {
			return "MyObject";
		}
		
		public boolean equals(Object obj) {
			boolean result = false;
			MyObject arg = (MyObject) obj;
			return result;
		}
	
}
