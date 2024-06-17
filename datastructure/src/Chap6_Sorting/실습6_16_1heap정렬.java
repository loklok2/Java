package Chap6_Sorting;

import java.util.Random;
import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);
	public int DeleteMax();   //인서트 딜리트사용
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	private int n; // MaxHeap의 현재 입력된 element 개수
	private int MaxSize; // Maximum allowable size of MaxHeap
	
	public Heap(int sz) {
		this.MaxSize = sz;
		this.heap = new int[MaxSize + 1];
		this.n = 0;

	}
	
	public int getSize() {
		return n;
	}

	public void display() {//heap 배열을 출력한다. 배열 인덱스와 heap[]의 값을 출력한다.
		for(int i = 0; i <= n; i ++) {
			System.out.print(heap[i] + " ");
		}
		System.out.println();
	
	}
	@Override
	public void Insert(int x) {//max heap이 되도록 insert한다. 삽입후 complete binary tree가 유지되어야 한다.
		int i; //새로 삽입할 값을 힙에 맞게 위치를 찾아가는데 사용하는 변수
        if (n == MaxSize) { // 힙이 가득찼으면
            HeapFull(); 
            return;
        }
        n++;// 힙의 크기 증가 
        for (i = n; i > 1 && x > heap[i / 2]; i /= 2) { // i = n 부터 시작해서, 부모노드의 새로운 x값과 비교
            heap[i] = heap[i / 2]; // = i의 부모노드 i가 1부터 시작해서 heap[(i-1) /2]이 부모노드 성립X
        }
        heap[i] = x;
    }
	@Override
	public int DeleteMax() {//heap에서 가장 큰 값을 삭제하여 리턴한다. 
		if (n == 0) {
	        HeapEmpty(); // 힙이 비었다면
	        return 0; // 힙이 비었을 때 반환할 값
	    }
	    
	    int max = heap[1]; // 삭제할 루트의 노드 최대값
	    int l = heap[n]; // 마지막 노드값
	    n--; // 힙 크기 감소

	    // 힙을 재구성하여 최대 힙 성질 유지
	    int i = 1;
	    int j = 2;
	    while (j <= n) {
	        if (j < n && heap[j] < heap[j + 1]) {
	            j++;
	        }
	        if (l >= heap[j]) {
	            break;
	        }
	        heap[i] = heap[j];
	        i = j;
	        j *= 2;
	    }
	    heap[i] = l; // 마지막 노드를 적절한 위치에 배치
	    
	    return max; // 최대값 반환
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}
public class 실습6_16_1heap정렬 {
	 static void showData(int[] d) {
		 for(int i = 0; i <d.length; i++) {
			 System.out.println(d[i] + " ");
		 }
		 System.out.println();
	 }
	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);
	    final int count = 10;//난수 생성 갯수
	    int[] x = new int[count+1];//x[0]은 사용하지 않으므로 11개 정수 배열을 생성한다 
	    int []sorted = new int[count];//heap을 사용하여 deleted 정수를 배열 sorted[]에 보관후 출력한다

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1://난수를 생성하여 배열 x에 넣는다 > heap에 insert한다.
				int newData = rnd.nextInt(100);
				heap.Insert(newData);
//			    showData(x);
				heap.display();
				break;
			case 2:	//heap 트리구조를 배열 인덱스를 사용하여 출력한다.
				heap.display();
				break;
			case 3://heap에서 delete를 사용하여 삭제된 값을 배열 sorted에 넣는다 > 배열 sorted[]를 출력하면 정렬 결과를 얻는다 
				for (int i = 0; i < count; i++) {
                    if (heap.getSize() > 0) { // 힙이 비어 있지 않은지 확인
                        sorted[i] = heap.DeleteMax();
                    } else {
                        System.out.println("Heap is already empty.");
                        break;
                    }
                }
                System.out.println("sorted: ");
                showData(sorted);
                break;
			case 4:
				return;
			}
		} while (select < 5);

		return;
	}
}

