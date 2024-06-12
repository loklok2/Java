package Chap6_Sorting;

import java.util.ArrayList;
import java.util.List;

//퀵 정렬(비재귀 버전) - 교재 버젼으로 stack을 2개 사용하는 문제가 있다 

import java.util.Scanner;

public class 실습6_10QuickSortStack {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
}

static class Point2 { // 정렬 좌표
	public int left; //왼쪽 인덱스
	public int right; //오른쪽 인덱스

	public Point2(int left, int right) {
		this.left = left;
		this.right = right;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

}

static class IntStack { // 정수형 스택
	private List<Point2> data; //좌표 저장 리스트
	private int capacity;	//스택 용량
	private int top;	//스택 꼭대기 인덱스

	public class EmptyIntStackException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public EmptyIntStackException() {
		}
	}

	// --- 실행시 예외 : 스택이 가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public OverflowIntStackException() {
		}
	}

	public IntStack(int maxlen) { //스택 생성자
		top = 0;
		capacity = maxlen;
		try {								//예외처리
			data = new ArrayList<>(maxlen);
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

	public boolean isFull() { //스택이 가득찼음
		return top == capacity;
	}

	public boolean isEmpty() { //스택이 비었음
		return top == 0;
	}

	public void push(Point2 p) { 	//스택에 데이터 추가
		if (isFull()) {
			throw new OverflowIntStackException();
		}
		data.add(p);
		top++;
	}

	public Point2 pop() {		//스택에 데이터 제거하고 리턴
		if (isEmpty()) {
			throw new EmptyIntStackException();
		}
		return data.remove(--top);
	}

}

	// --- 퀵 정렬(비재귀 버전)---//
	static void quickSort(int[] a, int left, int right) {
		//초기 스택에 정렬범위를 저장
		Point2 p = new Point2(left, right);	
		IntStack st = new IntStack(right - left + 1);
		st.push(p);
		// lstack.push(left);
		// rstack.push(right);

		while (!st.isEmpty()) { //스택이 빌때까지 반복
			p = st.pop();	//스택에서 정렬 범위 호출
			left = p.left;	// 왼쪽 인덱스설정
			right = p.right;	//오른쪽 인덱스설정
			int pl = left ;		//왼쪽커서
			int pr = right;		//오른쪽커서
			// int pl = left = lstack.pop(); // 왼쪽 커서
			// int pr = right = rstack.pop(); // 오른쪽 커서
			int x = a[(left + right) / 2]; // 피벗은 가운데 요소

			do {
			    while (a[pl] < x) pl++; //왼쪽커서 이동
			    while (a[pr] > x) pr--;	//오른쪽 커서 이동
			    if (pl <= pr) { // 왼쪽이랑 오른쪽 커서가 범위를 벗어나지 않을때 스왑
			        swap(a, pl++, pr--);
			    }
			} while (pl <= pr);	//왼쪽 커서가 오른쪽 커서보다 작거나 같을때 까지 반복	
			showData(a);
			System.out.println();	
			if (left < pr) {	//왼쪽그룹 정렬
				System.out.println("left = " + left + ", pr = " + pr);
				st.push(new Point2(left, pr));
				// lstack.push(left); // 왼쪽 그룹 범위의
				// rstack.push(pr); // 인덱스를 푸시
			}
			if (pl < right) {	//오른쪽 그룹 정렬
				System.out.println("pl = " + pl + ", right = " + right);
				st.push(new Point2(right, pl));
				// lstack.push(pl); // 오른쪽 그룹 범위의
				// rstack.push(right); // 인덱스를 푸시
			}
		}
	}

	static void showData(int[] d) {
		System.out.println();
		for (int i = 0; i < d.length; i++)
			System.out.print(d[i] + " ");
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("퀵 정렬");
		System.out.print("요솟수: ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		//배열초기화
		for (int i = 0; i < nx; i++) {
			double d = Math.random();
			x[i] = (int) (d * 20);
			// System.out.print("x[" + i + "]: ");
			// x[i] = stdIn.nextInt();
		}
		showData(x);

		quickSort(x, 0, nx -1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");
		showData(x);
	}
}
