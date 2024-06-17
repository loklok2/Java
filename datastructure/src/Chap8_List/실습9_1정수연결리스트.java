package Chap8_List;
//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data; //노드 데이터 저장변수
	Node1 link;	// 다음노드 가르키는 링크

	public Node1(int element) { //300p 참고
		data = element; 
		link = null; // 초기에는 다음 노드를 가르키는 링크는 null로 초기화
	}
}

class LinkedList1 {
	Node1 first; //리스트의 첫번째 노드

	public LinkedList1() {
		first = null;	//리스트의 첫번쨰 노드를 null로 초기화
	}

	public boolean Delete(int element) { //리스트에서 삭제하는 메서ㅣ드
		Node1 q, current = first; //q와 current를 첫 노드로 초기화
		q = current;
		
		if(first == null) {
			return false; //리스트 비었을때 삭제 못하니까 false반환
		}
		if (first.data == element) {
			first = first.link; //첫번째 노드가 삭제할 값이면 첫노드를 변경
			return true;
		}
		while (q.link != null) {
			if (q.link.data == element) { //삭제할 값을 찾으면
				q.link = q.link.link;	//노드를 건너뛰고 연결
				return true;				
			}
			q = current.link; 		//q를 다음 노드로 이동
		}
		return false;	//삭제할게 없으면 
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node1 p = first;  //p를 첫 노드로 초기화
	    int num = 0; // 인덱스 변수
	    while (p != null) { // p가 null이 아닐 때까지 반복
			System.out.print(p.data + " -> "); // p의 데이터를 출력
			p = p.link; // p를 다음 노드로 이동
			if (p == null)  {
				System.out.println("리스트 끝"); // p가 null이면 리스트의 끝이므로 출력
			}
		}
	}

	public void Add(int element) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{								// 처음에 중간에 끝에가 여기서 모두 구현이 되야한다 
		Node1 newNode = new Node1(element);
		if (first == null) // insert into empty list 
		{
			first = newNode; //리스트가 비었으면 첫번째 노드로 설정
			return;
		}
		Node1 p = first, q = null;//p라는 변수를 도입해서 각 노드를 따라간다
		while (p != null) {
			if(element > p.data) {
				q = p; //q가 p를 따라 다닌다
				p = p.link; //p를 다음노드로 이동
			}else {
				if(q == null) { //1번처리 = 첫번째 노드에 삽입하는 경우임
					newNode.link = p;
					first = newNode;
					return;
				}
				q.link = newNode; //2번 새로운 노드 삽입
				newNode.link = p;
				return;
			}
		}
		if(q != null) { 
			q.link = newNode;  	// 리스트의 끝에 삽입
		} else {
			first = newNode;		//교수님 코드에서 하나 더 추가  리스트가 비엇을 경우에 첫번째 노드로 설정
		}

	}

	public boolean Search(int data) { //전달된 data 값을 찾아 존재하면 true로 리턴, 없으면 false로 리턴
		Node1 ptr = first;   //ptr을 첫 노드로 초기화
		while (ptr != null) {
			if(data == ptr.data) {			//값을 찾았으면 true
				return true;
			}
			ptr = ptr.link; // ptr을 다음 노드로 이동
		}
		return false;
	}
	
	void Merge(LinkedList1 b) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b
		 * merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지 않고 합병하는 알고리즘 구현
		 * 난이도 등급: 최상
		 * a = (3, 5, 7), b = (2,4,8,9)이면 a = (2,3,4,5,8,9)가 되도록 구현하는 코드
		 * 새로운 노드를 만들지 않고, 제자리에서 바로 구현하는거
		 */
	    Node1 p1 = first; // 첫 번째 리스트의 포인터
	    Node1 p2 = b.first; // 두 번째 리스트의 포인터
	    Node1 tail = null; // 합병된 리스트의 끝을 가리킬 포인터

	    // 두 리스트가 모두 비어있을 경우 처리
	    if (p1 == null) {
	        first = p2; // 첫 번째 리스트가 비어 있으면 두 번째 리스트를 첫 번째 리스트로 설정
	        return;
	    }
	    if (p2 == null) {
	        return; // 두 번째 리스트가 비어 있으면 첫 번째 리스트는 그대로 유지
	    }

	    // 첫 번째 노드를 설정
	    if (p1.data <= p2.data) {
	        first = p1; // 첫 번째 리스트의 첫 노드가 더 작거나 같으면 첫 노드로 설정
	        p1 = p1.link; // 첫 번째 리스트의 포인터를 다음 노드로 이동
	    } else {
	        first = p2; // 두 번째 리스트의 첫 노드가 더 작으면 첫 노드로 설정
	        p2 = p2.link; // 두 번째 리스트의 포인터를 다음 노드로 이동
	    }
	    tail = first; // tail을 첫 노드로 초기화

	    // 두 리스트를 순회하며 합병
	    while (p1 != null && p2 != null) {
	        if (p1.data <= p2.data) {
	            tail.link = p1; // 첫 번째 리스트의 노드가 더 작거나 같으면 tail의 다음 노드로 설정
	            tail = p1; // tail을 첫 번째 리스트의 노드로 이동
	            p1 = p1.link; // 첫 번째 리스트의 포인터를 다음 노드로 이동
	        } else {
	            tail.link = p2; // 두 번째 리스트의 노드가 더 작으면 tail의 다음 노드로 설정
	            tail = p2; // tail을 두 번째 리스트의 노드로 이동
	            p2 = p2.link; // 두 번째 리스트의 포인터를 다음 노드로 이동
	        }
	    }

	    // 남아있는 노드를 tail의 다음 노드로 연결
	    if (p1 != null) {
	        tail.link = p1; // 첫 번째 리스트에 남아있는 노드가 있으면 연결
	    } else {
	        tail.link = p2; // 두 번째 리스트에 남아있는 노드가 있으면 연결
	    }
	}

}

public class 실습9_1정수연결리스트 {
	enum Menu { //java내에서는 0 1 2 3 4 5 로 인식
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx) //ordinal는 순서 위에 012345를 가르키는 변수
					return m;
			return null;
		}
		//"Add" 상수가 정의될 때 Menu("삽입") 생성자가 호출되어 message 필드가 "삽입"으로 초기화
		//생성자는 각 상수가 정의될 때 호출되며, 해당 상수의 message 필드를 초기화하는 역할
		//enum 상수가 언제 정의되는가를 찾아 보아야 한다 
	  //클래스(    생성자    )
		Menu(String string) { // 생성자(constructor)가 언제 호출되는지 파악하는 것이 필요하다 
			message = string;
			System.out.println("\nMenu 생성자 호출:: " + string);
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
				//n%3은 3으로 나누어 나머지를 계산한다 
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())//메뉴 출력시에 다음행에 출력하라는 의미
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();//메뉴 선택 번호로 입력된 값이 key이다 
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());//입력된 key가 음수이거나 Exit에 대한 enum 숫자보다 크면 다시 입력받는다 
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴 참조 변수일 뿐이다 
		Random rand = new Random();
		LinkedList1 l = new LinkedList1();  //Linkedlist l 객체 생성
		Scanner sc = new Scanner(System.in);
		int count = 0; //난수 생성 갯수
		int data = 0;
		do {
			switch (menu = SelectMenu()) {//Menu 생성자 호출 - menu 객체를 리턴한다 
			case Add: // 난수를 삽입하는데 올림차순으로 정렬되도록 구현
				count = 5; ///이거 맞는지 확인해야함
				for (int i =0; i < count; i++) {
					data = rand.nextInt(20);
					l.Add(data); 
				}
				break;
			case Delete:
				System.out.println("삭제할 값을 입력: ");
				data = sc.nextInt();
				boolean tag = l.Delete(data);
				System.out.println("삭제 데이터 존재여부: " + tag);
				break;
			case Show: //리스트 전체를 출력
				l.Show();
				break;
			case Search: //입력 숫자 n을 검색한다.
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + " 데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + " 데이터가 존재합니다.");
				break;
			case Merge://리스트 l과 l2를 합병하여 올림차순 정렬이 되게 구현한다 
				LinkedList1 l2 = new LinkedList1();
				for (int i =0; i < count; i++) {
					data = rand.nextInt(20);
					l2.Add(data);
				}
				l.Merge(l2);//merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
