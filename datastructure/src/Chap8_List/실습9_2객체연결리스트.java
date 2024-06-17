package Chap8_List;
/*
 * 정수 리스트 > 객체 리스트: 2번째 실습 대상
 */
import java.util.Comparator;
import java.util.Scanner;


class SimpleObject5 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	private String no; // 회원번호
	private String name; // 이름
	String expire;//  유효기간 필드를 추가

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}
	
	public String getNo() {
        return no;
    }
	
	public SimpleObject5() { //생성자
		no = null;
		name = null;
	}
	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {//sw가 3이면 11 비트 연산 >  NO, NAME을 모두 입력받는다 
		//여기서 sw가 3이라고 했을 때, 이를 이진수로 표현하면 11. 즉, 두 번째 비트와 첫 번째 비트가 모두 1.
		//NO는 1이므로, 이진수로는 01. 따라서 sw & NO는 11 & 01이 되어, 결과는 01. 이는 10진수로 1.
		//즉, sw & NO의 결과가 NO와 같다는 것은 sw의 첫 번째 비트가 1이라는 뜻. 이는 sw가 NO를 포함한다는 의미로 해석. 
		//이와 같은 방식으로 sw & NAME의 결과를 통해 sw가 NAME을 포함하는지를 판단. 
		//이러한 방식을 통해 sw의 값에 따라 번호와 이름을 동시에, 또는 각각 입력받을 수 있게 됨.
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요."+ sw);

		if ((sw & NO) == NO) { //& 는 bit 연산자임 sw가 3이면 &는 비트 연산이므로 결과는 1
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {//sw가 3이고 NAME과 비트 & 연산하면 결과는 2
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			// d1의 회원번호가 d2의 회원번호보다 크면 1을 반환
			// d1의 회원번호가 d2의 회원번호보다 작으면 -1을 반환
			// 두 회원번호가 같으면 0을 반환
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no)<0) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			// d1의 이름과 d2의 이름을 비교하여 결과를 반환
			// 이름이 같으면 0, d1의 이름이 사전 순으로 더 앞이면 음수, 더 뒤면 양수를 반환
			return d1.name.compareTo(d2.name);
		}
	}
}
class Node2 {
	SimpleObject5 data;
	Node2 link;
	public Node2(SimpleObject5 element) {  //생성자
		data = element;
		link = null;
	}
}

class LinkedList2 {
	Node2 first; 
	
	public LinkedList2() { //생성자
		first = null;
	}

	public int Delete(SimpleObject5 element, Comparator<SimpleObject5> cc)
	//전달된 element를 찾을 때 comparator 객체를 사용한다 
	{
		Node2 q, current = first;
		q = current;
		
		if(current != null) {
			//첫번째 노드데이터가 삭제 데이터와 일치하면
			if(cc.compare(q.data, element) == 0) {
				first = q.link; // 첫번째 노드를 다음노드로 변경하고
				return 1;	//삭제 성공
			}else {
				while(q.link != null) { //첫번째 이후 노드중에서 삭제할 데이터 찾기
					if(cc.compare(q.link.data, element) == 0) { //삭제할 데이터를 찾으면
						q.link = q.link.link; // 현재노드의 다음링크를 다다음 노드로 변경
						return 1; //삭제 성공
					}
					current = q.link; // 현재 노드를 다음 노드로 이동
				}
			}
		}
		return -1;// 삭제할 대상이 없다.
	}
	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node2 p = first;
		SimpleObject5 so;
		while(p != null) { // p가 null이 아닐 때까지 반복
			System.out.println(p.data.toString()); // p의 데이터를 출력
			p = p.link; // p를 다음 노드로 이동
		}
	}
	public void Add(SimpleObject5 element, Comparator<SimpleObject5> cc) {
		//임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
		Node2 newNode = new Node2(element); // 새 노드를 생성하고, 데이터를 설정합니다.
		if (first == null) { // 리스트가 비어있는 경우
			first = newNode; // 첫 번째 노드를 새 노드로 설정합니다.
		} else { // 리스트가 비어있지 않은 경우
			Node2 p = first; // p를 첫 번째 노드로 설정합니다.
			while(p.link != null) { // p의 다음 노드가 null이 아닐 때까지 반복합니다.
				p = p.link; // p를 다음 노드로 이동합니다.
			}
			p.link = newNode; // p의 다음 노드를 새 노드로 설정합니다.
		}
	}
	
	public boolean Search(SimpleObject5 element, Comparator<SimpleObject5> cc) { 
		// 전체 리스트를 올림차순 순서대로 출력한다.
		Node2 q, current = first;
		q = current;
		while (q != null) {
	        if (cc.compare(q.data, element) == 0) {
	            return true;
	        }
	        current = q;
	        q = q.link;
	    }
	    return false;
	}
	/*
	 * 연결리스트 a,b에 대하여 a = a + b
	 * merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지 않고 합병하는 알고리즘 구현
	 * 난이도 등급: 최상급
	 * 회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9)이면 a = (2,3,4,5,8,9)가 되도록 구현하는 코드
	 */
	void Merge(LinkedList2 b) {
	    if (first == null) { // 첫 번째 리스트가 비어있는 경우
	        first = b.first; // 첫 번째 리스트를 두 번째 리스트로 설정
	    } else if (b.first == null) { // 두 번째 리스트가 비어있는 경우
	        // 첫 번째 리스트를 그대로 유지합니다. 추가 작업이 필요 없음
	    } else { // 두 리스트 모두 비어있지 않은 경우
	        Node2 aPtr = first; // 첫 번째 리스트의 현재 노드를 가리킨다
	        Node2 bPtr = b.first; // 두 번째 리스트의 현재 노드를 가리킨다
	        Node2 mergedPtr; // 합친 리스트의 현재 노드를 가리킨다
	        // 첫 번째 노드를 결정합니다.
	        if (aPtr.data.getNo().compareTo(bPtr.data.getNo()) < 0) { // 첫 번째 리스트의 현재 노드가 더 작은 경우
	            mergedPtr = aPtr; // 합친 리스트의 현재 노드를 첫 번째 리스트의 현재 노드로 설정
	            aPtr = aPtr.link; // 첫 번째 리스트의 현재 노드를 다음 노드로 이동
	        } else { // 두 번째 리스트의 현재 노드가 더 작거나 같은 경우
	            mergedPtr = bPtr; // 합친 리스트의 현재 노드를 두 번째 리스트의 현재 노드로 설정
	            bPtr = bPtr.link; // 두 번째 리스트의 현재 노드를 다음 노드로 이동
	        }

	        first = mergedPtr; // 첫 번째 노드를 설정합니다.

	        // 두 리스트의 모든 노드를 비교하면서 병합
	        while (aPtr != null && bPtr != null) { // 두 리스트의 현재 노드가 모두 null이 아닌 동안 반복
	            if (aPtr.data.getNo().compareTo(bPtr.data.getNo()) < 0) { // 첫 번째 리스트의 현재 노드가 더 작은 경우
	                mergedPtr.link = aPtr; // 합친 리스트의 현재 노드의 다음 노드를 첫 번째 리스트의 현재 노드로 설정
	                aPtr = aPtr.link; // 첫 번째 리스트의 현재 노드를 다음 노드로 이동
	            } else { // 두 번째 리스트의 현재 노드가 더 작거나 같은 경우
	                mergedPtr.link = bPtr; // 합친 리스트의 현재 노드의 다음 노드를 두 번째 리스트의 현재 노드로 설정
	                bPtr = bPtr.link; // 두 번째 리스트의 현재 노드를 다음 노드로 이동
	            }
	            mergedPtr = mergedPtr.link; // 합친 리스트의 현재 노드를 다음 노드로 이동
	        }

	        // 남아있는 노드를 추가
	        if (aPtr != null) { // 첫 번째 리스트에 남아있는 노드가 있는 경우
	            mergedPtr.link = aPtr; // 합친 리스트의 현재 노드의 다음 노드를 첫 번째 리스트의 현재 노드로 설정
	        } else { // 두 번째 리스트에 남아있는 노드가 있는 경우
	            mergedPtr.link = bPtr; // 합친 리스트의 현재 노드의 다음 노드를 두 번째 리스트의 현재 노드로 설정
	        }
	    }
	}

}
public class 실습9_2객체연결리스트 {

	enum Menu {
		Add( "삽입"), Delete( "삭제"), Show( "인쇄"), Search( "검색"), Merge("합병"), Exit( "종료");

		private final String message;                // 표시할 문자열

		static Menu MenuAt(int idx) {                // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) {                        // 생성자(constructor)
			message = string;
		}

		String getMessage() {                        // 표시할 문자열을 반환
			return message;
		}
	}

	//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 &&
						m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() ||key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu;                               
		LinkedList2 l = new LinkedList2();
		LinkedList2 l2 = new LinkedList2();
		Scanner sc = new Scanner(System.in);
		int count = 3; //3개의 객체 입력 개수
		SimpleObject5 data;
		do {
			switch (menu = SelectMenu()) {
			case Add :                          
				data = new SimpleObject5();
				data.scanData("입력", 3);
				l.Add(data, SimpleObject5.NO_ORDER);//회원번호 순서로 정렬 입력
				break;
			case Delete :                         
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
				int num = l.Delete(data, SimpleObject5.NO_ORDER);//회원번호 조건 비교하여 삭제 
				System.out.println("삭제된 데이터 성공은 " + num);
				break;
			case Show :                           
				l.Show();
				break;
			case Search : // 회원 번호 검색
				data = new SimpleObject5();
				data.scanData("탐색", SimpleObject5.NO);
				boolean result = l.Search(data, SimpleObject5.NO_ORDER);//회원번호로 검색
				if (result)
					System.out.println("검색 성공 = " + result );
				else
					System.out.println("검색 실패 = " + result);
				break;
			case Merge://난이도 상
				for (int i = 0; i < count; i++) {//3개의 객체를 연속으로 입력받아 l2 객체를 만든다 
					data = new SimpleObject5();
					data.scanData("병합", 3);
					l2.Add(data, SimpleObject5.NO_ORDER );				
				}
				l.Merge(l2);
				break;
			case Exit :                           // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}


