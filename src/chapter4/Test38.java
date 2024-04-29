package chapter4;

public class Test38 {

	public static void main(String[] args) {
		int[]arr = {1,2,3,4,5};
//		n>정수변수, 배열의 각 element 배열이름
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println('\n');
		
		for(int n : arr) {
			System.out.print(n+" ");
		}
	}
	
}

// println>라인 다음줄 // print>라인 그대로