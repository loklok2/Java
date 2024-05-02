package com.ruby.java.ch07.abstraction;
//7장 실습 구현 
abstract class Person {
	int pid;
	String name;
	int age;
	
	public Person(int p, String n, int a) {
		this.pid = p;
		this.name = n;
		this.age = a;
		
	}

	public abstract String toString();
}
class Student extends Person {
	int sid;
	String dept;
	int year;
	public Student(int pid, String name, int age, int sid, String dept, int year) {
		super(pid, name, age);
		this.sid = sid;
		this.dept = dept;
		this.age = age;
	}
	@Override
	public String toString() {
		return pid + ","+ name +","+ age + ","+ sid + "," + dept + "," + year;
	}
}
class Employee extends Person {
	int eno;
	String dept;
	float salary;
	public Employee(int pid, String name, int age, int eno, String dept, float salary) {
		super(pid, name, age);
		this.eno = eno;
		this.dept = dept;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return pid + "," + name+ "," + age + "," + eno + "," + dept + "," + salary;
	}
}
public class Ch07_test_클래스작성연습 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person arr[] = new Person[5];
        arr[0] = new Student(1, "Kim", 20, 1001, "Computer Science", 3);
        arr[1] = new Student(2, "Lee", 21, 1002, "Mathematics", 2);
        arr[2] = new Employee(3, "Park", 30, 2001, "HR", 3000);
        arr[3] = new Employee(4, "Choi", 35, 2002, "Engineering", 4000);
        arr[4] = new Student(5, "Kang", 22, 1003, "Physics", 4);
		for(Person p : arr) {
			System.out.println(p.toString());
		}

	}

}
