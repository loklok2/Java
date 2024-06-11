package test;
/*
 * 미로 찾기 문제
 * 플라토의 미로 찾기 문제 설명 자료 학습
 * int input[12][15] 테이블에서 입구는 (0,0)이며 출구는 (11,14)임
 * 미로 테이블 (12,15)을 상하좌우 울타리를 친 maze[14][17]을 만들고 입구는 (1,1)이며 출구는(12,15)
 * stack을 사용한 backtracking 구현
 */

import java.util.ArrayList;
import java.util.List;

import test.Stack4.EmptyGenericStackException;
import test.Stack4.OverflowGenericStackException;

//23.2.16 수정
//23.2.24: 객체 배열 초기화, static 사용, 내부 클래스와 외부 클래스 사용 구분을 못하는 문제 => 선행 학습 필요
enum Directions {N, NE, E, SE, S, SW, W, NW}
class Items { // 이동할때 나타내는 좌표 
	int x;	
	int y;
	int dir;
	
		Items(int x, int y, int dir){
			this.x = x;	
			this.y = y;
			this.dir = dir;
		}
}
class Offsets {	// 이동할때 각 방향의 좌표변화 저장
	int a;
	int b;
	
		Offsets(int a, int b){
			this.a = a;
			this.b = b;
		}

}
class StackList {
    // ArrayList를 사용한 사용자 정의 스택 구현

    // 스택 연산을 위한 예외 클래스들
    public class EmptyGenericStackException extends Exception {
        private static final long serialVersionUID = 1L;

        public EmptyGenericStackException(String message) {
            super(message);
        }
    }

    public class OverflowGenericStackException extends RuntimeException {
        public OverflowGenericStackException(String message) {
            super(message);
        }
    }

    private List<Items> data; // 스택 요소를 저장할 ArrayList
    private int capacity; // 스택의 최대 용량
    private int top; // 최상위 요소의 인덱스

    // 주어진 용량으로 스택을 초기화하는 생성자
    public StackList(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>(capacity);
        this.top = 0;
    }

    // 스택에 요소를 추가하는 push 연산
    public boolean push(Items x) throws OverflowGenericStackException {
        if (isFull()) {
            throw new OverflowGenericStackException("스택이 가득 찼습니다.");
        }
        top++;
        return data.add(x);
    }

    // 스택에서 최상위 요소를 제거하고 반환하는 pop 연산
    public Items pop() throws EmptyGenericStackException {
        if (isEmpty()) {
            throw new EmptyGenericStackException("스택이 비어 있습니다.");
        }
        return data.remove(--top);
    }

    // 스택의 최상위 요소를 반환하는 peek 연산
    public Items peek() throws EmptyGenericStackException {
        if (isEmpty()) {
            throw new EmptyGenericStackException("스택이 비어 있습니다.");
        }
        return data.get(top - 1);
    }

    // 스택의 모든 요소를 제거하는 clear 연산
    public void clear() {
        top = 0;
    }

    // 특정 요소의 인덱스를 반환하는 indexOf 연산
    public int indexOf(Items x) {
        for (int i = top - 1; i >= 0; i--) {
            if (data.get(i).equals(x)) {
                return i;
            }
        }
        return -1;
    }

    // 용량 및 스택 크기에 대한 getter 메서드들
    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return top;
    }

    // 스택이 비어 있는지 여부를 확인하는 isEmpty 연산
    public boolean isEmpty() {
        return top <= 0;
    }

    // 스택이 가득 찼는지 여부를 확인하는 isFull 연산
    public boolean isFull() {
        return top >= capacity;
    }

    // 스택의 모든 요소를 출력하는 dump 연산
    public void dump() throws EmptyGenericStackException {
        if (isEmpty()) {
            throw new EmptyGenericStackException("스택이 비어 있습니다.");
        } else {
            for (int i = 0; i < top; i++) {
                System.out.print(data.get(i) + " ");
            }
            System.out.println();
        }
    }
}

public class Test_실습5_2미로찾기문제 {
	static Offsets[] moves = new Offsets[8];//static을 선언하는 이유를 알아야 한다
	StackList st = new StackList(100);
	void path(int maze[][], int mark[][], int m, int p) {//m = 12, p = 15 
	st.push(new Items(1,1,2));//출발점 (1,1), 이동 방향 dir = 2(2는 동쪽) 을 스택에 push
	while (!st.isEmpty()) {
		Items item = st.pop(); //스택에서 좌표가져오기
		//(i,j,dir) = coordinates and direction deleted from top of stack;
		int i = item.x;
		int j = item.y;
		int dir = item.dir;
		//현재 위치 (i,j)에 대하여 mark[][]을 1로 설정
		mark[i][j] = 1;
		
		while (dir < 8) {//8가지 방향중에서 남은 방향에 대하여
			//(g,h) = coordinates of next move;
			//현재 위치 (i,j)에 대하여 이동 방향 계산
			int g = i + moves[dir].a;
			int h = j + moves[dir].b;
			//만약에 출구에 도착했을 경우 //success;
			if ((g == m) && (h == p)) {
			//(i,j)와 (g,h)에 대하여 mark 표시
				mark[g][h] = 1;
				return;
			}
			//벽이 아니고, 이전에 온적이 없는 곳이라면
			if (maze[g][h] == 0 && mark[g][h] == 0) {
				mark[g][h] = 1;
				//다음 이동할 방향설정해서 push
				//여기서부터는 동쪽으로 시도하니까 다음부터는 서쪽으로
				st.push(new Items(i, j, (dir + 7) %8));
				//다음 이동할 좌표 설정
				i = g;
				j = h;
				dir = 0;// 북쪽부터 시도
				//dir = next direction to try;
				//add (i,j,dir) to top of stack;
				// i = g; j = h; dir = N;
			} else {
	          dir++; //try next direction
			}
        }

        // 현재 위치의 mark를 취소
        mark[i][j] = 0;
    }

    // 출구를 찾지 못한 경우
    System.out.println("No path found");
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
				{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }};
		
		moves[0].a = -1;	moves[0].b = 0;
		moves[1].a = -1;	moves[1].b = 1;
		moves[2].a = 0;		moves[2].b = 1;
		moves[3].a = 1;		moves[3].b = 1;
		moves[4].a = 1;		moves[4].b = 0;
		moves[5].a = 1;		moves[5].b = -1;
		moves[6].a = 0;		moves[6].b = -1;
		moves[7].a = -1;	moves[7].b = -1;
		//Directions d;
		//d = Directions.N;
		//d = d + 1;//java는 지원안됨
		//maze 배열 초기화
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				maze[i+1][j+1] = input[i][j];
				//input[][]을 maze[][]로 복사
			}
		}

		show("maze[12,15]::", maze);
		show("mark[12,15]::", mark);

		path(maze, mark, 12, 15);
		show("maze[12,15]::", maze);
		show("mark[12,15]::", mark);


	}
}
