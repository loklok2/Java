package com.ruby.java.ch11;


public class Test05 {
	
	
	int battery = 0;
	
	public void charge(int time) throws Exception {
		if(time < 0) {
			throw new NagativeNumberException();
	
		}

		
		battery += time;
		System.out.println("현재 배터리:" + battery);
	}
	
}	

