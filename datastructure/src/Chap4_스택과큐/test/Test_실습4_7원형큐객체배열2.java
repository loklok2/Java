package Chap4_스택과큐;

import java.util.Random;
import java.util.Scanner;


class Point5 {
    private int ix;
    private int iy;

    // 생성자
    public Point5(int x, int y) {
        this.ix = x;
        this.iy = y;
    }

    @Override
    public String toString() {
        return "(" + ix + ", " + iy + ")";
    }
}


class CircularQueue {
    private static int QUEUE_SIZE = 0;  //원형 큐 크기
    private Point5[] que;				//큐 저장배열
    private int front, rear;			
    private boolean isEmptyTag;			//큐가 비었는지 플래그

    //--- 실행시 예외: 큐가 비어있음 ---//
    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException(String msg) {
            super(msg);
        }
    }

    //--- 실행시 예외: 큐가 가득 찼음 ---//
    public static class OverflowQueueException extends RuntimeException {
        public OverflowQueueException(String msg) {
            super(msg);
        }
    }

   
    public CircularQueue(int count) {   // 생성자
        this.QUEUE_SIZE = count;		//큐 크기 
        this.que = new Point5[count];	// 큐 배열 초기화
        this.front = 0;					// 큐 맨앞 초기화
        this.rear = 0;					// 큐 맨뒤 초기화
        this.isEmptyTag = true;			// 큐가 비어있음 플래그 true
    }

   
    void push(Point5 it) throws OverflowQueueException {  //데이터 추가
        if (isFull()) { //큐 가득차면 예외 
            throw new OverflowQueueException("push: circular queue overflow");
        }
        que[rear] = it;	// 큐 맨뒤에 데이터 추가
        rear = (rear + 1) % QUEUE_SIZE;	// rear 인덱스 추가
        isEmptyTag = false;		//rear데이터가 추가 됬으니까 큐가 안비었음 플래그 false
    }

  
    Point5 pop() throws EmptyQueueException {		//데이터 삭제
        if (isEmpty()) {	//큐가 비었으면 예외
            throw new EmptyQueueException("pop: circular queue underflow");
        }
        Point5 data = que[front];	//큐 맨앞 데이터 추가
        que[front] = null; // 삭제된 자리는 null로 초기화
        front = (front + 1) % QUEUE_SIZE;	// front 부분 데이터 앞으로 
        if (front == rear) {	//만약 front와 rear가 
            isEmptyTag = true;		// 그럼 큐가 비었다
        }
        return data;		//제거된 데이터 리턴
    }

    // 큐 초기화
    void clear() {
        front = 0; //front 초기화
        rear = 0;	//rear 초기화	
        isEmptyTag = true;	// 큐가 비었음 플래그 true
    }

    // 큐의 크기 반환
    public int getCapacity() {
        return QUEUE_SIZE; 		//생성자 참고, 큐의 전체크기 리턴
    }

    // 큐에 쌓여 있는 데이터 개수 반환
    public int size() {
        if (isEmptyTag) {		//생성자 참고,만약 큐가 비었으면 
            return 0;			// 데이터 갯수는 0 리턴
        } else if (front <= rear) {	 //만약에 front가 rear보다 앞에 있다면
            return rear - front;	// 데이터 갯수 계산	
        } else {					// front가 rear보다 뒤라면
            return (rear + QUEUE_SIZE - front) % QUEUE_SIZE;	//원형큐 데이터 계산법 리턴
        }
    }

    // 큐가 비어있는지 확인
    public boolean isEmpty() {
        return isEmptyTag;		// 비었으면 플래그 true
    }

    // 큐가 가득 찼는지 확인
    public boolean isFull() {
        return !isEmptyTag && front == rear;	//큐가 비어있지 않고, front와 rear가 같다면 풀
    }

    // 큐의 모든 데이터 출력
    public void dump() throws EmptyQueueException {
        if (isEmpty()) { //큐가 비었다는 예외
            throw new EmptyQueueException("dump: queue empty");
        } else {
            int i = front;	//시작인덱스는 front로 초기화
            while (i != rear) {	//front부터 rear 까지 돌고
                System.out.print(que[i] + " ");	//데이터 출력
                i = (i + 1) % QUEUE_SIZE;	// 인덱스 원형으로 
            }
            System.out.println();
        }
    }

    // 큐의 첫 번째 데이터 반환
    public Point5 peek() throws EmptyQueueException {
        if (isEmpty()) {	//큐가 비었다면 예외
            throw new EmptyQueueException("peek: queue empty");
        }
        return que[front]; // 큐 첫 데이터 front 리턴
    }
}

// 메인 클래스: 큐를 테스트하는 프로그램
public class Test_실습4_7원형큐객체배열2 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        CircularQueue oq = new CircularQueue(4); // 최대 4개를 인큐할 수 있는 큐
        Random random = new Random();
        int rndx = 0, rndy = 0;
        Point5 p = null;

        while (true) {
            System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
            System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
            System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5) clear  (0)종료: ");
            int menu = stdIn.nextInt();
            if (menu == 0)
                break;

            switch (menu) {
                case 1: // 인큐
                    rndx = random.nextInt(20);
                    rndy = random.nextInt(20);
                    System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
                    p = new Point5(rndx, rndy);
                    try {
                        oq.push(p);
                        System.out.println("push: size() = " + oq.size());
                    } catch (CircularQueue.OverflowQueueException e) {
                        System.out.println("queue이 full입니다." + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case 2: // 디큐
                    try {
                        p = oq.pop();
                        System.out.println("pop: size() = " + oq.size());
                    } catch (CircularQueue.EmptyQueueException e) {
                        System.out.println("queue이 비어있습니다." + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case 3: // 피크
                    try {
                        p = oq.peek();
                        System.out.println("피크한 데이터는 " + p + "입니다.");
                    } catch (CircularQueue.EmptyQueueException e) {
                        System.out.println("queue이 비어있습니다." + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case 4: // 덤프
                    try {
                        oq.dump();
                    } catch (CircularQueue.EmptyQueueException e) {
                        System.out.println("queue이 비어있습니다." + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case 5: // clear
                    oq.clear();
                    System.out.println("큐가 초기화되었습니다.");
                    break;
            }
        }

        stdIn.close();
    }
}
	