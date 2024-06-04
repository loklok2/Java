package Chap4_스택과큐;
/*
 * 실습 4번 - 정수 배열 원형 큐
 * 교재 148 ~ 157 페이지
 * 교재 소스 코드를 보지 않고 구현 완성 연습 필요 
 */
import java.util.Random;
/*
 * 큐 1번 실습 코드 - 정수들의 큐
 */
import java.util.Scanner;

import Chap4_스택과큐.IntQueue3.EmptyIntQueue3Exception;
import Chap4_스택과큐.IntQueue3.OverflowIntQueue3Exception;

//int형 고정 길이 큐
class IntQueue3 {
    private int[] que;       // 큐용 배열
    private int capacity;    // 큐의 크기
    private int front;       // 맨 처음 요소 커서
    private int rear;        // 맨 끝 요소 커서
    private int num;         // 현재 데이터 개수

    // 내부 클래스: 큐가 비어있을 때 발생하는 예외
    public class EmptyIntQueue3Exception extends RuntimeException {
        public EmptyIntQueue3Exception() {
        }
    }

    // 내부 클래스: 큐가 가득 찼을 때 발생하는 예외
    public class OverflowIntQueue3Exception extends RuntimeException {
        public OverflowIntQueue3Exception() {
        }
    }

    // 생성자
    public IntQueue3(int maxlen) {
        capacity = maxlen;
        que = new int[capacity];
        front = rear = num = 0;
    }

    // 큐에 데이터를 인큐
    public void enque(int x) throws OverflowIntQueue3Exception {
        if (isFull())
            throw new OverflowIntQueue3Exception();  // 큐가 가득 찼을 때 예외 발생
        que[rear++] = x;        // rear가 가리키는 위치에 데이터를 저장하고 rear를 한 칸 증가
        num++;                  // 데이터 증가
        if (rear == capacity)   // rear가 배열의 끝에 도달하면 원형 큐이므로 rear를 0으로 설정
            rear = 0;
    }

    // 큐에서 데이터를 디큐
    public int deque() throws EmptyIntQueue3Exception {
        if (isEmpty())
            throw new EmptyIntQueue3Exception();    // 큐가 비어있을 때 예외 발생
        int x = que[front++];  // front가 가리키는 위치의 데이터를 x에 저장하고 front 증가
        num--;                // 데이터  감소
        if (front == capacity) // front가 배열의 끝에 도달하면 원형 큐 front를 0으로
            front = 0;
        return x;
    }

    // 큐에서 데이터를 피크(프런트 데이터를 들여다봄)
    public int peek() throws EmptyIntQueue3Exception {
        if (isEmpty())
            throw new EmptyIntQueue3Exception();    // 큐가 비어있을 때 예외 발생
        return que[front];  // front가 가리키는 위치의 데이터 리턴
    }

    // 큐를 비움
    public void clear() {
        front = rear = num = 0;
    }

    // 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환
    public int indexOf(int x) {
        for (int i = 0; i < num; i++) {
            int idx = (front + i) % capacity;  // 원형 큐이므로 인덱스를 계산할 때 capacity로 나눈 나머지 사용
            if (que[idx] == x)
                return i;
        }
        return -1;
    }

    // 큐의 크기를 반환
    public int getCapacity() {
        return capacity;
    }

    // 큐에 쌓여 있는 데이터 개수를 반환
    public int size() {
        return num;
    }

    // 큐가 비어있는가?
    public boolean isEmpty() {
        return num <= 0;
    }

    // 큐가 가득 찼는가?
    public boolean isFull() {
        return num >= capacity;
    }

    // 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력
    public void dump() {
        if (isEmpty())
            System.out.println("큐가 비어 있습니다.");
        else {
            for (int i = 0; i < num; i++) {
                System.out.print(que[(front + i) % capacity] + " ");
            }
            System.out.println();
        }
    }
}
public class train_실습4_3_2정수원형큐_배열 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue3 oq = new IntQueue3(10); // 최대 64개를 인큐할 수 있는 큐
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
				} catch(IntQueue3.OverflowIntQueue3Exception e) {
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (IntQueue3.EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (EmptyIntQueue3Exception e) {
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