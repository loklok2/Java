package test;
/*
 * 3장 과제3 : 파일 리스트를 읽어 중복 제거후 2개의 리스트를 합병하여 정렬후 파일에 저장
 * file1: 서울,도쿄,북경,상해,서울,도쿄, 뉴욕,부산 , 상해,도쿄 ,  서울, 도쿄
 * file2: 런던, 로마,방콕, 도쿄,서울,부산
 * file > string split() > 배열 > ArrayList > sort > iterator 사용하여 merge > 중복 제거 > string 배열 > file에 저장
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.ByteBuffer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class Test_중복없는리스트합병 {
    static ArrayList<String> removeDuplicate(ArrayList<String> al) {
        // 중복을 제거하는 함수
        ArrayList<String> list1 = new ArrayList<>();
        list1 = al;
        int p = 0, q= 0;
        while(p<list1.size()) {
        	q = p+1;
        	while(q <list1.size()) {
        		if(list1.get(p).compareTo(list1.get(q))==0) {
        			list1.remove(q);
        		}else {
        			break;
        		}
        		p++;
        	}
        }
        return list1;
    }

    static List<String> mergeList(List<String> list1, List<String> list2) {
        // 두 리스트를 병합하여 정렬하는 함수
        List<String> mergedList = new ArrayList<>();
        int index1 = 0; // 리스트1의 인덱스
        int index2 = 0; // 리스트2의 인덱스

        while (index1 < list1.size() && index2 < list2.size()) {
            String str1 = list1.get(index1); // 리스트1의 현재 요소
            String str2 = list2.get(index2); // 리스트2의 현재 요소
            int compareResult = str1.compareTo(str2); // 두 요소 비교

            if (compareResult < 0) { // 리스트1의 요소가 더 작은 경우
                mergedList.add(str1);
                index1++;
            } else if (compareResult > 0) { // 리스트2의 요소가 더 작은 경우
                mergedList.add(str2);
                index2++;
            } else { // 두 요소가 같은 경우
                mergedList.add(str1); // 아무 요소나 추가해도 상관 없음
                index1++;
                index2++;
            }
        }

        // 남은 요소들을 추가
        while (index1 < list1.size()) {
            mergedList.add(list1.get(index1));
            index1++;
        }
        while (index2 < list2.size()) {
            mergedList.add(list2.get(index2));
            index2++;
        }

        return mergedList;
    }

	public static void main(String[] args) {
		try {
			/*
			 * 자바 교재 547: 이클립스 > edu 프로젝트 - 마우스 우측 > New>File >a.txt 생성
			 * 입력 데이터를 다음과 같이 만든다:
			 *    file1: 서울,도쿄,북경,상해,서울,도쿄, 뉴욕,부산
			 *               상해,도쿄
			 *               서울, 도쿄
			 *    file2: 런던, 로마,방콕, 도쿄,서울,부산           
			 * 자바 교재 580: Path 클래스 - 파이썬 유사 
			 */
			Path input1 = Paths.get("a1.txt");
			byte[] bytes1 = Files.readAllBytes(input1);
			//readAllBytes: 파일의 모든 바이트를 읽어오는 메서드입니다. 
			//이 메서드는 파일을 열고 파일의 크기만큼 바이트를 읽어서 바이트 배열로 반환합니다.
			System.out.println("bytes[]의 길이 = "+bytes1.length);
			Path input2 = Paths.get("a2.txt");
			byte[] bytes2 = Files.readAllBytes(input2);
			
			String s1 = new String(bytes1);
			String s2 = new String(bytes2);
			System.out.println("입력 스트링: s1 = " + s1);
			System.out.println("입력 스트링: s2 = " + s2);
			String[] sarray1 = s1.split("[,\\s]+\r\n");// [,\\s]+\r\n은 쉼표나 공백이 하나 이상 나오고 이어서 캐리지 리턴과 개행 문자가 있는 패턴
			String[] sarray2 = s2.split("[,\\s]+\r\n");//file에서 enter키는 \r\n으로 해야 분리됨
			showData("스트링 배열 sarray1", sarray1);
			showData("스트링 배열 sarray2", sarray2);

			trimSpace(sarray1);
			trimSpace(sarray2);

			showData("trimSpace() 실행후 :스트링 배열 sarray1", sarray1);
			showData("trimSpace() 실행후 :스트링 배열 sarray2", sarray2);
			System.out.println("+++++++\n");
			// file1에서 read하여 list1.add()한다.
			// 배열을 list로 만드는 방법
			// 방법1:
			ArrayList<String> list1 = new ArrayList<>();
			makeList(sarray1, list1);
			showList("리스트1: ", list1);
			// 방법2
			ArrayList<String> list2 = new ArrayList<>(Arrays.asList(sarray2));
			showList("리스트2: ", list2);
			
			System.out.println("+++++++ collection.sort()::");
			Collections.sort(list1);
			showList("정렬후 리스트1: ", list1);

			//Arrays.sort(list2, null);//에러 발생 확인하고 이유는?
			Collections.sort(list2);
			showList("정렬후 리스트2: ", list2);	
	
			//정렬된 list에서 중복 제거 코드
			list1 = removeDuplicate(list1);
			list2 = removeDuplicate(list2);
			showList("중복 제거후 리스트1: ", list1);	
			showList("중복 제거후 리스트1: ", list2);	
	
			
			List<String> list3 = new ArrayList<>();
			
			// 방법3:
			list3 = mergeList(list1, list2);
			showList("merge후 합병리스트: ", list3);	

			// ArrayList를 배열로 전환
			String[] st = list3.toArray(new String[list3.size()]);
			// binary search 구현
			// binSearch(st, st.length, "key");
			// 정렬된 list3을 file에 출력하는 코드 완성
			System.out.println("\n" + "file에 출력:");
			int bufferSize = 10240;
			
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
			writeFile(list3, buffer);
			
			FileOutputStream file = new FileOutputStream("c.txt");
			FileChannel channel = file.getChannel();
			channel.write(buffer);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    static void writeFile(List<String> list) {
        try {
            FileOutputStream file = new FileOutputStream("C:/Users/user/Desktop/K_7/k7_java/datastructure/src/c.txt");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10240);

            for (String str : list) {
                buffer.put((str + "\n").getBytes());
            }

            buffer.flip();
            channel.write(buffer);

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void trimSpace(String[] arr) {
        // 문자열 배열의 공백 제거
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
    }
    
    static void showData(String string, String[] arr) {
        // 주어진 메시지와 배열의 내용을 출력합니다.
        System.out.println(string);
        for (String str : arr) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

    static void showList(String string, List<String> list) {
        // 주어진 메시지와 리스트의 내용을 출력합니다.
        System.out.println(string);
        for (String str : list) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

}
