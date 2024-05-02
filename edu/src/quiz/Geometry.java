package quiz;   //값은 알아서 해라

import java.util.List;

abstract class Geometry{
//	int[] xArr;
//	int[] yArr;
	List<Integer> xArr;
	List<Integer> yArr;
	public abstract double getArea();
	public abstract double getLength();
}

class Point extends Geometry {
	public Point(int x, int y) {
		xArr.add(x);
		yArr.add(y);
	}
	@Override
	public double getArea() {
		return 0.0;							// 점의 면적은 0
	}

	@Override
	public double getLength() {				// 점의 길이는 0
		
		return 0.0;
	}
	public String toStirng() {
		return "[" + xArr.get(0) + " , " + yArr.get(0) + "]";
	}
class Line extends Geometry {
	public Line(int x1, int x2, int y1, int y2) {
		xArr.add(x1);									//xArr 배열의 첫번째 좌표 x1과 두번쨰 x2 추가
		xArr.add(x2);
		yArr.add(y1);									//ㅛArr 배열의 첫번째 좌표 y1과 두번쨰 y2 추가
		yArr.add(y2);
	}
	public double getArea() {
		return 0.0; 							//직선거리의 면적은 0
	}
	@Override
	public double getLength() {				
		return Line.length(xArr.get(0),yArr.get(0), xArr.get(1),yArr.get(1));
	}
	static double length(int x1, int x2, int y1, int y2) {
		// TODO Auto-generated method stub
		return 0.0;
	}
	
}
class Polyline extends Geometry {
	public Polyline(int...arr) { 											//새로운 인자 값 생성
		for(int i = 0; i < arr.length/2; i++) {								//i는 0, i arr의 문자길이의 반보다 작고, i++
			xArr.add(arr[2*i]);												//arr의 배열에 짝수 인덱스를 xArr배열에 추가
			yArr.add(arr[2*i+1]);											//arr의 배열에 홀수 인덱스를 yArr배열에 추가
		}
	}
	@Override
	public double getArea() {				//선형 구조라 면적 0
		return 0;
	}
	@Override
	public double getLength() {
		double ret = 0.0;									//변수값 초기화
		for (int i = 0; i < xArr.size(); i++) {
			ret += Line.length(xArr.get(i), yArr.get(i), xArr.get(i+1), yArr.get(i+1));   //ret 변수에 총길이 저장 
		}
		return ret;
	}	
}
class TriAngle extends Geometry {
	public TriAngle(int x1, int x2, int y1, int y2, int x3, int y3) {
		xArr.add(x1);
		xArr.add(x2);
		yArr.add(y1);
		yArr.add(y2);
		xArr.add(x3);
		yArr.add(y3);
	}
	@Override
	public double getArea() {
		double ss1 = Math.sqrt(Math.pow(xArr.get(1) - xArr.get(0), 2) + Math.pow(yArr.get(1) - yArr.get(0), 2));
	    double ss2 = Math.sqrt(Math.pow(xArr.get(2) - xArr.get(1), 2) + Math.pow(yArr.get(2) - yArr.get(1), 2));
	    double ss3 = Math.sqrt(Math.pow(xArr.get(0) - xArr.get(2), 2) + Math.pow(yArr.get(0) - yArr.get(2), 2));
	    double s = (ss1 + ss2 + ss3) / 2;
	    return Math.sqrt(s * (s - ss1) * (s - ss2) * (s - ss3));
	}
	@Override
	public double getLength() {
		double ss1 = Math.sqrt(Math.pow(xArr.get(2) - xArr.get(1) *2, 2));
		double ss2 = Math.sqrt(Math.pow(xArr.get(2) - xArr.get(1) *2, 2));
		double ss3 = Math.sqrt(Math.pow(xArr.get(2) - xArr.get(1) *2, 2));
		
		return 0;
	}
}

class Rectangle extends Geometry {

	public Rectangle(int...arr) {
		for(int i = 0; i < arr.length/2; i++) {								//i는 0, i arr의 문자길이의 반보다 작고, i++
			xArr.add(arr[2*i]);												//arr의 배열에 짝수 인덱스를 xArr배열에 추가
			yArr.add(arr[2*i+1]);
		}	
	}
	@Override
	public double getArea() {
		double ret = 0.0;
		for (int i = 0; i < xArr.size(); i++) {
			ret += Line.length(xArr.get(i), yArr.get(i), xArr.get(i+1), yArr.get(i+1),xArr.get(i+2), yArr.get(i+2),xArr.get(i+3), yArr.get(i+3) );   //ret 변수에 총길이 저장 
		}
		return ret;
	}

	@Override
	public double getLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

}
