package Chap8_List;
/*
 * 정수 리스트 > 객체 리스트: 2번째 실습 대상
 */
import java.util.Comparator;
import java.util.Scanner;


class SimpleObject5 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?
	static final int EXPIRE = 3; //유효기간을 읽어 들일까요?
	
	private String no; // 회원번호
	private String name; // 이름
	private String expire;//  유효기간 필드를 추가

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name + " [" + expire + "]";
	}
	public SimpleObject5() {
		no = null;
		name = null;
		expire = null;
	}
	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {//sw가 3이면 11 비트 연산 >  NO, NAME을 모두 입력받는다 
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
		if ((sw & EXPIRE) == EXPIRE) { // sw가 EXPIRE일 경우 유효기간을 입력받음
            System.out.print("유효기간: ");
            expire = sc.next();
        }
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no)<0) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return d1.name.compareTo(d2.name);
		}
	}
	public static final Comparator<SimpleObject5> EXPIRE_ORDER = new EXPIREOrderComparator();

	private static class EXPIREOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return (d1.expire.compareTo(d2.expire) > 0) ? 1 : (d1.expire.compareTo(d2.expire)<0) ? -1 : 0;
		}
	}
	
	
	
	
}
class Node2 {
	SimpleObject5 data;
	Node2 link;
	public Node2(SimpleObject5 element) {
		data = element;
		link = null;
	}
}

class LinkedList2 {
	Node2 first;
	public LinkedList2() {
		first = null;
	}

	public int Delete(SimpleObject5 element, Comparator<SimpleObject5> cc) {
		//전달된 element를 찾을 때 comparator 객체를 사용한다 
		Node2 q, current = first;
		q = current;
		while (current != null) {
	        if (cc.compare(current.data, element) == 0) {
	            if (current == first) {
	                first = current.link;
	            } else {
	                q.link = current.link;
	            }
	            return 1; // 삭제 성공
	        }
	        q = current;
	        current = current.link;
	    }
	    return -1; // 삭제할 데이터가 없음
	}
	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node2 p = first;
	    SimpleObject5 so;
	    while (p != null) { // p가 null이 아닐 때까지 반복
	    	so = p.data;
			System.out.print(so + "  "); // p의 데이터를 출력
			p = p.link; // p를 다음 노드로 이동
			if (p == null)  {
				System.out.println("회원 끝"); // p가 null이면 리스트의 끝이므로 출력
			}
		}
	}
	public void Add(SimpleObject5 element, Comparator<SimpleObject5> cc) {
		//임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
		Node2 newNode = new Node2(element);
		if (first == null) {//insert into empty list
	        first = newNode; // 빈 리스트에 첫 번째 노드로 삽입
	        return;
	    }
		
	    Node2 p = first;
	    Node2 q = null;
	    
	    while (p != null && cc.compare(p.data, element) < 0) {
	        q = p;
	        p = p.link;
	    }

	    if (q == null) {
	        newNode.link = first;
	        first = newNode;
	    } else {
	        newNode.link = p;
	        q.link = newNode;
	    }
	}
	
	public boolean Search(SimpleObject5 element, Comparator<SimpleObject5> cc) { 
		// 전체 리스트를 올림차순 순서대로 출력한다.
		Node2 q, current = first;
		q = current;
		while (current != null) {
	        if (cc.compare(current.data, element) == 0) {
	            System.out.println("찾은 데이터: " + current.data);
	            return true; // 요소를 찾으면 true 반환
	        }
	        q = current;
	        current = current.link;
	    }

	    System.out.println("찾는 데이터가 없습니다.");
	    return false; // 요소를 찾지 못하면 false 반환
	}
	public void Merge(LinkedList2 b, Comparator<SimpleObject5> cc) {
	    Node2 p = first; // 첫 번째 리스트의 시작 노드
	    Node2 q = b.first; // 두 번째 리스트의 시작 노드
	    Node2 prev = null; // 합병된 리스트의 이전 노드
	    Node2 temp = null; // 합병된 리스트의 첫 번째 노드

	    // 두 리스트가 모두 비어있을 경우
	    if (first == null) {
	        first = b.first; // 첫 번째 리스트가 비어 있으면 두 번째 리스트를 첫 번째 리스트로 설정
	        return;
	    }
	    if (b.first == null) {
	        return; // 두 번째 리스트가 비어 있으면 그대로 종료
	    }

	    // 첫 번째 노드 설정
	    if (cc.compare(p.data, q.data) <= 0) {
	        temp = p;
	        p = p.link;
	    } else {
	        temp = q;
	        q = q.link;
	    }
	    first = temp; // 합병된 리스트의 첫 번째 노드 설정
	    prev = temp;

	    // 두 리스트를 순회하면서 합병
	    while (p != null && q != null) {
	        if (cc.compare(p.data, q.data) <= 0) {
	            prev.link = p;
	            prev = p;
	            p = p.link;
	        } else {
	            prev.link = q;
	            prev = q;
	            q = q.link;
	        }
	    }

	    // 남은 노드 처리
	    if (p == null) {
	        prev.link = q;
	    } else {
	        prev.link = p;
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
				data.scanData("입력", 3); //7?
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
					data.scanData("병합", 3);//7?
					l2.Add(data, SimpleObject5.NO_ORDER );				
				}
				System.out.println("리스트 l::");
				l.Show();
				System.out.println("리스트 l2::");
				l2.Show();
				l.Merge(l2, SimpleObject5.NO_ORDER);
				//merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				System.out.println("병합 리스트 l::");
				l.Show();
				break;
			case Exit :                           // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}


