package quiz;

import java.util.Random;
import java.util.Scanner;

public class MatrixCalc {
	private int[][] matrix = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
	};
	
    public void getMatirx( ) {
    	Scanner sc = new Scanner(System.in);
    	
    	int n = sc.nextInt();
    	int m = sc.nextInt();
    	matrix = new int[n][m];
    	
    	Random random = new Random();
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++ ) {
    			matrix[i][j] = random.nextInt(10);
    		}
    	}
    	for(int i = 0; i < n; i++) {
    		for(int j= 0; j < m; j++) {
    			matrix[i][j] = random.nextInt(10);
    		}
    	}
    	for(int i = 0; i < n; i++) {
    		for(int j= 0; j < m; j++) {
    			System.out.println(matrix[i][j] + "\t");
    		}
    		System.out.println();
    	}
    	
    	sc.close();
//	public void getMatrix( ) {
//		Scanner sc = new Scanner(System.in);
//		
//		int n = sc.nextInt();
//		int m = sc.nextInt();
//		matrix = new int[n][m];
//		
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				matrix[i][j] = (int)(Math.random()*10);
//			}
//		}
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				System.out.print(matrix[i][j]+"\t");
//			}
//			System.out.println();
//		}
//		
//		
//		sc.close();
//		
//	}
//	
//	public void work(boolean flag) {
//		if(flag) getMatrix();
//		for(int i = 0; i < matrix.length; i++) {
//			int sum = 0;
//			for(int j = 0; j < matrix[0].length; j++) {
//				sum = sum + matrix[i][j];
//			}
//			System.out.println(sum);
//		}
//		for(int i = 0; i < matrix.length; i++) {
//			int sum = 0;
//			for(int j = 0; j < matrix.length; j++) {
//				sum = sum + matrix[j][i];
//			}
//			System.out.println(sum);
//		}

	}
	

}
