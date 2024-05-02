package chapter3;

import java.util.Scanner;

public class Test20 {

	public static void main(String[] args) {
		//표준입력 System.in 표준출력 System.out
		Scanner sc = new Scanner(System.in);
		
		System.out.print("점수를 입력하세요");
		//기본 점수 설정
		//int score =90;
		int score = sc.nextInt();
		// 식별 등급부여
		char grade;
		
		// 점수별 식별등급 설정 5가지
		if(score >= 90) {
			grade = 'A';
		} else if(score >= 80) {
			grade = 'B';
		} else if(score >= 70) {
			grade = 'C';
		} else if(score >= 60 ) {
			grade = 'D';
		} else {
			grade = 'F';
		}
		System.out.println(grade);
	}

}
