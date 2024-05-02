package quiz;

import java.util.Scanner;

public class PrimeNumber2 {
	private void getPrimeNum() {
		Scanner sc = new Scanner(System.in); //새로운 정수값 입력
		while(true) {							  				// 무한 반복시작 
			System.out.print("Number[0:exit]:");
			int num = sc.nextInt();						
			if(num == 0) break;									// 0을 입력하면 반복문을 멈추고
			
			int s = (int)Math.pow(10.0, (double)(num-1));		// 정수범위 계산 최소값 (10^(num-1)
			int e = (int)Math.pow(10.0, (double)(num))-1;		// 정수범위 계산 최대값 (10^num-1)
			
			for(int i = s; i <= e; i++) {		
				if(isPrime(i)) {					// i는 소수인 경우
					System.out.println(i);			// i를 출력
				}
			}
			
		}
		sc.close();
		System.out.println("Done!");
	}
	//num이 소수면 true, 그렇지 않으면 false를 리턴
	private static boolean isPrime(int n) {     //입력을 받아 소수판단 수행
		if(n <= 1) {			
			return false;						//n이 1이하이면 소수가 아니니까 false 반환
		}
		if(n == 2) {							
			return true;						//n이 2면 소수니까 true 반환
		}
		if(n % 2 == 0) {
			return false;						//n이 짝수면 소수가 아니니까 false 반환
		}
		for(int i = 3; i * i <= n; i += 2) {
			if(n % i == 0) {
				return false;					//n이 홀수인 경우면, 3부터 n의 제곱근까지 범위에서 홀수로
			}									// 나눠서 n이 약수가 있는지 보고 n이 i로 나누어 떨어진다면 
		}										// 소수가 아니므로 false반환
		return true;		//최종적으로 소수이면 true반환
		
	}
	
	public void work(boolean flag) {		// 메인 Program 클래스에서 work메서드가 true 플래그를 받았을때 
		if(flag) getPrimeNum();				// getPrimeNum메서드 호출하고 입력받고 소수판단 
		
	}	
	
}