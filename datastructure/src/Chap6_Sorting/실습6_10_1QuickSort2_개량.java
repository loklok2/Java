// 퀵 정렬(비재귀 버전)
package Chap6_Sorting;

import java.util.EmptyStackException;

//교재 232 실습 6-10을 수정


class Point {
	private int ix; //좌표
	private int iy;	//y좌표

	public Point(int x, int y) {	//생성자
		ix = x;
		iy = y;
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}

	@Override
	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}
}

class Stack3<T>{  //제네릭 타입의 스택 클래스 
	private T[] stackArray;	//스택을 저장하는 배열
	private int top;		// 스택 꼭대기 인덱스
	private int maxSize;		//maxsize나 capacity나 별차이 없지만 maxsize가 용량으로 보기에 직관적임
	
	public Stack3(int maxSize) {
		this.maxSize = maxSize;
		stackArray = (T[]) new Object[maxSize];  // 제네릭 타입은 배열로 생성할 수가 없다. 그래서 Object타입으로 형변환해서 생성하는거임
		top = -1; // 스택은 비어있음 가장 위에 요소가 없으니까 -1로 설정
	}

	public void push(T item) {	//스택 요소 추가
        if (isFull()) {	//꽉차면 예외
            throw new StackOverflowError();
        }
        stackArray[++top] = item;
    }

    public T pop() {	// 스택 요소 제거
        if (isEmpty()) {	//비었으면 예외
            throw new EmptyStackException();
        }
        return stackArray[top--];	// 스택의 꼭대기 요소 제거
    }

    public boolean isEmpty() {
        return top == -1;			// -1이면 비었음
    }

    public boolean isFull() {
        return top == maxSize - 1;		//꼭대기가 최대 크기 -1 이면 꽉참
    }
}

public class 실습6_10_1QuickSort2_개량 {

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) { //배열의 두개의 값을 교환하는 메서드
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void quickSort(int[] a, int left, int right) {
		Stack3<Point> st = new Stack3<>(10);	//스택생성
		Point pt = new Point(left, right);		// 시작점과 끝점으로 Point 객체 생성
		st.push(pt);	// 스택에 Point 객체 추가
		while (!st.isEmpty()) {	//스택이 isEmpty 비어있지 않는 동안 반복
			Point rt = st.pop();	// 스택에서 Point객체 호출
			int pl = left = rt.getX();	// 왼쪽 인덱스 설정
			int pr = right = rt.getY();	//오른쪽 인덱스 설정
			int mp = a[(pr + pl) / 2]; //중간 값 계산

			do {
				while (a[pl] < mp)	//중간 값보다 작은거 찾기
					pl++;
				while (a[pr] > mp)	//중간 값보다 큰거 찾기
					pr--;
				if (pl <= pr)	//pl이 pr보다 작거나 같으면
					swap(a, pl++, pr--); //두개 교환
			} while (pl <= pr);	//pl이 pr보다 작거나 같을 때 까지 반복
			System.out.println("left = " + left + "right = " + right);
			for (int i = left; i <= right; i++)
				System.out.print(" " + a[i]);
			System.out.println();
			if (left < pr) { //왼쪽부분 배열이 있으면
				Point pt2 = new Point(left, pr);	//왼쪽부분 배열의 시작과 끝점에 스택추가
				st.push(pt2);

			}
			if (pl < right) {
				Point pt2 = new Point(pl, right);	//오른쪽부분 배열의 시작과 끝점에 스택추가
				st.push(pt2);

			}

		}
	}
	static void showData(int[] d) {
		System.out.println();
	    for (int i = 0; i < d.length; i++)
	        System.out.print(d[i] + " ");
	}
	public static void main(String[] args) {
		int nx = 10;
		int[] x = new int[10];
		for (int ix = 0; ix < 10; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 20);
		}
		showData(x);

		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");
		showData(x);
		
	}
}
