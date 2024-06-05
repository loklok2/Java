package test;
/*
 * 실습 6번: 원형 큐로서 큐에 Point 객체를 배열로 저장
 * 실습4_3_2 정수 원형 큐 배열을 객체 버젼으로 구현하는 것임
 */


import java.util.Random;
import java.util.Scanner;

class Point5 {
	private int ix;
	private int iy;
	
	public Point5(int x, int y) {
		this.ix = x;
		this.iy = y;
	}
	public int getX() {
		return ix;
	}
	public int getY() {
		return iy;
	}
	public void setX(int x) {
		this.ix = x;
	}
	public void setY(int y) {
		this.iy = y;
	}
	public String toString() {
		return "(" + ix + "," + iy + ")";
	}
}


class CircularQueue {
	int QUEUE_SIZE = 0;
	Point5[] que;//배열로 객체원형 큐 구현
	int front, rear;
	boolean isEmptyTag;
	//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String string) {
		}
	}
	//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException{
		public OverflowQueueException(String string) {
		}
	}
	

	public CircularQueue(int count) {		//생성자
		QUEUE_SIZE = count;
		que = new Point5[QUEUE_SIZE];  //큐 배열길이
		front = rear = 0;				// 빈 큐로 초기화
		isEmptyTag = true;				// 빈큐면 트루  아니면 폴스
	}
	void push(Point5 it) throws OverflowQueueException{
		if(isFull()) { //큐가 꽉참
			throw new OverflowQueueException("push: circular queue overflow");	
		}
		if(isEmptyTag) { //큐가 비어있으면
			isEmptyTag = false; //큐가 비었다	
		}
		rear = (rear + 1) % que.length; //원형큐 특성 rear를 다음으로 이동
		que[rear] = it; //rear에 요소 추가
	}

	Point5 pop() throws EmptyQueueException{
		if(isEmpty()) { //큐가 비었다
			throw new EmptyQueueException("pop: circular queue empty"); 
		}
		Point5 remove = que[front]; // front 인덱스에 있는 요소 제거하고 리턴값지정
		front = (front +1) % que.length; //원형큐 특성 front를 다음칸으로 이동
		if (front == (rear +1) % que.length) { //만약에 front가 리어가 다음칸으로 이동한 것고 같으면
			isEmptyTag = true;					// 원형큐가 비었다 확인
		}
		return remove;
	}
	 void clear() {
		if(isEmpty()) {
			return; // 비었으면 진짜 true 반환
		}		 
		front = rear = 0;   //front와 rear가 0이면 
		isEmptyTag = true;  // boolean 타입의 빈원형큐 isEmptyTag true 반환 
	}

	//--- 큐의 크기를 반환 ---//
		public int getCapacity() {
			return QUEUE_SIZE;
		}

	//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
		public int size() {//front, rear를 사용하여 갯수를 size로 계산
			if(isEmpty()) {
				return 0; //큐가 비었으니 0리턴
			}
			if (front <= rear) { //front보다 rear가 크면
				return rear - front + 1; //요소의 개수를 계산해서 리턴
			}
			return (que.length - front + rear + 1) % que.length; //rear가 front보다 작으면 front부터 끝까지 요소부터 처음부터 rear까지 요소 더해서 전체 크기 계산하고 이건 원형큐 특성계산으로 
		}
		//--- 원형 큐가 비어있는가? --- 수정 필요//
		public boolean isEmpty() {
			return front == rear && isEmptyTag; //front와 리어가 같고, isEmptyTag가  true 면 비었다
		}

	//--- 원형 큐가 가득 찼는가? --- 수정 필요//
		public boolean isFull() {
			return front == rear && !isEmptyTag;//front와 리어가 같고, isEmptyTag가  false 면 비었다
		}

		public void dump() throws EmptyQueueException{
			if (isEmpty())
					throw new EmptyQueueException("dump: queue empty");
			else { //front부터 rear까지 보든 요소 출력
				// front부터 시작      큐가 원형이니까 i가 (rear +1) % que.length까지 반복    i를 원형큐계산법으로 나눈 나머지로 이동
				for (int i = front; i != (rear +1) % que.length; i = (i+1)% que.length ) {
					System.out.print(que[i] + " ");
				}
				System.out.println();
			}
		}
		public Point5 peek() throws EmptyQueueException {
			if (isEmpty())
				throw new EmptyQueueException("peek: queue empty"); // 큐가 비어있음
			return que[front]; //front가 가르키는 위치 리턴
		}
}

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
			p = new Point5(rndx,rndy);
			try {
				oq.push(p);
				System.out.println("push: size() = "+ oq.size());
			} catch(CircularQueue.OverflowQueueException e) {
				System.out.println("queue이 full입니다." + e.getMessage());
				e.printStackTrace();
			}
			break;

		case 2: // 디큐
			try {
				p = oq.pop();
				System.out.println("pop: size() = "+ oq.size());
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
		case 5: //clear
			oq.clear();
			break;
	}
	}
}
}
	