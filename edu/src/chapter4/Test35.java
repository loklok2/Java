package chapter4;

public class Test35 {

	public static void main(String[] args) {
		int arr[][] = new int[5][5];
		for (int i = 0; i<arr.length; i++)
			for (int j = 0; j<arr.length; j++) 
		{
				arr[i][j] = i * 5 + j + 1;
		}
		for (int i = 0; i<arr.length; i++) {
			for (int j = 0; j<arr.length; j++) {
				System.out.print(arr[i][j]+ " ");
			}
			System.out.println();
		}	
	}
}	
		
		
//		new int [3][4]
//		         행 열
//		int[][]b=
//		       b는 참조변수
//		matrix(행렬), row(행), column(열)
//		즉 3개의 row와 4개의 column로 저장
//		int[3]부터 해석 다음[4] 해석
		
//		int arr[][] = {{1,2},{3,4}};
//		arr[0][0]= 10;
		//arr = {10,20,30}; 오류 못쓴다
		
			
			
//		for (int[]n : arr)
//			for (int k: n)
//				System.out.println(k);
	
// 4월 18일 리뷰: 꼭 열었으면 닫자. {}[]()!!!!!!!!!!!!