package com.ruby.java.ch06;

//하나의 자바파일에는 하나의 public class만 존재할 수 있다
// 객체(object) = 속성,필드,행동,메서드 하나의 단위
// 클래스(class) = 객체를 찍어내는 틀
//인스턴스(instance) =  클래스를 통해 찍어낸 객체
public class Armor2 {
	private String name;
	private int height;

	public String getName() { //게터
		return name;
	}
	public void setName(String value) { //세터
		name = value;
	}
	public static void main(String[] args) {
//		메인에서 설정된 인스턴스는 하위코드에서 함께 사용
		Armor2 suit1 = new Armor2();
		suit1 = null;
		suit1.setName("mark");
		System.out.println(suit1.getName());
//		참조가 끊겼다.
	}

}
