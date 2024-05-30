package Chap3_검색;

/*
 * 3장 2번 실습과제 - 스트링 배열의 정렬과 이진검색 
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
public class train_실습2_6_0스트링배열정렬이진탐색 {

	public static void main(String[] args) {
		String []data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};
		showData("정렬전", data);
		sortData(data);//올림차순으로 정렬 교재211-212 단순 선택 정렬 알고리즘으로 구현
		showData("정렬후", data);

		String key = "포도";
		int resultIndex = linearSearch(data, key);//교재 100 페이지 seqSearch() 함수로 구현
		System.out.println("\nlinearSearch(포도): key = " + key + ", result 색인 = " + resultIndex);

		key = "배";
		resultIndex = binarySearch(data, key);//교재 109 페이지 binSearch() 함수로 구현
		System.out.println("\nbinarySearch(배):key = " + key + ",  result = " + resultIndex);
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(산딸기): key = " + key + ", result = " + resultIndex);
	}
	static void showData(String string, String[] data) {
		System.out.print(string + ": ");
		for (String item : data) {
			System.out.print(item + " ");
			
		}
		System.out.println();
	}

	static void sortData(String[] data) {
		for(int i = 0; i<data.length -1; i++) {  //배열반복정렬 실행
			int minindex = i;	//현재위치를 최소값인덱스 위치로 초기화
			for(int j = i; j < data.length; j++) {
				if(data[j].compareTo(data[minindex])<0) {  //문자열 비교, 현재 최소값보다 작은 값이 있다면 최소값 위치 업데이트
					minindex = j;			
				}
			}
			if(minindex != i) {      // 최소값이 현재위치와 다를경우 교환
				String temp = data[i];		// 교환을 위한 임시변수
				data[i] = data[minindex];		// 선택정렬에서는 최소값을 찾았을때 현재 위치값을 교환함으로써 정렬진행
				data[minindex] = temp;
			}
		}
	}


	static int binarySearch(String[] data, String key) {
		int l = 0;  //왼쪽 끝
		int r = data.length -1;  //오른쪽 끝
		while(l <= r) { //이진탐색 시작 l가 r보다 작거나 같을때까지 반복
			int mid = (l + r) /2; //중간 인덱스 값
			int comparevalue = key.compareTo(data[mid]);  //중간인덱스랑 키값이랑 비교
			if(comparevalue == 0) {    // 중간인덱스가 찾는요소이면 
				return mid;				//인덱스 반환하고 종료
			}
			else if (comparevalue < 0) { //찾는게 중간인덱스보다 왼쪽이면
				r = mid - 1;  // comparevalue가 음수면 탐색범위가 왼쪽
			}
			else {  // 찾는게 중간인덱스보다 오른쪽이면
				l = mid + 1;  // comparevalue가 양수이면 탐색범위가 오른쪽
			}
		}
		return -1;   //탐색반복하다 l이 r보다 커지니까 요소를 찾지못해서 -1반환 문자열에 존재안한다를 의미
	}

	static int linearSearch(String[] data, String key) {
		for (int i = 0; i < data.length; i++) {
			if(data[i].equals(key))
				return i;
		}
		return -1;
	}


}
