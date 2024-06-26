package Chap5_Recursive;
/*
 * 미로 찾기 문제
 * 플라토의 미로 찾기 문제 설명 자료 학습
 * int input[12][15] 테이블에서 입구는 (0,0)이며 출구는 (11,14)임
 * 미로 테이블 (12,15)을 상하좌우 울타리를 친 maze[14][17]을 만들고 입구는 (1,1)이며 출구는(12,15)
 * non-recursive알고리즘 -> stack을 사용한 backtracking 구현
 * 정답: 지나간 경로만 그림 표시
 */

import java.util.ArrayList;
import java.util.List;


//23.2.16 수정
//23.2.24: 객체 배열 초기화, static 사용, 내부 클래스와 외부 클래스 사용 구분을 못하는 문제 => 선행 학습 필요

//좌표를 나타내는 Items 클래스 정의

enum Directions {N, NE, E, SE, S, SW, W, NW}

class Items {
	int x;
	int y;
	int dir;

	public Items(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.dir = d;
	}

	@Override
	public String toString() {
		return "x = " + x + ", y = " + y + ", dir = " + dir;
	}
}

class Offsets {
	int a;
	int b;

	public Offsets(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

class StackList {
	private List<Items> data; // 스택용 배열
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 실행시 예외 : 스택이 비어있음 ---//
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

	// --- 생성자(constructor) ---//
	public StackList(int maxlen) {
		top = 0;
		capacity = maxlen;
		try {
			data = new ArrayList<>(0); // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	// --- 스택에 x를 푸시 ---//
	public void push(Items p) throws OverflowIntStackException {
		if (top >= capacity) // 스택이 가득 참
			throw new OverflowIntStackException();
		data.add(p);
		top++;
		return;
	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Items pop() throws EmptyIntStackException {
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException();
		return data.remove(--top);
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Items peek() throws EmptyIntStackException {
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException();
		return data.get(top - 1);
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(벌견하지 못하면 –1)를 반환 ---//
	public int indexOf(Items x) {
		for (int i = top - 1; i >= 0; i--) // 정상 쪽에서 선형검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("스택이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.println((i+1) + ": " + data.get(i) + " ");
		}
	}
}

public class Test_실습5_2미로찾기문제 {

	static Offsets[] moves = new Offsets[8];// static을 선언하는 이유를 알아야 한다

	public static void path(int[][] maze, int[][] mark, int ix, int iy) {

			mark[1][1] = 1;
			StackList st = new StackList(50);
			Items temp = new Items(0, 0, 0);//N :: 0
			temp.x = 1;
			temp.y = 1;
			temp.dir = 2;//E:: 2
			mark[temp.x][temp.y] = 2;//미로 찾기 궤적은 2로 표시
			st.push(temp);
			int i = temp.x;
			int j = temp.y;
			int d = temp.dir;
			while (!st.isEmpty()) {
				if (i == ix && j == iy) {
					st.dump();
					return;
				}
				int g = i + moves[d].a;
				int h = j + moves[d].b;
				
				if (maze[g][h] == 0 && mark[g][h] == 0) {
					mark[i][j] = 2;
					maze[i][j] = 1;
					st.push(new Items(i, j, d));
					i = g;
					j = h;
					d = 0;
				}
				else {
					d++;
					if (d == 8) {
						mark[i][j] = 1;
						maze[i][j] = 0;
						if (!st.isEmpty()) {
							temp = st.pop();
							i = temp.x;
							j = temp.y;
							d = temp.dir + 1;
						}
					}
				}
			}
			
			System.out.println("no path in maze ");
		}

	static void show(int[][] d, int row, int col) {
		for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= col; j++) {
				System.out.print(d[i][j] + " ");

			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] maze = new int[14][17];
		int[][] mark = new int[14][17];

		int input[][] = { // 12 x 15
				{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 }, 
				{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 }, 
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 }, 
				{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 }, 
				{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 } };
		for (int ia = 0; ia < 8; ia++)
			moves[ia] = new Offsets(0, 0);// 배열에 offsets 객체를 치환해야 한다.
		moves[0].a = -1;
		moves[0].b = 0;
		moves[1].a = -1;
		moves[1].b = 1;
		moves[2].a = 0;
		moves[2].b = 1;
		moves[3].a = 1;
		moves[3].b = 1;
		moves[4].a = 1;
		moves[4].b = 0;
		moves[5].a = 1;
		moves[5].b = -1;
		moves[6].a = 0;
		moves[6].b = -1;
		moves[7].a = -1;
		moves[7].b = -1;
		// Directions d;
		// d = Directions.N;
		// d = d + 1;//java는 지원안됨
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				if(i == 0 || j == 0 || i == 13 || j == 16) maze[i][j]=1;
				else maze[i][j]=input[i-1][j-1];
				mark[i][j]=0;
				// input[][]을 maze[][]로 변환
			}
		}
		
		System.out.println("maze[12,15]::");
		show(maze, 13, 16);
		System.out.println("mark::");
		show(mark, 13, 16);
		path(maze, mark, 12, 15);
		System.out.println("maze[12,15]::");
		show(maze, 13, 16);
		System.out.println("mark::");
		show(mark, 13, 16);
	}
}