package Chap3_검색;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 3장 4번 실습과제 - 객체 배열의 정렬과 이진검색 - Comparator 구현
 * 3장 실습 3-8를 수정하여 객체 배열의 정렬 구현
 */

class PhyscData3 {
	 private String name;
	 private int height;
	 private double vision;
	public PhyscData3(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	public String getName() {
		return name;
	}
	
	public int getHeight() {
		return height;
	}
	public double getVision() {
		return vision;
	}

}
class NameOrder implements Comparator<PhyscData3>{
	@Override
	public int compare(PhyscData3 p1, PhyscData3 p2) {
		return p1.getName().compareTo(p2.getName());
	}

}
class HeightOrder implements Comparator<PhyscData3>{
	public int compare(PhyscData3 p1, PhyscData3 p2) {
        if (p1.getHeight() > p2.getHeight()) {
            return 1;
        } else if (p1.getHeight() < p2.getHeight()) {
            return -1;
        } else {
            return 0;
        }
    }

}
class VisionOrder implements Comparator<PhyscData3>{
	@Override
	public int compare(PhyscData3 p1, PhyscData3 p2) {
//		return (int)(p1.getVision() - p2.getVision());   //int 로 비교하면 소주점 이하 정보가 없어져서 비교가 불가능함
		return Double.compare(p1.getVision(),p2.getVision());
	}

}
public class train_실습4_8객체비교연산자 {	
	static final Comparator<PhyscData3> HEIGHT_ORDER = new HeightOrder();

	public static void main(String[] args) {
		PhyscData3[] data = {
				new PhyscData3("홍길동", 162, 0.3),
				new PhyscData3("나가자", 164, 1.3),
				new PhyscData3("다정해", 152, 0.7),
				new PhyscData3("소주다", 172, 0.4),
				new PhyscData3("사이다", 182, 0.6),
				new PhyscData3("신정신", 166, 1.2),
				new PhyscData3("이기자", 167, 1.5),
		};
		showData("정렬전 객체 배열", data);
		Arrays.sort(data, HEIGHT_ORDER);
		showData("정렬후 객체 배열", data);
		
		showData("height로 정렬후 객체 배열", data);
		PhyscData3 key = new PhyscData3("길동", 167, 0.2);
		
		int idx = Arrays.binarySearch(data, key, HEIGHT_ORDER);
		System.out.println("\nArrays.binarySearch(): result = " + idx);   //124p
		
		Arrays.sort(data, new VisionOrder());//익명객체 
		showData("vision로 정렬후 객체 배열", data);
		
		Arrays.sort(data, new Comparator<PhyscData3>() {
			@Override
			public int compare(PhyscData3 a1, PhyscData3 a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		showData("name로 정렬후 객체 배열", data);
	}
	
	 static void showData(String msg, PhyscData3[] data) {
		 System.out.println(msg);
		 for (PhyscData3 d : data) {
			 System.out.println(d.getName() + " " + d.getHeight() + " " + d.getVision());
	        }
		 System.out.println();
	    }

}
