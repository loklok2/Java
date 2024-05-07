package quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MReader {
	//파일에서 데이터를 읽어서 2차원 배열로 ㅁ나들어주는 메서드
	private static int[][] loadMatrix(String fname) {
		int [][] ret = null;
		List<String> list = new ArrayList(); 
		String str;
		try(BufferedReader br = new BufferedReader(new FileReader(fname))) {
			while((str = br.readLine()) != null) {
				list.add(str);
			}				
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		for(int i = 0; i < list.size(); i++) {
			String[] arr = list.get(i).split(" ");
			if(ret == null) {
				ret = new int[list.size()][arr.length];								
			}
			for(int j = 0; j <arr.length; j++) {
				ret[i][j] = Integer.parseInt(arr[j]);
			}
		}
		return ret;
	}
	// 파라미터로 넘어온 2차원 배열을 출력하는 매서드
	private static void printMatrix(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j]+ "\t");
			}
			System.out.println();
		}
		System.out.println("-".repeat(30));
	}
	//2차원 배열 2개를 곱하는 메서드
	private static int[][] caclMatrix(int[][] a, int[][] b){	
//	    int rowsA = a.length;                        //행길이
//	    int colsA = a[0].length;					// 열길이
//	    int rowsB = b.length;						// 행길이
//	    int colsB = b[0].length;					// 열길이
//
//	    int[][] c = new int[rowsA][colsB];			//두 행렬의 초기화
		int[][]caclmatrix = new int[a.length][b[0].length];
		for (int i = 0; i <a.length; i++) {
			for(int j = 0; j < b.length; j++) {
				int c = 0;
				for(int k = 0; k < b.length; k++) {
					c += a[i][k] * b[k][j];
				}
				matrix[]
			}
		}
		return c;
//	    for (int i = 0; i < rowsA; i++) {				//i는 0이랑 같고, A의 열길이 보다 작다 
//	        for (int j = 0; j < colsB; j++) {
//	            c[i][j] = 0;
//	            for (int k = 0; k < colsA; k++) {
//	                c[i][j] += a[i][k] * b[k][j];
//	            }
//	        }
//	    }
//	    return c;
	}
	//메인메서드
	public static void main(String[] args) {
		int[][] a = loadMatrix("m2.txt");
		if(a == null) return;
		printMatrix(a);
		int[][] b = loadMatrix("m3.txt");
		if(b == null) return;
		printMatrix(b);
		
		
		int[][] c = caclMatrix(a, b);
		printMatrix(c);
	}	
}
