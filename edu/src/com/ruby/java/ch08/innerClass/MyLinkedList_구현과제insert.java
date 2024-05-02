package com.ruby.java.ch08.innerClass;


public class MyLinkedList_구현과제insert {

	private Node head = null; 					//Linkedlist 노드 head 초기화, 외부에서 접근금지 클래스 내부에서만 접근가능	

	private class Node {						// 내부 클래스 Node Linkedlist의 각 노드
		private String data;					// 노드에 저장된 데이터
		private Node link;						// 다음 노드를 가르키는 링크

		public Node(String data) {				//생성자 생성, 노드 초기화하고 저장
			this.data = data;					// 데이터 노드에 저장
			this.link = null;					// 새로운 노드의 링크는 처음에는 null로 설정
		}
	}
	public void insert(String data) {			//데이터 넣는 메서드
		Node newNode = new Node(data);			//넣을 데이터를 가진 새로운 노드 생성
		if(head == null) {						//Linkedlist가 비어 있을경우, 새노드를 head노드로 설정
			head = newNode;
			return;								//값 넣고 함수 끝
		}
			Node current = head;				// 현재 노드를 head노드로 초기화
			Node previous = null;				// 이전 노드를 추적 변수
			
		while (current != null && current.data.compareTo(data) < 0) {   	//현재 노드 따라가면서 넣을 위치 찾기
			previous = current;		// 이전노드를 현재노드로						//현재 노드의 data가 주어진 data보다 작으면 계속 이동
			current = current.link;	// 다음노드로 이동				
		}
		newNode.link = current;													// 새노드 넣기
		if(previous == null) {													// 새로운 노드의 link를 현재 노드로
			head = newNode;														// 이전 노드가 없을 경우, 새로운 노드를 head 노드로 설정
		} else {
			previous.link = newNode;											// 이전 노드의 link를 새 노드로
		}			
	}
	public void printList() {
		Node current = head;								//현재 노드를 head노드로 초기화
		while (current != null) {							// 리스트를 따라가면서 노드 data 출력
			System.out.println(current.data);
			if(current.link != null) {						//다음 노드가 있으면 ""값을 출력하여 연결표시
				System.out.println("다음");
		 }
		 current = current.link;							//다음노드로 이동
		}
		System.out.println();
	}
	public void delete(String data) {						//데이터 삭제 메서드
		if(head == null) {									//리스트가 비어있으면 안함
			return;
		}
		
		if(head.data.equals(data)) {						// head 노드의 data가 주어진 data와 일치하면 
			head = head.link;								// head를 head.link로 업데이트 하고, 첫번 째 노드 삭제
			return;											
		}
		
		Node p = head;										// p는 현재 노드를 초기화
		Node q = null;										// q는 이전 노드를 초기화
		
		while (p != null && !p.data.equals(data)) {   		//p가 null이 아니면서 p의 data가 주어진 data와 일치 하지 않으면 계속 루프
			q = p;											// q를 p로 업데이트 하면서 이전 노드 쫓아감
			p = p.link;										// p를 다음 노드 p.link로 이동
		}
		
		if(p != null) {										// 삭제할 p 노드가 존재하면
			q.link = p.link;								// 이전 노드 q의 링크를 현재노드p의 link로 업데이트
		}													// 이전 노드의 다음 노드를 현재노드의 다음으로 설정하여 p노드를 linkedlist에서 제거
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList_구현과제insert myList = new MyLinkedList_구현과제insert();      //객체생성
		myList.printList();

		myList.insert("JAVA");
		myList.insert("HTML");
		myList.insert("CSS");
		myList.insert("Javascript");
		myList.printList();
		myList.delete("CSS");
		myList.printList();
	}

}

