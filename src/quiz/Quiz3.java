package quiz;

import java.util.Scanner;

public class Quiz3 {
	
//	static int r = 5;
//	static int h = 10;
	
	
	static double r = 5.5;
	static double h = 10.5;
	
	static double getVolume() {
		return Math.PI * r * r * h;
	}
	
	static double getArea() {
		return Math.PI * r * r * 2+(2*Math.PI*r) * h;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("반지름[0:exit]:"); r = sc.nextInt();
			if( r == 0)
				break;
			System.out.println("높이:"); h = sc.nextInt();
			
			System.out.println("Volume :"+ getVolume());
			System.out.println("Area: " + getArea());	
		}
		System.out.println("Done");
		sc.close();
//		System.out.println("반지름:"); r = sc.nextInt();
//		System.out.println("높이"); h = sc.nextInt();
//		
//		System.out.println("Volume"+ getVolume());
//		System.out.println("Volume"+ getArea());
	}
}
