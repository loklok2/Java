package com.ruby.java.ch08.innerClass;

public class MyLinkedList {

 private Node head = null;
 private Node tail = null;
 
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
 
 public void print() {
	 if(head == null) {
		 System.out.println("등록된 데이터가 없습니다.");
	 } else {
		 System.out.println("등록된 데이터는 다음과 같습니다.");
		 Node next = head;
		 while(next != null) {
			 System.out.println(next.data);
			 next = next.link;
		 }
	 }
 }

public void printReverse() {
	System.out.println("-".repeat(40));
	System.out.println("이번에는 거꾸로 출력합니다");
	System.out.println("-".repeat(40));
}
 
 
 
 
}