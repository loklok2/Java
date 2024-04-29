package quiz;

import java.util.Scanner;

public class Quiz2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.println("숫자");
		int n = sc.nextInt();
			if(n==0)
					break;
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 1; i < n; i++ ) {
			if(i % 2 ==0)
				sum2 += i;
			else
				sum1 += i;
		}
		System.out.println("합:"+ (sum1+sum2));
		System.out.println("합1:"+ sum1);
		System.out.println("합2:"+ sum2);
	}
		System.out.println("종료");
}
}
