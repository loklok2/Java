package com.ruby.java.ch13.multiGen;

class Bag<T, N> {
	private T thing;
	private N name;
				//타입인자, 생성인자
	public Bag(T thing, N name) {
		this.thing = thing;
		this.name = name;
	}

	public T getThing() {
		return thing;
	}

	public void setThing(T thing) {
		this.thing = thing;
	}

	public N getName() {
		return name;
	}

	public void setName(N name) {
		this.name = name;
	}

	void showType() {
		System.out.println("T�� Ÿ���� " + thing.getClass().getName());
		System.out.println("N�� Ÿ���� " + name.getClass().getName());
	}
}

class Book {
	public String toString() {
		return "å";
	}
}

class PencilCase {
}

class Notebook {
}

public class BagTest {

	public static void main(String[] args) {

		Bag<Book, String> bag = new Bag<Book, String>(new Book(), "����");

		bag.showType();

		Book book = bag.getThing();
		String name = bag.getName();

		System.out.println("Thing is : " + book);
		System.out.println("name is : " + name);
	}
}