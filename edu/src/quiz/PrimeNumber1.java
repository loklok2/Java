package quiz;

import java.util.Scanner;

public class PrimeNumber1 {
	private void getPrimeNum() {
		Scanner sc = new Scanner(System.in); //새로운 값 입력
		while(true) {							  				// 무한 반복시작 
			System.out.print("Number[0:exit]:");
			int num = sc.nextInt();						
			if(num == 0) break;									// 0을 입력하면 반복문을 멈추고
			if(isPrime(num) == true) {							// 0이 아니면 isPrime(num)메서드 호출
				System.out.println(num + " is a Prime. ");		// 소수값을 입력하면 출력	
			}else {
				System.out.println(num + " is not a Prime. ");	// 소수값이 아닌경우 출력
			}													
			
		}
		sc.close();
		System.out.println("Done!");
	}
	//num이 소수면 true, 그렇지 않으면 false를 리턴
	private static boolean isPrime(int num) {     //입력을 받아 소수판단 수행
		if(num < 2) {
			return false;					// 2미만일 경우 소수가 아니니깐 false반환
		}
		if(num == 2) {
			return true;					// 2 면 소수니까 ture 반환
		}
		if(num % 2 == 0) {			
			return false;					// 짝수면 false 반환 
		}
		for(int i = 3; i * i <= num; i += 2) {
			if(num % i == 0) {
				return false;				// 3부터 num의 제곱근까지 홀수로 num을 나눔
			}								// num을 나눠서 나머지가 0이면 소수가 아니니깐 false 반환
		}									// num이 3부터 num의 제곱근까지 홀수로 나눠서 안떨어지면 ture 반환
		
		return true;						// 모든 판단을 통과하면 num이 소수니까 true 반환
		
	}
	
	public void work(boolean flag) {		// 메인 Program 클래스에서 work메서드가 true 플래그를 받았을때 
		if(flag) getPrimeNum();				// getPrimeNum메서드 호출하고 입력받고 소수판단 
		
	}	
	
}