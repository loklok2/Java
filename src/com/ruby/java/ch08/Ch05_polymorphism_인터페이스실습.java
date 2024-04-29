package com.ruby.java.ch08;

interface Shape {
	public double perimeter();     //필드선언
	public float area();
	@Override
	String toString();
}

class Triangle implements Shape {
	private int x1,y1,x2,y2,x3,y3;
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
}
class Rectangle implements Shape {
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
class Square extends Rectangle {
	int side;
	public Square (int x1, int y1, int x2,int y2, int x3,int y3, int x4,int y4, int side){
		super(x1, y1, x2, y2, x3, y3, x4, y4);
		this.side = side;
	}
}
class Pentagon implements Shape {
	private int x1,y1,x2,y2,x3,y3,x4,y4,x5,y5;
	public Pentagon(int x1, int y1, int x2,int y2, int x3, int y3, int x4, int y4, int x5,int y5){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.x4 = x4;
		this.y4 = y4;
		this.x5 = x5;
		this.y5 = y5;
	}
	public double perimeter() {
		double side1 = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		double side2 = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
		double side3 = Math.sqrt((x4 - x3) * (x4 - x3) + (y4 - y3) * (y4 - y3));
		double side4 = Math.sqrt((x5 - x4) * (x5 - x4) + (y5 - y4) * (y5 - y4));
		double side5 = Math.sqrt((x1 - x5) * (x1 - x5) + (y1 - y5) * (y1 - y5));
		return side1 + side2 + side3 + side4 + side5;
	}
	public float area() {
        float area = 0;
        
        area += (x1 * y2 - y1 * x2); 
        area += (x2 * y3 - y2 * x3); 
        area += (x3 * y4 - y3 * x4);
        area += (x4 * y5 - y4 * x5);
        area += (x5 * y1 - y5 * x1);
     
        area = Math.abs(area) / 2;
        
        return area;
	}   
}

public class Ch05_polymorphism_인터페이스실습 {
	public static void main(String[] args) {
	Shape[]arr = new Shape[4];
	arr[0] = new Triangle(1,2,3,4,5,6);
	arr[1] = new Rectangle(1,2,3,2, 1,5,3,5);
	arr[2] = new Square(1,2,3,2,1,4,3,4,2);
	arr[3] = new Pentagon(5,1,8,1,3,5,10,5,6,12);
	for(Object p : arr) {
		System.out.println(p.toString());
	}	
	System.out.println("삼각형 둘레 길이 = " + arr[0]);
	System.out.println("삼각형 면적 = " + arr[0]);
	System.out.println("사각형 둘레 길이 = " + arr[1]);
	System.out.println("사각형 면적 = " + arr[1]);
	System.out.println("정사각형 둘레 길이 = " + arr[2]);
	System.out.println("정사각형 면적 = " + arr[2]);
	System.out.println("오각형 둘레 길이 = " + arr[3]);
	System.out.println("오각형 면적 = " + arr[3]);
	}	
}

