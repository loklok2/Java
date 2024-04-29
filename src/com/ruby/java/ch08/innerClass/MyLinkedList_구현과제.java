package com.ruby.java.ch08.innerClass;


public class MyLinkedList_구현과제 {

	private Node head = null;

	private class Node {
		private String data;
		private Node link;

		public Node(String data) {
			this.data = data;
		}
	}
	public void add(String data) {
		Node newNode = new Node(data);
		if(head == null) {
			head = newNode;
		} else {
			Node next = head;
			while(next.link != null) {
				next = next.link;
			}
			next.link = newNode;
		}

	}
	public void printList() {
		//printList() 결과는 A -> B -> C 등으로 출력한다
		if(head == null) {
			System.out.println("등록된 데이터 없습니다");
		} else {
			System.out.println("등록된 데이터는 다음과 같습니다");
			Node next = head;
		 while(next != null) {
			 System.out.println(next.data);
			 next = next.link;
		 }
		}
	}
	public void delete(String data) {
		//printList() 결과는 A -> B -> C 등으로 출력한다
		if(head == null) {
			return;
		}
		if(head.data.equals(data)) {
			head = head.link;
			return;
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList_구현과제 myList = new MyLinkedList_구현과제();
		myList.printList();

		myList.add("JAVA");
		myList.add("HTML");
		myList.add("CSS");
		myList.add("Javascript");
		myList.printList();
		myList.delete("CSS");
		myList.printList();
	}

}

