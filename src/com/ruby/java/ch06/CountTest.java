package com.ruby.java.ch06;
class Count{
	public static int totalCount; // 클래스의 값을 말하는거
//	num은 heap영역에 객체인스턴스로 되어 필드로 저장되어 있지만,
//  static으로 선언된 것으로 값을 공유함
	int num;	 //인스턴스 값을 말하는거 //int앞에 아무것도 없고 선언이 되면  default로 적용
	public void print() {                  //객체가 있어야 사용가능한 인스턴스
		System.out.println("num=" + num);
	}
	public static void print2() {          //객체가 없어도 사용가능한 클래스
		System.out.println("totalCount=" + totalCount);
	}
	public Count() { // 자바 컴파일러가 자동 생성
		num = 1;
		totalCount++;
	}
	int getter() {
		return num;
	}
	void setter(int n) {
		num++;
	}
}
//다시 강조점. public class는 하나이며, 곧 파일명.class임
public class CountTest {
	public static void main(String[] args) {
		System.out.println(Count.totalCount);
		Count.print2();
//		Count.print();
//		System.out.println(Count.num);//num은 일반변수
		Count c1 = new Count();
//				new(heap 메모리할당>할당된 공간의주소를 반환	  Count()(생성자. 클래스이름)
		Count c2 = new Count();
		Count c3 = new Count();
		c1.print2();
		c1.print();
		c1.getter();
		c1.setter(1);
		c1.num++;
		System.out.println(c3.totalCount);
		System.out.println(c1.totalCount);
		System.out.println(Count.totalCount);
		
	}

}
