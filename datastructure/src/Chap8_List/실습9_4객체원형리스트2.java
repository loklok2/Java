package Chap8_List;

/*
 * 정수 리스트 > 객체 리스트> 객체 원형 리스트 
 * 헤드 노드가 있는 원형 리스트, 헤드 노드가 없는 원형 리스트 구현
 * merge 구현: in-place 구현, 새로운 노드를 생성하여 합병 구현 
 * 원형 이중 리스트로 동일하게 적용
 */
import java.util.Comparator;
import java.util.Scanner;

class SimpleObject4 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	private String no; // 회원번호
	private String name; // 이름
	String expire;//  유효기간 필드를 추가
	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}

	public SimpleObject4(String no, String name) {
		this.no = no;
		this.name = name;
	}
	public SimpleObject4() {// head node를 만들 때 사용
		this.no = null;
		this.name = null;
	}
	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject4> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject4> {
		public int compare(SimpleObject4 d1, SimpleObject4 d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject4> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject4> {
		public int compare(SimpleObject4 d1, SimpleObject4 d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

class Node5 {
	SimpleObject4 data;
	Node5 link;

	public Node5(SimpleObject4 element) {
		data = element;
		link = null;
	}
}

class CircularList4 {
	Node5 first;

	public CircularList4() { //head node
		first = null;
	}

	/*
	 * static void sortData(Fruit []arr, Comparator<Fruit> cc) { for (int i = 0; i <
	 * arr.length; i++) { for (int j = i; j < arr.length; j++) if
	 * (cc.compare(arr[i], arr[j])> 0) swap(arr, i, j); } }
	 */
	public int Delete(SimpleObject4 element, Comparator<SimpleObject4> cc) {
		if (first == null) {
            return -1; // 리스트가 비어있음
        }
        Node5 current = first;
        Node5 prev = null;
        do {
            if (cc.compare(current.data, element) == 0) {
                if (prev == null) { // 삭제할 노드가 첫 번째 노드인 경우
                    Node5 last = first;
                    while (last.link != first) {
                        last = last.link;
                    }
                    if (last == first) { // 리스트에 노드가 하나만 있는 경우
                        first = null;
                    } else {
                        last.link = first.link;
                        first = first.link;
                    }
                } else {
                    prev.link = current.link;
                }
                return 0; // 삭제 성공
            }
            prev = current;
            current = current.link;
        } while (current != first);
        return -1; // 삭제할 대상이 없음
    }

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		if (first == null) {
            System.out.println("리스트가 비어있습니다.");
            return;
        }
        Node5 current = first;
        do {
            System.out.println(current.data);
            current = current.link;
        } while (current != first);
    }

	public void Add(SimpleObject4 element, Comparator<SimpleObject4> cc) { 
		// 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
		Node5 newNode = new Node5(element);
        if (first == null) {
            first = newNode;
            newNode.link = first;
        } else {
            Node5 current = first;
            Node5 prev = null;
            do {
                int compareResult = cc.compare(current.data, element);
                if (compareResult >= 0) {
                    break;
                }
                prev = current;
                current = current.link;
            } while (current != first);

            newNode.link = current;
            if (prev != null) {
                prev.link = newNode;
            } else {
                // 새 노드가 첫 번째 노드가 됨
                Node5 last = first;
                while (last.link != first) {
                    last = last.link;
                }
                last.link = newNode;
                first = newNode;
            }
        }
    }

	public boolean Search(SimpleObject4 element, Comparator<SimpleObject4> cc) { // 전체 리스트를 순서대로 출력한다.
		if (first == null) {
            return false; // 리스트가 비어있음
        }
        Node5 current = first;
        do {
            if (cc.compare(current.data, element) == 0) {
                return true; // 검색 성공
            }
            current = current.link;
        } while (current != first);
        return false; // 검색 실패
    }
	void Merge(CircularList4 b, Comparator<SimpleObject4> cc) {
	    // b 리스트가 비어있으면 아무 작업도 하지 않음
	    if (b.first == null) {
	        return;
	    }
	    
	    // 현재 리스트(this)가 비어있으면 b 리스트를 그대로 복사하여 합병
	    if (first == null) {
	        first = b.first;
	        return;
	    }
	    
	    Node5 currentA = first;
	    Node5 currentB = b.first;
	    Node5 lastA = first;
	    
	    // 현재 리스트(this)의 마지막 노드를 찾음
	    while (lastA.link != first) {
	        lastA = lastA.link;
	    }
	    
	    // 현재 리스트의 첫 번째 노드와 b 리스트의 첫 번째 노드를 비교하여 합병 정렬
	    while (currentA != lastA && currentB != b.first) {
	        if (cc.compare(currentA.data, currentB.data) <= 0) {
	            currentA = currentA.link;
	        } else {
	            Node5 nextB = currentB.link;
	            lastA.link = currentB;
	            currentB.link = currentA;
	            currentB = nextB;
	        }
	    }
	    
	    // b 리스트에 남아 있는 노드들을 합병 결과에 추가
	    if (currentA == lastA) {
	        lastA.link = currentB;
	    }
	    
	    // 합병 후, 원형 리스트의 마지막 노드를 첫 번째 노드와 연결하여 원형을 유지
	    while (lastA.link != first) {
	        lastA = lastA.link;
	    }
	    lastA.link = first;
	}

}
public class 실습9_4객체원형리스트2 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		CircularList4 l = new CircularList4();
		CircularList4 l2 = new CircularList4();
		Scanner sc = new Scanner(System.in);
		SimpleObject4 data;
		int count = 3;//l2 객체의 숫자를 3개로 한다 

		do {
			switch (menu = SelectMenu()) {
			case Add: //
				data = new SimpleObject4();
				data.scanData("입력", 3);
				l.Add(data, SimpleObject4.NO_ORDER);
				break;
			case Delete: // 
				data = new SimpleObject4();
				data.scanData("삭제", SimpleObject4.NO);
				int num = l.Delete(data, SimpleObject4.NO_ORDER);
				System.out.println("삭제된 데이터 성공은 " + num);
				break;
			case Show: 
				l.Show();
				break;
			case Search: // 회원 번호 검색
				data = new SimpleObject4();
				data.scanData("탐색", SimpleObject4.NO);
				boolean result = l.Search(data, SimpleObject4.NO_ORDER);
				if (result)
					System.out.println("검색 성공 = " + result);
				else
					System.out.println("검색 실패 = " + result);
				break;
			case Merge:
				for (int i = 0; i < count; i++) {//3개의 객체를 연속으로 입력받아 l2 객체를 만든다 
					data = new SimpleObject4();
					data.scanData("병합", 3);
					l2.Add(data, SimpleObject4.NO_ORDER );				
				}
				System.out.println("리스트 l::");
				l.Show();
				System.out.println("리스트 l2::");
				l2.Show();
				l.Merge(l2, SimpleObject4.NO_ORDER);
				//merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				System.out.println("병합 리스트 l::");
				l.Show();
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}