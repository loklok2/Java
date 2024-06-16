package test;

//해가 256개 확인 필요
import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking
//모든 해가 나오는 버젼 만들기 
/*
 * 체스판은 8 x 8 체스의 기물: king/가로세로대각선 1칸만 이동, queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
 * Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
 * pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능 체스판 최대 배치 문제 : king/16,
 * Queen/8, rook/8, bishop/?, knight/? rook 2개/a, h, knight 2개/b, g, bishop
 * 2개/c, f, queen 1개/black queen은 black 칸에, 폰 8개
 */
class Point {
    // (x, y) 좌표를 나타내는 Point 클래스
    private int ix;
    private int iy;

    public Point(int x, int y) {
        ix = x;
        iy = y;
    }

    public int getIx() {
        return ix;
    }

    public int getIy() {
        return iy;
    }

    @Override
    public String toString() {
        return "(" + ix + ", " + iy + ")";
    }

    @Override
    public boolean equals(Object p) {
        if (this == p) {
            return true;
        }
        if (p == null || getClass() != p.getClass()) {
            return false;
        }
        Point other = (Point) p;
        return ix == other.ix && iy == other.iy;
    }
}

class Stack4 {
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

    private List<Point> data; // 스택 요소를 저장할 ArrayList
    private int capacity; // 스택의 최대 용량
    private int top; // 최상위 요소의 인덱스

    // 주어진 용량으로 스택을 초기화하는 생성자
    public Stack4(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>(capacity);
        this.top = 0;
    }

    // 스택에 요소를 추가하는 push 연산
    public boolean push(Point x) throws OverflowGenericStackException {
        if (isFull()) {
            throw new OverflowGenericStackException("스택이 가득 찼습니다.");
        }
        top++;
        return data.add(x);
    }

    // 스택에서 최상위 요소를 제거하고 반환하는 pop 연산
    public Point pop() throws EmptyGenericStackException {
        if (isEmpty()) {
            throw new EmptyGenericStackException("스택이 비어 있습니다.");
        }
        return data.remove(--top);
    }

    // 스택의 최상위 요소를 반환하는 peek 연산
    public Point peek() throws EmptyGenericStackException {
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
    public int indexOf(Point x) {
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

public class Test_실습5_1QueenEight_구현과제_최종수정본 {

    // 백트래킹을 사용하여 Eight Queen 문제를 해결하는 메서드
    public static void EightQueen(int[][] d) throws Stack4.EmptyGenericStackException {
        int count = 0; // 배치된 퀸의 개수
        int numberSolutions = 0; // 가능한 해의 수
        int ix = 0, iy = 0; // 현재 행 ix, 열 iy
        Stack4 st = new Stack4(100); // 위치를 저장할 스택
        Point p = new Point(ix, iy); // 현재 위치를 나타내는 Point 객체 생성
        d[ix][iy] = 1; // 현재 위치에 퀸 배치
        count++; // 퀸 개수 증가
        ix++; // 다음 행으로 이동
        st.push(p); // 스택에 현재 위치 추가

        while (true) { // 열을 이동하면서 퀸을 배치하는 작업을 반복
            while (ix < 8) { // 8행 이전에만 실행
                iy = nextMove(d, ix, iy); // 다음 이동할 열 결정
                if (iy < 0) { // 다음 이동할 열이 없는 경우
                    if (st.isEmpty()) { // 스택이 비어 있는지 확인
                        System.out.println("총 가능한 해의 개수 : " + numberSolutions); // 가능한 해의 총 개수 출력
                        return;
                    }
                    p = st.pop(); // 스택에서 위치 꺼내기
                    ix = p.getIx();
                    iy = p.getIy();
                    d[ix][iy] = 0; // 해당 위치에 있는 퀸 제거
                    count--;
                    iy++; // 다음 열로 이동
                } else { // 다음 이동할 열이 있는 경우
                    p = new Point(ix, iy); // 해당 위치에 퀸 배치
                    st.push(p); // 스택에 추가
                    d[ix][iy] = 1; // 퀸 배치 표시
                    count++;
                    ix++; // 다음 행으로 이동
                    iy = 0; // 첫 번째 열부터 시작
                }
            }
            numberSolutions++; // 해의 수 증가
            showQueens(d); // 현재 퀸의 배치 출력
            p = st.pop(); // 스택에서 위치 꺼냄
            ix = p.getIx();
            iy = p.getIy();
            d[ix][iy] = 0; // 해당 위치에 있는 퀸 제거
            count--;
            iy++; // 다음 열로 이동
        }
    }
       
            

    // 배열 d에서 행 crow에 퀸을 배치할 수 있는지 확인하는 메서드
    public static boolean checkRow(int[][] d, int crow) {
        for (int j = 0; j < d.length; j++) { // 행 crow의 모든 열을 검사
            if (d[crow][j] == 1) // 열에 퀸이 이미 존재할 경우
                return false; // 배치할 수 없음
        }
        return true; // 행 crow에 퀸을 배치 가능
    }

    // 배열 d에서 열 ccol에 퀸을 배치할 수 있는지 확인하는 메서드
    public static boolean checkCol(int[][] d, int ccol) {
        for (int i = 0; i < d[0].length; i++) { // 열 ccol의 모든 행을 검사
            if (d[i][ccol] == 1) // 행에 퀸이 이미 존재할 경우
                return false; // 배치할 수 없음
        }
        return true; // 열 ccol에 퀸을 배치 가능
    }

    // 배열 d에서 행 cx, 열 cy에 퀸을 남서, 북동 대각선으로 배치할 수 있는지 확인하는 메서드
    public static boolean checkDiagSW(int[][] d, int cx, int cy) {
        int x = cx, y = cy;
        // 남서 방향 대각선 검사
        while (x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
            if (d[x][y] == 1)
                return false; // 이미 퀸이 배치된 경우
            x++; // 행 증가
            y--; // 열 감소
        }
        
        x = cx; y = cy;
        // 북동 방향 대각선 검사
        while (x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
            if (d[x][y] == 1)
                return false; // 이미 퀸이 배치된 경우
            x--; // 행 감소
            y++; // 열 증가
        }
        return true; // 배치 가능
    }

    // 배열 d에서 행 cx, 열 cy에 퀸을 남동, 북서 대각선으로 배치할 수 있는지 확인하는 메서드
    public static boolean checkDiagSE(int[][] d, int cx, int cy) {
        int x = cx, y = cy;
        // 남동 방향 대각선 검사
        while (x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
            if (d[x][y] == 1)
                return false; // 이미 퀸이 배치된 경우
            x++; // 행 증가
            y++; // 열 증가
        }
        
        x = cx; y = cy;
        // 북서 방향 대각선 검사
        while (x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
            if (d[x][y] == 1)
                return false; // 이미 퀸이 배치된 경우
            x--; // 행 감소
            y--; // 열 감소
        }
        return true; // 배치 가능
    }

    // 배열 d에서 (x,y)에 퀸을 배치할 수 있는지 확인하는 메서드
    public static boolean checkMove(int[][] d, int x, int y) {
        return checkRow(d, x) && checkCol(d, y) && checkDiagSW(d, x, y) && checkDiagSE(d, x, y);
    }

    // 배열 d에서 현재 위치(row,col)에 대하여 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴하는 메서드
    public static int nextMove(int[][] d, int row, int col) {
        for (int j = col; j < d.length; j++) { // 현재 열부터 끝까지 검사
            if (checkMove(d, row, j)) { // 이동 가능한 열인 경우
                return j; // 해당 열 반환
            }
        }
        return -1; // 이동할 열이 없음
    }

    // 배열을 출력하는 메서드
    static void showQueens(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1) {
                    System.out.print("Q "); // Queen이 있는 위치에는 'Q' 출력
                } else {
                    System.out.print(data[i][j] + " "); // Queen이 없는 위치에는 값 그대로 출력
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // main 메서드
    public static void main(String[] args) throws Stack4.EmptyGenericStackException {
        int row = 8, col = 8; // 행과 열의 크기 설정
        int[][] data = new int[row][col]; // 8 x 8 행열 배열 선언 초기화

        for (int i = 0; i < data.length; i++) // 행열의 요소를 0으로 초기화
            for (int j = 0; j < data[0].length; j++)
                data[i][j] = 0; // 0으로 초기화

        EightQueen(data); // EightQueen() 메서드 호출
    }
}