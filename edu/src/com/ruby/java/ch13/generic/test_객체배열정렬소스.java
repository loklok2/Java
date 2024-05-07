package com.ruby.java.ch13.generic;
/*
 * 객체를 정렬하는 코드 완성 
 * 스트링 배열을 정렬
 * 객체 배열을 정렬 
 */
import java.util.Arrays;
import java.util.Comparator;


class Student{
	String sno;
	String sname;
	
	public Student(String sno, String sname) {
		this.sno = sno;
		this.sname = sname;
	}
//	@Override
//	public int hashCode() {
//		return Integer.parseInt(sno);
//	}
	public String getSno() {
		return this.sno;
	}
	public String getSname() {
		return this.sname;
	}
	
	public String toString() {
		return sno + " " + sname; 
		
	}
}
class SnoComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
    	int sno1 = Integer.parseInt(s1.sno);
    	int sno2 = Integer.parseInt(s2.sno);
        
        return Integer.compare(sno1,sno2);
    }
}
public class test_객체배열정렬소스 {
															
	static void showData(String msg, Object[]data) {
		System.out.println(msg + " :");
		for(Object obj : data) {
			System.out.println(obj);
		}
	}
	public static void main(String[] args) {
		String [] stringData = {"apple","grape","blueberry","banana"};
		Student [] data = new Student[]{
				new Student("12", "홍길동"),
				new Student("121", "홍길순"),
				new Student("213", "홍길춘"),
				new Student("9", "홍길홍")
		};
		showData("정렬전", stringData);
		Arrays.sort(stringData);
		showData("정렬후", stringData);
		showData("정렬전", data);
		Arrays.sort(data, new SnoComparator());   
		showData("정렬후", data);
	}

}
