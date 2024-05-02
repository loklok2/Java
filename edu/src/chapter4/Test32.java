package chapter4;

public class Test32 {

	public static void main(String[] args) {
		
		int a[] = new int[20];
		a[0] = 0;
		for(int i = 1; i<20; i++) {
			a[i] = a[i-1] + 5;
		}
		for(int j = 0; j<20; j++) {
			System.out.println(a[j]);
		}
		
		
		
		
		
//		int a[] = new int[10];
//		a[0]= 0;
//		for(int i = 1; i<10; i++) {
//			a[i] = a[i-1] + 5;
//		}
//		
//		for(int j= 0; j<10; j++) {
//			System.out.println(a[j]);
//		}
//		
	
	}

}
