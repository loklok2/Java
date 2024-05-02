package chapter5;

public class Name {
	
	static void test(String name, int... v) {
		System.out.println(name + " : ");
		for(int x : v)
			System.out.print(x+ " ");
			System.out.println();
	}
	
	public static void main(String[] args) {
		test("오정임", 98, 85, 88);
		test("오정임", 98, 85, 88);
		test("오정임", 98, 85, 88);
	}
}
