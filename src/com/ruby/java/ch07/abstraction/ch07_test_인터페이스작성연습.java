package com.ruby.java.ch07.abstraction;
/*
 * 7장 실습 코드로 구현한다 
 */

interface Shape {   // 인터페이스 Shape는 perimeter()와 area()메소드를 정의
	public double perimeter(); //둘레 반환
	public float area(); //면적 반환
}

class Triangle implements Shape { // Triangle 클래스는 shape 인터페이스를 구현중
	private int x1,y1,x2,y2,x3,y3; 
	//세점의 입력값을 받아 초기화
	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;	
	}
	@Override
	public double perimeter() { 
		double side1 = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		double side2 = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
	    double side3 = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
		return side1 + side2 + side3;
	}
	@Override
	public float area() { //0/5 * |(x1y2+x2y3+x3y1)-(x2y1+x3y2+x1y3)|
		float ss1 = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	    float ss2 = (float) Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
	    float ss3 = (float) Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
	    float s = (ss1 + ss2 + ss3) / 2.0f;
	    float area = (float) Math.sqrt(s * (s - ss1) * (s - ss2) * (s - ss3));
	    return area;
	}
	    //float은 4바이트, double은 8바이트 숫자의 범위차이 때문에 double이 더 정확할 거 같음

}
class Rectangle implements Shape { //Rectangle 클래스는 Shape 인터페이스를 구현
	int x1, y1, x2, y2, x3, y3, x4, y4;
	// 네점의 좌표값을 받아 초기화
	public Rectangle(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.x4 = x4;
		this.y4 = y4;				
	}
	@Override
	public double perimeter() { // = 1/2|x1y2+x2y4+x3y4+x4y1-(y1x2+y2x3+y3x4+y4x1)| 
		double side1 = Math.hypot(x2 - x1, y2 - y1);
		double side2 = Math.hypot(x3 - x2, y3 - y2);
		double side3 = Math.hypot(x4 - x3, y4 - y3);
		double side4 = Math.hypot(x1 - x4, y1 - y4);
		return side1 + side2 + side3 + side4;
	}

	@Override
	 public float area() {
        float area = 0;
        
        area += (x1 * y2 - y1 * x2); 
        area += (x2 * y3 - y2 * x3); 
        area += (x3 * y4 - y3 * x4);
        area += (x4 * y1 - y4 * x1); 
     
        area = Math.abs(area) / 2;
        
        return area;
	}   
}
class Square extends Rectangle { // Square는 Rextangle의 클래스를 상속받음
//	 Square는 정사격형이니까, Ractangle의 클래스를 상속받아 길이와 폭이 같음
	int side;
	public Square (int x1, int y1, int x2,int y2, int x3,int y3, int x4,int y4, int side){
		super(x1, y1, x2, y2, x3, y3, x4, y4);
		this.side = side;
	}
}
public class ch07_test_인터페이스작성연습 {
	public static void main(String[] args) {
	Shape t = new Triangle(1,2,3,4,5,6);
	Shape r = new Rectangle(1,2,3,2,1,5,3,5);
	Shape s = new Square(1,2,3,2,1,5,3,5,3);
	
	System.out.println("삼각형 둘레 길이 = " + t.perimeter());
	System.out.println("삼각형 면적 = " + t.area());
	System.out.println("사각형 둘레 길이 = " + r.perimeter());
	System.out.println("사각형 면적 = " + r.area());
	System.out.println("정사각형 둘레 길이 = " + s.perimeter());
	System.out.println("정사각형 면적 = " + s.area());
	}
}