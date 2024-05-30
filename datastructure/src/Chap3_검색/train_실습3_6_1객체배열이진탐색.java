package Chap3_검색;

/*
 * 3장 3번 실습과제 - 객체 배열의 정렬과 이진검색 - Comparable 구현
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;


//if(a>b) 객체라서 비교가 안되니 
//if(compareTO(n)>0)
//if(cc.compare(a,b) comparator 123~127p
//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData2 implements Comparable<PhyscData2>{
	String name;
	int height;
	double vision;

	public PhyscData2(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
		
	}
	@Override
	public String toString() {//[홍길동,162,0.3] 형태로 리턴한다 
		return Arrays.toString(new Object[] {name, height, vision});
	}
	@Override
	public int compareTo(PhyscData2 p) {
		int result = this.name.compareTo(p.name);
		if (result == 0) {
			result = Integer.compare(this.height,p.height);
			if(result == 0) {
				result = Double.compare(this.vision, p.vision);
			}
		}
		return result;
		
	}
	public boolean equals(Object p) {
		if (this == p) return true;
		if (p == null || getClass() != p.getClass()) return false;
		PhyscData2 physcData = (PhyscData2) p;
		return vision == physcData.vision && name.equals(physcData.name);
		
	}

}
public class train_실습3_6_1객체배열이진탐색 {
	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("나동", 164, 1.3),
				new PhyscData2("최길", 152, 0.7),
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("박동", 182, 0.6),
				new PhyscData2("박동", 167, 0.2),
				new PhyscData2("길동", 167, 0.5),
		};
		showData("정렬전", data);
		sortData(data);//6장 06-4 단순 삽입 정렬 InsertionSort() 함수로 구현
		showData("정렬후", data);
		reverse(data);
		showData("역순 재배치후", data);
		Arrays.sort(data);//사용된다 그 이유는? 이해가 되어야 한다 
		showData("Arrays.sort() 정렬후", data);
		
		PhyscData2 key = new PhyscData2("길동", 167, 0.5);
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(<길동,167,0.5>): result index = " + resultIndex);
		
		key = new PhyscData2("박동", 167, 0.6);
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);//comparable를 사용
		System.out.println("\nbinarySearch(<박동,167,0.6>): result index = " + resultIndex);
		key = new PhyscData2("나동", 164, 0.6);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);//comparable를 사용
		System.out.println("\nArrays.binarySearch(<나동,164,0.6>): result index = " + resultIndex);
	}

	static void reverse(PhyscData2[] data) {
		int l = 0;
		int r = data.length - 1;
		while (l < r) {
			PhyscData2 temp = data[l];
			data[l] = data[r];
			data[r] = temp;
			l++;
			r--;
		}

	}

	static void showData(String string, PhyscData2[] data) {
		System.out.println(string + ":");
		for (PhyscData2 item : data) {
			System.out.println(item + " ");
		}
		System.out.println();
		
	}
	
	static void sortData(PhyscData2[] data) {
		for(int i = 0; i < data.length -1; i++) {
			int midindex = i;
			for(int j = i; j < data.length; j++) {
				if(data[j].compareTo(data[midindex]) <0) {
					midindex = j;
				}
			}
			if(midindex != i) {
				PhyscData2 temp = data[i];
				data[i] = data[midindex];
				data[midindex] = temp;
			}
		}
		
	}
	
	

	static int linearSearch(PhyscData2[] data, PhyscData2 key) {
		for (int i = 0; i < data.length; i++) {
			if(data[i].equals(key))
				return i;
		}
		return -1;
	}
	
	static int binarySearch(PhyscData2[] data, PhyscData2 key) {
		int l = 0;
		int r = data.length -1;
		while(l <= r) {
			int mid = (l + r) /2;
			int comparevalue = key.compareTo(data[mid]);
			if(comparevalue == 0) {
				return mid;
			}
			else if (comparevalue < 0) {
				r = mid -1;
			}
			else {
				l = mid + 1;
			}
		}
		return -1;
	}
}
