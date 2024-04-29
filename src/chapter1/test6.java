package chapter1;

public class test6 {

	public static void main(String[] args) {
		//비트연산자
		int num = 42;
		String binaryValue1 = Integer.toBinaryString(num);
		System.out.println(binaryValue1);
		String binaryValue2 = Integer.toHexString(num);
		System.out.println(binaryValue2);
	}
}