package Chap11_그래프;



//소스코드 6.1: Graph Representation
//6.1 Adjacency Lists + BFS + DFS


import java.util.Scanner;

class ListNode {
	int data;
	int weight;
	ListNode link;

	public ListNode(int data, int weight) {
		this.data = data; this.weight = weight;
		link = null;
	}
}

class List {
	public List() {
		first = null;
	}
	ListNode first;
	void Insert(int k, int w) {//같은 값을 체크하지 않아 중복 입력됨
		ListNode newnode = new ListNode(k, w);
		newnode.link = first;
		first = newnode;

	}
	void Delete(int k) {
		ListNode previous = null;
		ListNode current = first;
		while(current != null) {
			previous = current;
			current = current.link;
		}
		if(current = null) {
			if()
		}
	}
}

class ListIterator {

	private List list;
	private ListNode current;

	public ListIterator(List l) {
		list = l;
		current = list.first;
	}

	int First() {
		if (current != null)
			return current.data;
		else
			return 0;
	}

	int Next() {
		int data = current.data;
		current = current.link;
		return data;
	}

	boolean NotNull() {
		if (current != null)
			return true;
		else
			return false;
	}

	boolean NextNotNull() {
		if (current.link != null)
			return true;
		else
			return false;
	}


}

class QueueNode {
	int data;
	QueueNode link;

	QueueNode(int data, QueueNode link) {
		this.data = data;
		this.link = link;
	}
}

class Queue {
	private QueueNode front, rear;

	void QueueEmpty() {
		front = rear = null;
	}

	public Queue() {
		QueueEmpty();
	}

	boolean IsEmpty() {
		if (front == null)
			return true;
		else
			return false;
	}

	void Insert(int y) {
		QueueNode temp = new QueueNode(y, null);

	}

	int Delete()
	// delete the first node in queue and return its data
	{
	

	}
}
class StackNode {
	ListNode data;
	StackNode link;

	StackNode(ListNode data, StackNode link) {
		this.data = data;
		this.link = link;
	}
}
class Stack {
	private StackNode top;

	void StackEmpty() {
		top = null;
	}

	public Stack() {
		StackEmpty();
	}

	boolean IsEmpty() {
		if (top == null)
			return true;
		else
			return false;
	}

	void Insert(ListNode y) {
		StackNode temp = new StackNode(y, null);

	}

	ListNode Delete()
	// delete the top node in stack and return its data
	{

	}
}


class Graph {
	List[] HeadNodes; // 각 노드에 대한 adjacency list
	int n;	//노드 갯수
	boolean[] visited;  //노드를 방문했는지 체크

	public Graph(int vertices) {
		/*
		int[][]a = new int[3][];
        for (int i = 0; i < 3; i++) {
            a[i] = new int[5]; 
        }	
		*/
		//생성자//
		n = vertices;
		HeadNodes = new List[n]; // 배열을 만들고 
		visited = new boolean[n];
		for (int i = 0; i < n; i++) { //이 for루프를 왜 하는지 이해하자, 상위 주석 for루프 참조
			HeadNodes[i] = new List(); //빈 리스트를 만들고
			visited[i] = false;
		}
		
	}
	int FindMaxVertex() {
		return n;
	}
	void displayAdjacencyLists() {
		// 정수체인해시처럼 출력
		// 0 -> 1 형태
		//테이블 인덱스 출력하고 하는거
	}

	void InsertVertex(int start, int end, int weight) {
		if (start < 0 || start >= n || end < 0 || end >= n) { //여기서 n은 8
			System.out.println("the start node number is out of bound.");
			return;
		}
		//	0->1 표현하는 리스트 노드를 만드는 코드
		// 	1->0
		// 10장의 정수체인해시 코드와 유사
	}

	void BFS(int v) { //큐를 사용해서 
		boolean[] visited = new boolean[n]; // visited is declared as a Boolean 
		for (int i = 0; i < n; i++)
			visited[i] = false; // initially, no vertices have been visited

	
	}
	void ShowList(List l) {
		ListIterator li = new ListIterator(l);

	}

	// Driver - main()에서 호출되는 함수를 driver라고 한다
	void DFS(int v) {
		for (int i = 0; i < n; i++)
			visited[i] = false; // initially, no vertices have been visited //

		_DFS(v); // start search at vertex 0
		//_NonRecursiveDFS(v);

	}

	// Workhorse
	void _DFS(int v) // 참고자료 10p
	// visit all previously unvisited vertices that are reachable from vertex v //v가 출발노드
	{
		visited[v] = true;
		//구현
		//c++로 작성된 알고리즘을 사용하여 자바버전으로 구현

	}
	// Workhorse 2
		void _NonRecursiveDFS(int v)
		// visit all previously unvisited vertices that are reachable from vertex v //스택을 이용해서 만들어라
		{
			visited[v] = true;
			int w = -1;
		
		}
}
//void _NonRecursiveDFS(int v) 구현을 listiterator를 사용한 버젼 만들기
class InputGraph {
	int start;			//방향성이 아니라 무방향성 ex) (0,1), (0,2)
	int end;
	public InputGraph(int start, int end) {
		this.start = start;
		this.end = end;
	}
}
public class Chap11_test_그래프DFS_BFS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int select = 10, n, startEdge = -1, endEdge = -1;
		int startBFSNode = 0;// the start node to BFS //시작노드

		n = 8; //내가 만들려는 그래프의 노드 갯수
		Graph g = new Graph(n);

		while (select != '0') {
			System.out.println("\n명령 선택 1: edge 추가, 2: Adjacency Lists 출력, 3: BFS, 4: DFS, 5: 종료 => ");
			select = sc.nextInt();
			switch (select) {
			case 1:
				InputGraph []inputData = 	{new InputGraph(0,1), new InputGraph(0,2),new InputGraph(1,3),
						new InputGraph(1,4),new InputGraph(2,5),new InputGraph(2,6),
						new InputGraph(3,7),new InputGraph(4,7),new InputGraph(5,7),new InputGraph(6,7)};  //값을 입력하므로, 그래프의 edge를 표현함
				for (int i = 0; i < inputData.length;i++) {
					startEdge = inputData[i].start;
					endEdge = inputData[i].end;
					g.InsertVertex(startEdge, endEdge, 10); // 10은 가중치, 임의의 값을 넣어도 된다 
				}
				
				break;
			case 2:
				// display
				g.displayAdjacencyLists();
				break;

			case 3:
				System.out.println("Start BFS from node: " + startBFSNode);
				g.BFS(startBFSNode);
				break;
			case 4:
				System.out.println("Start DFS from node: " + startBFSNode);
				g.DFS(startBFSNode);
				//g.DFS(0); //시작 노드를 0으로
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("WRONG INPUT  " + "\n" + "Re-Enter");
				break;
			}
		}

		return;
	}
}
