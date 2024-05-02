package chapter4;

public class Test39 {

	public static void main(String[] args) {
		int arr[][] = new int[5][5];
		for(int i = 0; i < arr.length; i++) 
			for(int j = 0; j< arr.length; j++)
				
			{
				arr[i][j] = i * 5 + j + 1;
			}
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					if(i==j)
						sum = sum + arr[i][j];System.out.print(arr[i][j]+" ");
			}
		}
			System.out.println(sum);
	}
}