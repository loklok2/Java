package chapter5;

public class Armor {
////	필드선언
	String name = "John";
	int height = 185;
	int weight = 100;
	String color = "RED";
	boolean isFly = false;

	void setData(String n) {
		name = n;
		
		
	}

	void setName(String str) {
		name = str;
	}
	void setName2(String kkk) {
		name = kkk;
	}
	void setColor(String yellow) {
		color = yellow;
	}
	void namename(String what) {
		name = what;
	}
	void setWeight(int h) {
		weight = h;
	}
}
//	void setData(String n, int h, int w, String c) {
//		name = n;
//		height = h;
//		weight = w;
//		color = c;

////	boolean takeOff() {
//		System.out.println("takeOff 메서드가 호출되었습니다.");
//		
//		return isFly;	
//	}
//	