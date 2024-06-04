package Chap4_스택과큐;
/*
 * 실습3번 - 정수 선형 큐를 리스트로 구현
 * 교재 148 페이지는 원형큐를 배열로 구현한 코드 실습 4번에 활용
 */

import java.util.Scanner;

/*
* Queue of ArrayList
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Queue4 {
    private List<Integer> que; // 리스트로 큐 구현
    private int capacity; // 큐의 최대 크기
    private int front; // 큐의 맨 앞 요소의 위치
    private int rear; // 큐의 맨 끝 요소의 위치

    // 실행시 예외: 큐가 비어있음
    public class EmptyQueueException extends RuntimeException {
        public EmptyQueueException(String message) {
            super(message);
        }
    }

    // 실행시 예외: 큐가 가득 찼음
    public class OverflowQueueException extends RuntimeException {
        public OverflowQueueException(String message) {
            super(message);
        }
    }

    public Queue4(int maxlen) {
        que = new ArrayList<>(maxlen);
        capacity = maxlen;
        front = rear = 0; // 큐가 비어있는 상태로 초기화
    }

    // 인큐: 큐에 데이터를 추가
    public void enque(int x) throws OverflowQueueException {
        if (isFull()) { // 큐가 가득 찼을 때
            throw new OverflowQueueException("큐가 가득 찼습니다.");
        }
        que.add(x); // 큐의 맨 끝에 데이터 추가
        rear++; // rear 위치 이동
        System.out.println("인큐한 데이터는 " + x + "입니다."); // 메시지 출력
    }

    // 디큐: 큐에서 데이터를 제거하고 반환
    public int deque() throws EmptyQueueException {
        if (isEmpty()) { // 큐가 비어있을 때
            throw new EmptyQueueException("큐가 비어 있습니다.");
        }
        int data = que.get(front); // front 위치의 데이터를 가져옴
        que.remove(front);
        rear--;
        return data; // 디큐한 데이터 반환
    }

    // 피크: 큐의 맨 앞 데이터를 반환 (제거하지 않음)
    public int peek() throws EmptyQueueException {
        if (isEmpty()) { // 큐가 비어있을 때
            throw new EmptyQueueException("큐가 비어 있습니다.");
        }
        return que.get(front); // front 위치의 데이터 반환
    }

    // 큐를 비움
    public void clear() {
        que.clear(); // 리스트 초기화
        front = rear = 0; // 큐의 상태 초기화
    }

    // 큐의 크기 반환
    public int getCapacity() {
        return capacity;
    }

    // 현재 큐에 들어있는 데이터 개수 반환
    public int size() {
        return que.size();
    }

    // 큐가 비어있는지 여부 반환
    public boolean isEmpty() {
        return que.isEmpty();
    }

    // 큐가 가득 찼는지 여부 반환
    public boolean isFull() {
        return que.size() >= capacity;
    }

    // 큐의 모든 데이터 출력
    public void dump() {
        if (isEmpty()) { // 큐가 비어있을 때
            System.out.println("큐가 비어 있습니다.");
            return;
        }
        for (int i = 0; i < que.size(); i++) { // 큐 전체를 탐색
            System.out.print(que.get(i) + " "); // 데이터 출력
        }
        System.out.println(); // 줄 바꿈
    }
}

public class train_실습4_3_1정수선형큐_리스트 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Queue4 oq = new Queue4(10); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");
			int menu = stdIn.nextInt();
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx +")");
				try {
					oq.enque(rndx);
				} catch(Chap4_스택과큐.Queue4.OverflowQueueException e) {
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (Chap4_스택과큐.Queue4.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (Chap4_스택과큐.Queue4.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 4: // 덤프
				oq.dump();
				break;
			default:
				break;
			}
		}
	}
}