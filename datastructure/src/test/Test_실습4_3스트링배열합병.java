package test;

/*
 * 3장 과제1: 스트링 배열 합병 정렬
 * merge.sort
 */
import java.util.Arrays;
import java.util.List;
public class Test_실습4_3스트링배열합병 {
    public static void main(String[] args) {
	String[] s1 = {"홍길동", "강감찬", "을지문덕", "계백", "김유신", "최치원" };
	String[] s2 = {"독도", "울릉도", "한산도", "영도", "오륙도", "동백섬"};
	Arrays.sort(s1);
	Arrays.sort(s2);
	
	showList("s1배열 = ", s1);
	showList("s2배열 = ", s2);

	String[] s3 = mergeList(s1,s2);//정렬된 s1[], s2[]을 합병하여 다시 정렬된 결과를 만드는 함수 구현
	showList("스트링 배열 s3 = s1 + s2:: ", s3);
    }

	private static String[] mergeList(String[] s1, String[] s2) {
		int length1 = s1.length;
		int length2 = s2.length;
		String[] s3 = new String[length1 + length2];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < length1 && j < length2) {
			if(s1[i].compareTo(s2[j])<0) {
				s3[k++] = s1[i++];
			}else {
				s3[k++] = s2[j++];
			}
		}
		while ( i < length1) {
			s3[k++] = s1[i++];
		}
		while (j <length2 ) {
			s3[k++] = s2[j++];
		}
		return s3;
	}

	private static void showList(String string, String[] s1) {
		System.out.println(string);
		for(String s: s1) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

}
