package com.ruby.java.ch13.boundGen;

import chapter4.Array;

class Bag<T extends Solid> {
  private T thing;
  private String owner;

  public Bag(T thing) {
    this.thing = thing;
  }

  public T getThing() {
    return thing;
  }

  public void setThing(T thing) {
    this.thing = thing;
  }

  void showType() {
    System.out.println("T의 타입은 " + thing.getClass().getName());
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  boolean isSameOwner(Bag<?> obj) {
    if(this.owner.equals(obj.getOwner()))
      return true;
    return false;
  }
}

class Solid { }
class Liquid{ }

class Book extends Solid{ }
class PencilCase extends Solid{ }
class Notebook extends Solid { }

class Water extends Liquid{ }
class Coffee extends Liquid{ }


public class BagTest {

  public static void main(String[] args) {
    Bag<Book> bag = new Bag<>(new Book());
    Bag<PencilCase> bag2 = new Bag<>(new PencilCase());

    bag.setOwner("홍길동");
    bag2.setOwner("홍길동");

    boolean result = bag.isSameOwner(bag2);
    if(result) System.out.println("소유자가 동일합니다.");
    else System.out.println("소유자가 다릅니다.");
  }
}
