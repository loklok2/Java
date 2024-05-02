package chapter1;

public class test7 {

	public static void main(String[] args) {
		byte a = 0b00010001;
		byte b = 0b00100010;
		
		int c = a & b;
		System.out.println(Integer.toBinaryString(c));
		
		
		
		
		int f = 12;
		int g = (~f)+1; //two's
		System.out.println(g);
	}
}