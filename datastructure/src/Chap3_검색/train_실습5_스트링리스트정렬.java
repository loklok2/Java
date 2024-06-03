package Chap3_검색;
/*
 * 3장 5번 실습과제 - 스트링 리스트 정렬
 * 리스트를 배열로 변환후 중복제거후 다시 리스트 만들기 등 실습
 * 리스트 정렬 찾고 merge
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class train_실습5_스트링리스트정렬 {
			//compareTo if(compareTo(b)>0)
	    public static String[] removeElement1(String[] arr, String item) {
			List<String> list = new ArrayList<>(Arrays.asList(arr));
			list.remove(item);
	    	return list.toArray(new String[0]);
	    }
	    
	    static void getList(List<String> list) {
			list.add("서울");	list.add("북경");
			list.add("상해");	list.add("서울");
			list.add("도쿄");	list.add("뉴욕");


			list.add("런던");	list.add("로마");
			list.add("방콕");	list.add("북경");
			list.add("도쿄");	list.add("서울");

			list.add(1, "LA");
	    }
	    static void showList(String topic, List<String> list) {
	    	System.out.println(topic);
	    	for(String item : list) {
	    		System.out.println(item);
	    	}
	    }
	    static void sortList(List<String> list) {
	    	//배열의 구현과 리스트의 구현의 차이를 remove.add 사용불가
	    	//리스트를 배열로 변환
	    	String[] cityarray = list.toArray(new String[0]);
	    	// 배열에서 중복제거
	    	Set<String> set = new HashSet<>(Arrays.asList(cityarray));
	    	String[] CityArray = set.toArray(new String[0]);
	    	// 배열에서 정렬하고, 내림차순으로 변경
	    	Arrays.sort(CityArray);
	    	//배열을 리스트로 저장
	    	list.clear();
	    	list.addAll(Arrays.asList(CityArray));
	    }
	    
	    static String[] removeDuplicateList(List<String> list) {
		    String cities[] = new String[0];
		    cities = list.toArray(cities);
		    
		    return cities;
	    }
		public static void main(String[] args) {
			ArrayList<String> list = new ArrayList<>();
			getList(list);
			showList("입력후", list);
			//sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩

		    Collections.sort(list);
			System.out.println();
			showList("오름차순 정렬 후", list);
			
			Collections.reverse(list);
			System.out.println();
			showList("내림차순 정렬 후", list);

		    sortList(list);
		    System.out.println();
		    String[] cities = removeDuplicateList(list);
	        ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities));
		    showList("중복제거후:", lst);
		}
	}

