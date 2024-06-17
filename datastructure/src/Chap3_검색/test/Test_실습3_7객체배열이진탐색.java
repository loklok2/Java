package Chap3_검색;

import java.util.Arrays;
import java.util.Comparator;


/*
 * 3장 과제2 : 객체 배열 정렬/검색 - 람다식사용
 * 
* Comparator를 사용하는 방법
* MyComparator implements Comparator<>
* MyComparator myComparator = new MyComparator();
* 
* Arrays.sort(array, myComparator);
* Collections.sort(list, new MyComparator());
*/

class Fruit4 {
	String name;
	int price;
	String expire;
	public Fruit4(String name, int price, String expire) {
		this.name = name;
		this.price = price;
		this.expire = expire;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public String toString() {
		return name + price;
	}

}
//교재 123~129 페이지 참조하여 구현
class FruitName implements Comparator<Fruit4>{

	@Override
	public int compare(Fruit4 f1, Fruit4 f2) {
		int result = f1.name.compareTo(f2.name);
		return result;
	}}
class FruitPrice implements Comparator<Fruit4>{
	@Override
	public int compare(Fruit4 f1, Fruit4 f2) {
		int result = Integer.compare(f1.price, f2.price);
		return result;
	}}

public class Test_실습3_7객체배열이진탐색 {

	private static void sortData(Fruit4[] arr, Comparator<Fruit4> cc_price) {}

	public static void main(String[] args) {

		Fruit4[] arr = {new Fruit4("사과", 200, "2023-5-8"), 
				new Fruit4("감", 500, "2023-6-8"),
				new Fruit4("대추", 200, "2023-7-8"), 
				new Fruit4("복숭아", 50, "2023-5-18"), 
				new Fruit4("수박", 880, "2023-5-28"),
				new Fruit4("산딸기", 10, "2023-9-8") };
		
		System.out.println("\n정렬전 객체 배열: ");
		showData("정렬전 객체", arr);
		
		FruitName cc = new FruitName();
		System.out.println("\n comparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc);
		showData("Arrays.sort(arr, cc) Name 정렬 후", arr);
		
		sortData(arr, new FruitPrice());
		showData("Arrays.sort(arr, cc)  Price 실행후", arr);
		
		// 람다식은 익명클래스 + 익명 객체이다
		Comparator<Fruit4> cc_expire = (a, b) -> a.expire.compareTo(b.expire);
		Arrays.sort(arr, cc_expire); // 람다식으로 만들어진 객체를 사용
		showData("람다식 변수 cc_expire을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);
		
		Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice()); 
		showData("람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

		System.out.println("\n익명클래스 객체로 정렬(가격)후 객체 배열: ");
		Arrays.sort(arr, new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 a1, Fruit4 a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData("name comparator - 익명 객체를 사용한 정렬:", arr);
		
		//익명 클래스를 사용한 comparator 객체
		Comparator<Fruit4> cc_name = (f1,f2) -> f1.getName().compareTo(f2.getName());// 익명클래스 사용
		
		Comparator<Fruit4> cc_price = Comparator.comparingInt(Fruit4::getPrice);


		Fruit4 newFruit4 = new Fruit4("수박", 880, "2023-5-18");
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		int result3Index = Arrays.binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit4, cc_price);//교재 113 binSearch() 함수를 고쳐서 구현 
		System.out.println("\nbinarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);	
	}

	private static void showData(String string, Fruit4[] arr) {
		System.out.println(string + ":");
		for (Fruit4 item : arr) {
			System.out.println(item + " ");
		}
		System.out.println();
		
	}

	static int binarySearch(Fruit4[] arr, Fruit4 newFruit4, Comparator<Fruit4> cc_price) {
		 int l = 0;
		 int r = arr.length - 1;
		 while (l <= r) {
			 int mid = (l + r) / 2;
		     int comparevalue = cc_price.compare(arr[mid], newFruit4);
		     if (comparevalue == 0) {
		    	 return mid;
		     } else if (comparevalue < 0) {
		         l = mid + 1;
		     } else {
		         r = mid - 1;
		     }
		  }
		  return -1;
	}
}


