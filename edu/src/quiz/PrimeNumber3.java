package quiz;

import java.util.Scanner;

public class PrimeNumber3 {
    private boolean isPrime(int n) {  // num 입력값은 정수임
        if (n < 2) {					// 소수이면 true, 아니면 false
            return false;				// 2미만은 소수가 아니니깐 false 반환
        }
        for (int i = 2; i * i <= n; i++) {	//2부터 num의 제곱근까지 반복해서 소수인지 판단
            if (n % i == 0) {					//num을 i로 나눌때 나머지가 0이면 소수 아님
                return false; 					// 약수가 존재하면 false 반환
            }
        }
        return true; 							// 약수가 없으면 소수니까 true 반환
    }

    													// 트런트케이트소수 > 오른쪽부터 자릿수를 하나씩 제거해도 계속 소수로 남는 소수
    private boolean isTruncatablePrime(int n) {		// 주어진 정수가 트런케이티드 소수인지 확인합니다.
        String nStr = String.valueOf(n);			// 입력값은 num 정수
        int length = nStr.length();					

        																
        for (int i = 0; i < length; i++) {								// 왼쪽에서 오른쪽으로 잘라가면서 확인
            int truncatedNum = Integer.parseInt(nStr.substring(i));	// num의 부분 수를 정수로 변환
            if (!isPrime(truncatedNum)) {
                return false; 											// 왼쪽에서 오른쪽으로 잘라낸 부분이 소수가 아니니깐 false 반환
            }
        }

        																	
        for (int i = length; i > 0; i--) {									// 오른쪽에서 왼쪽으로 잘라가면서 확인
            int truncatedNum = Integer.parseInt(nStr.substring(0, i));	// num의 부분 수를 정수로 반환
            if (!isPrime(truncatedNum)) {									// 부분 수가 소수가 아니면 false 반환
                return false; 
            }
        }
//      private static boolean isPrimeAll(int num) {
//    	  int i = 1;
//    	  String s = String.valueOf(num);
//    	  while(true) {
//    		  if(!isPrime(Integer.parseInt(s.substring(0, i)))) {
//    		  return false;
//    	  }
//    	  
//    	  if(s.length() < i) {
//    		  break;
//    	  }
    	  
     

        return true;    							// 모든 잘라낸 부분이 소수라면 true 반환
    }

    										
    private void getPrimeNum() {								// 주어진 자릿수에 해당하는 소수 출력하기
        Scanner sc = new Scanner(System.in);					// 새 정수값 받기
        System.out.print("입력 자릿수: ");
        int digit = sc.nextInt();

        int start = (int) Math.pow(10, digit - 1);				// 입력받은 정수에 해당하는 범위 설정
        int end = (int) Math.pow(10, digit) - 1;

        System.out.println("입력 자릿수의 소수 목록: ");				
        for (int n = start; n <= end; n++) {				//각 숫자에 대한 소수 판단
            if (isPrime(n)) {
                System.out.println(n);
            }
        }
        sc.close();
    }
 
    public void work(boolean flag) {
        if(flag) getPrimeNum();
    }
}