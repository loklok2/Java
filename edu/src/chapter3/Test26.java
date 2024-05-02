package chapter3;

import java.util.Scanner;

public class Test26 {
	public static void main(String[] args) {
		//while
		// 제어변수선언초기화
		// while(조건식)
		// 출력
		// 증감식
		Scanner sc = new Scanner(System.in);
		//스캐너로 새로 값을 받을 때에 반복횟수를 재지정하고
		// 새로운 변수값이 기존 조건변수에 포함
		int i = 0;
		System.out.print("반복횟수:");
		int k = sc.nextInt();
			
		while(i<k) {
			if(i%2==0)
			System.out.println(i);
			i++;
		}
		System.out.println("OK");
	}
}
