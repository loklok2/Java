package com.ruby.java.ch14;
//

 //
import java.util.ArrayList;
/*
 * public interface Comparator<T>{
 *    int compare(T 01, T 02);
 *    }
 *    
 * public interface Comparable<T> {
 *   public int compareTo(T o);
 *   }   
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
class Student {
	String sno;
	String sname;
	
	public Student(String sno, String sname) {
		this.sno = sno;
		this.sname = sname;
	}
	public String getSno() {
		return this.sno;
	}
	public String getSname() {
		return this.sname;
	}
	public String toString() {
		return sno + "" + sname;
	}
	
}
public class 구현과제14장_람다식 {
	
	static void sortStudents(String msg, Object[]data) {
		System.out.println(msg + " : ");
		for(Object obj : data) {
			System.out.println(obj);
		}
	}
	static void showStudents(String msg, Object[]data) {
		System.out.println(msg + " : ");
		for(Object obj : data) {
			System.out.println(obj);
		}
	}
	static void showStudents(String msg, List<Student>l) {
		System.out.println(msg + " : ");
		for(Student st : l)
			System.out.println(st); //element 하나씩 가르킴
		
	}
	public static void main(String[] args) {
		Comparator<Student> compSno = new Comparator<Student>(){
			@Override
			public int compare(Student s1, Student s2) {
				int sno1 = Integer.parseInt(s1.sno);
				int sno2 = Integer.parseInt(s2.sno);
				return Integer.parseInt(s1.sno) - Integer.parseInt(s2.sno);
			}
		};
        Student[] sArray = new Student[5];
        // Student 객체 생성 및 배열에 할당
        sArray[0] = new Student("121", "Alice");
        sArray[1] = new Student("33", "Bob");
        sArray[2] = new Student("9", "Charlie");
        sArray[3] = new Student("2", "John");
        sArray[4] = new Student("39", "Bobby");
        // ArrayList로 변환
        ArrayList<Student> sList = new ArrayList<>();
        for (Student student : sArray) {
            sList.add(student);
        }
        showStudents("sno 정렬전", sArray);
        //문제1: Arrays.sort()를 사용한 sArray 정렬 - sno 사용, 익명클래스 사용
        //Arrays.sort(sArray);
        Arrays.sort(sArray, compSno);
        showStudents("sno 정렬후", sArray);
        
        showStudents("sname 정렬전", sArray);
        //문제2: Arrays.sort()를 사용한 sArray 정렬 - sname 사용, 익명 객체 사용
        Arrays.sort(sArray, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s1.sname.compareTo(s2.sname);
			}
		});
        showStudents("sname 정렬후", sArray);
        
        showStudents("sno 정렬전", sList);
        //문제3: Collections.sort()를 사용한 sList 정렬 - sno 사용, 람다식 사용 /책 677p 참고
        Collections.sort(sList, (s1,s2)-> {
        	int sno1 = Integer.parseInt(s1.sno);
			int sno2 = Integer.parseInt(s2.sno);
        	return Integer.compare(sno1,sno2);	
        });
        showStudents("sno 정렬후", sList);
    
        showStudents("sname 정렬전", sList);
        //문제4: Collections.sort()를 사용한 sList 정렬 - sname 사용
        Collections.sort(sList, (s1,s2)->s1.sname.compareTo(s2.sname));
        showStudents("sname 정렬후", sList);
        
//        showStudents("sno 정렬전", sArray);
//        sortStudents()
//        //문제5: sortStudents()를 사용한 sArray 정렬 - sno 사용
//        showStudents("sname 정렬후", sArray);
	}

}