package quiz;

import java.util.Random;
import java.util.Scanner;

public class CaclMatrix {
    private int[][] matrix = {       //변수로 행렬을 나타냄
        {1, 2, 3},					//초기값으로 3*3 행렬으로 설정
        {4, 5, 6},
        {7, 8, 9}
    };
    private void Matrix() {					//메서드 
    	Scanner sc = new Scanner(System.in);	//Scanner를 통해서 M,N의 값을 새로 입력 받음
    	System.out.println("----".repeat(4));	
    	System.out.print("행 값 입력:");			
    	int M = sc.nextInt();
    	System.out.print("열 값 입력:");
    	int N = sc.nextInt();
    	System.out.println("----".repeat(4));
    	
    	matrix = new int[M][N];					//입력받은 크기로 matrix 크기 설정
    	Random random = new Random();			//Random 객체 사용
    													//for루프를 통해서 matrix 원소 순회
    	for(int i = 0; i < M; i++) {					//i는 행 인덱스로 0부터 M미만까지 증가 반복
    		for(int j = 0; j < N; j++) {				//j는 열 인덱스로 0부터 N미만까지 증가 반복			
    			matrix[i][j] = random.nextInt(9) + 1; 	//각 행렬을 1~9까지의 랜덤값으로 할당
    		}
    	}	
    	sc.close();
    }
    
    public void work(boolean flag) {
    	if(flag) getMatrix();								//flag가 true이면 getMatrix 호출
    														//flag가 false면 getMatrix 호출X, 기존꺼 사용
    	System.out.println("\n 랜덤 행렬:");
    	for(int i = 0; i < matrix.length; i++) {			// i가 0부터 matrix.length까지 반복
    		for(int j = 0; j < matrix[i].length; j++) {		// j가 0부터 matrix[i].length까지 반복
    			System.out.print(matrix[i][j] + "\t");		// 탭으로 구분하여 출력
    		}
    		System.out.println();							// 줄바꿈 용도로 사용
    	}
    	
    	int M = matrix.length;		
    	int N = matrix[0].length;
    	
    	int[] rowSum = new int[M];		//rowSum 배열 M으로 초기화
    	int[] colSum = new int[N];		//colSum 배열 N으로 초기화
    	
    	for(int i = 0; i < M; i++) {			// i는 0부터 M미만까지 증가 반복
    		for(int j = 0; j < N; j++) {		// j는 0부터 M미만까지 증가 반복
    			rowSum[i] += matrix[i][j];		// rowSum[i]에 matrix[i][j]의 값을 더하고 행의 합 계산해서 rowSum[i]에 누적함
    			colSum[j] += matrix[i][j];		// colSum[j]에 matrix[i][j]의 값을 더하고 행의 합 계산해서 colSum[j]에 누적함
    		}
    	}
    	
    	System.out.println("----".repeat(4));
    	System.out.println("\n 가로 값");
    	for(int i = 0; i < M; i++) {									// i가 0부터 M미만까지 증가 반복해서 rowSum[i] 값
    		System.out.println("합"+ (i + 1 ) + ":" + rowSum[i]);		//rowSum 배열의 각 행마다 합을 출력
    	}																
    	
    	System.out.println("----".repeat(4));							
    	System.out.println("\n 세로 값");
    	for(int j = 0; j < N; j++) {									//j가 0부터 N미만까지 증가 반복
    		System.out.println("합"+ (j + 1) + ":" + rowSum[j]);			//colSum 배열의 각 열마다 합을 출력
    	}
    	System.out.println("----".repeat(4));
    }

}