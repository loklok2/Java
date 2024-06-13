package test;
/*
 * 6장 구현 실습과제1 
 * 객체로 실행
 */

class PhyscData implements Comparable<PhyscData>{
	String name;              // 이름
    int    height;            // 키
    double vision;            // 시력
	
	public PhyscData(String name, int height, double vision) { //생성자
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	
	@Override
	public int compareTo(PhyscData p) { //compareTo 메소드 구현
		int result = this.name.compareTo(p.name);
		if (result == 0) {
			result = Integer.compare(this.height,p.height);
			if(result == 0) {
				result = Double.compare(this.vision, p.vision);
			}
		}
		return result;
	}
	
	public String toString() {
		return name + "(키: " + height +", 시력:" +vision+")";
	}

}

public class Test_객체merge정렬 {
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb ) {
		int size = rightb - lefta + 1; //배열의 총크기
		PhyscData[] temp = new PhyscData[size]; //배열생성
		int p = lefta; // 첫번째 서브배열의 인덱스
		int q = leftb;	// 두번째 서브배열의 인덱스
		int ix = 0;		// 임시 배열의 인덱스 
		while (p <= righta && q <= rightb) { //두 배열을 병합하는동안 반복
			if (a[p].compareTo(a[q]) <= 0) { //두 배열의 요소를 비교해서 
				temp[ix++] = a[p++];		 //더 작은 요소를 ix배열에 넣고 인덱스 증가
			} else {
				temp[ix++] = a[q++];
			}
		}
		while (p <= righta) {	// 첫번째 배열의 남은요소를 임시배열에 복사
			temp[ix++] = a[p++];
		}
		while (q <= rightb) { 	// 두번째 배열의 남은요소를 임시배열에 복사
			temp[ix++] = a[q++];
		}
		System.out.print("병합: ");
		for (ix = 0; ix < size; ix++) { //ix배열의 요소를 원래 배열에 복사하여 병합
			a[lefta + ix] = temp[ix];
			System.out.print(" "+ a[lefta+ix]);
		}
		System.out.println();
	}

	// --- 퀵 정렬(비재귀 버전)---// 비재귀는 스택을 사용
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left+right)/2; //중간지점 계산
		// 배열 분할 상태 출력
        System.out.print("분할: ");
        for (int i = left; i <= right; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
		if (left == right) return; // 배열의 요소가 같은 경우 리턴
		MergeSort(a, left, mid); 	// 왼쪽 절반 정렬
		MergeSort(a, mid+1, right);	// 오른쪽 절반 정렬
		merge(a, left, mid, mid+1, right); 	// 절반 낸거 다시 합치기
		return; //리턴
	}

	public static void main(String[] args) {
		PhyscData[] x = {
		         new PhyscData("강민하", 162, 0.3),
		         new PhyscData("김찬우", 173, 0.7),
		         new PhyscData("박준서", 171, 2.0),
		         new PhyscData("유서범", 171, 1.5),
		         new PhyscData("이수연", 168, 0.4),
		         new PhyscData("장경오", 171, 1.2),
		         new PhyscData("황지안", 169, 0.8),
		     };
			
		int nx = x.length;
		MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬
		System.out.println("오름차순으로 정렬했습니다.");
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키  시력");
		System.out.println("------------------");
		for (int i = 0; i < x.length; i++) {
			System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);			
		}
	}
}
