package com.ruby.java.ch06;

//하나의 자바파일에는 하나의 public class만 존재할 수 있다
// 객체(object) = 속성,필드,행동,메서드 하나의 단위
// 클래스(class) = 객체를 찍어내는 틀
//인스턴스(instance) =  클래스를 통해 찍어낸 객체
public class Armor {
	private String name;
	private int height;

	public String getName() { //게터
		return name;
	}
	public void setName(String value) { //세터
		name = value;
	}
	public static void main(String[] args) {
		Armor a = new Armor();
//	   (타입)(참조변수) =(heap 메모리에 객채생성) (생성자)
//		     (주소)=    (메모리필드공간할당)> (할당된주소로 반환)
		a.setName("Kim");
//		(참조변수)(의 해당되는 메서드 호출)
		System.out.println(a.getName());

	}

}

// JVM
// code heap stack
// 인스턴스에 객체와 메서드가 생성되지만, 이게 실행되는 시점에 메서드는 코드영역으로 이동하고 힙영역에는 객체만 남아있게됨
// stack 영역 > local(지역)변수를 저장
// 객체가 생성되기 위해서는 참조변수가 필요하다.222p 1,2,3 외우자