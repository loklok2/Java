package Chap2_기본자료구조;


//5번 실습 - 2장 실습 2-10를 수정하여 객체 배열의 정렬 구현 교재88p
class PhyscData implements Comparable<PhyscData>{
	String name;
	int height;
	double vision;
	
	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision= vision;
	}
	
	@Override
	public String toString() {
		return name + "(키: " + height +", 시력:" +vision+")";
	}
	@Override
	public int compareTo(PhyscData p) {
		int result = this.name.compareTo(p.name);
		if (result == 0) {
			result = Integer.compare(this.height,p.height);
			if(result == 0) {
				result = Double.compare(this.vision, p.vision);
			}
		}
		return result;
		//교재 123p
	}
	@Override
	public boolean equals(Object p) {
		if (this == p) return true;
		if (p == null || getClass() != p.getClass()) return false;
		PhyscData physcData = (PhyscData) p;
		return vision == physcData.vision && name.equals(physcData.name);
		

	}
}
//data[i].compareTo(data[i]) > 0
public class Test_실습2_14객체배열정렬 {
	public static void main(String[] args) {
		PhyscData[] data = {
				new PhyscData("홍길동", 162, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길동", 162, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("이길동", 167, 0.2),
				new PhyscData("최길동", 169, 0.5),
		};
		//bubble.sort?
		showData("정렬전",data);
		sortData(data);
		showData("정렬후", data);
		PhyscData[] newData= insertObject(data, new PhyscData("이기자", 179, 1.5));//배열의 사이즈를 1개 증가시킨후 insert되는 객체 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 객체 배열을 리턴
		showData("삽입후", newData);
	}
	

	static void sortData(PhyscData[] data) {
		for (int i = 0; i < data.length -1; i++) {
			for (int j = 0; j < data.length - 1 - i; j++) {
				if (data[j].compareTo(data[j + 1]) > 0) {
					swap(data, j, j +1);
				}
			}
		}
		
	}

	static void showData(String string, PhyscData[] data) {
		System.out.println(string);
		for (PhyscData str: data) {
			System.out.println(str + " ");
		}
		
	}

	static void swap(PhyscData[] data, int i, int j ) {
		PhyscData t = data[i];
		data[i] = data[j];
		data[j] = t;
	}
	
	static PhyscData[] insertObject(PhyscData[] data, PhyscData physcData) {
		//배열의 사이즈를 1개 증가시킨후 insert되는 스트링 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 스트링 배열을 리턴
		PhyscData[] newData = new PhyscData[data.length + 1];
		int index = 0;
		for (index = 0; index < data.length; index++) {
			if(data[index].compareTo(physcData)>0){
				break;
			}
		}
		for (int i = 0; i < index; i++) {
			newData[i] = data[i];
		}
		newData[index] = physcData;
		for (int i = 0; i < data.length; i++) {
			newData[i + 1] = data[i];
		}
		return newData;
//		PhyscData[] newData = Arrays.copyOf(data, data.length +1);
//		int index;
//		for (index =  0; index < data.length; index++) {
//			if(data[index].compareTo(physcData)>0) {
//				break;
//			}
//		}
//		for (int i = newData.length - 1; i > index; i--) {
//			newData[i] = newData[i - 1];
//		}
//		newData[index] = physcData;
//		return newData;
	}

}
