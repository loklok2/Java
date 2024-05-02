package com.ruby.java.ch07.abstraction;

abstract class Employee {
	String name;
	int salary;

	public abstract void calcSalary(); // public abstrack void 함수(); 추상클래스 
											// 일반클래스는 객체 생성이 가능하나, 추상클래스는 객체 생성 불가
	public abstract void calcBonus();		// 이유는 추상클래스는 함수body가 없어서 불가능
}

class Salesman extends Employee {		
	public void calcSalary() {
		System.out.println("Salesman 급여 =  기본급 + 판매수당");
	}

	public void calcBonus() {
		System.out.println("Salesman ���ʽ� = �⺻�� * 12 * 4");
	}
}

class Consultant extends Employee {
	public void calcSalary() {
		System.out.println("Consultant �޿� = �⺻�� + ������ Ư�� ����");
	}

	public void calcBonus() {
		System.out.println("Consultant ���ʽ� = �⺻�� * 12 * 2");
	}
}

abstract class Manager extends Employee {
	public void calcSalary() {
		System.out.println("Manager �޿� = �⺻�� + �� ���� ����");
	}

}

class Director extends Manager {
	public void calcBonus() {
		System.out.println("Director ���ʽ� = �⺻�� * 12 * 6");
	}
}

public class HRSTest {
	public static void main(String[] args) {
		Salesman s = new Salesman();
		Consultant c = new Consultant();
		Director d = new Director();
//		Manager m = new  Manager(;) // 오류코드 설명 아직 Manager는 추상클래스다, 인스턴스를 만들지 못한다
		s.calcSalary();					// 추상 클래스는 추상 메소드가 포함>함수 boby가 없음
		c.calcSalary();
		d.calcSalary();
	}
}
