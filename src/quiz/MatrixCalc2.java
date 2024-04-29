package quiz;

import java.util.Scanner;

public class MatrixCalc2{
	private int[][] matrix = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
	};
	private void getMatrix() {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		matrix = new int [n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.println(matrix[i][j]);
			}
		}
		
		
		
		
		
		
		
		sc.close();
	}
	
}
