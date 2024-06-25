package Chap8_List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

class TreeNode5 {
	TreeNode5 LeftChild;
	int data;
	TreeNode5 RightChild;

	public TreeNode5(int x) {
		LeftChild = RightChild = null;
		data = x; //생성자 추가
	}
}

class ObjectStack5{
	//--- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;
		public EmptyGenericStackException() {
			super();
		}
	}

	//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException() {
		}
	}

    private List<TreeNode5> data;  // list를 사용: 배열은 크기를 2배로 늘리는 작업 필요 
	//private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

//--- 생성자(constructor) ---//
	public ObjectStack5(int capacity) {
		top = 0;
		this.capacity = capacity;
		// this.data = new T[capacity]; // 스택 본체용 배열을 생성
		try {
		data = new ArrayList<>(capacity);
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

//--- 스택에 x를 푸시 ---//
	public boolean push(TreeNode5 x) throws OverflowGenericStackException {
		System.out.println("top = " + top +"capacity = " + capacity);
		if (top >= capacity)
			throw new OverflowGenericStackException();
		top++;
		return data.add(x);

	}

//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public TreeNode5 pop() throws EmptyGenericStackException  {
		if (top <= 0)
			throw new EmptyGenericStackException();
		return data.remove(--top);
	}

//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public TreeNode5 peek() throws EmptyGenericStackException  {
		if (top <= 0)
			throw new EmptyGenericStackException();
		return data.get(top - 1);
	}

//--- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(TreeNode5 x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

//--- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("stack이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i)+ " ");
			System.out.println();
		}
	}
}
//정수를 저정하는 이진트리 만들기 실습
class ObjectQueue5 {
    private TreeNode5[] que;//큐는 배열로 구현
	//private List<Integer> que; // 수정본
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private int num; // 현재 데이터 개수

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String msg) {
			super(msg);
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String msg) {
			super(msg);
		}
	}

//--- 생성자(constructor) ---//
public ObjectQueue5(int maxlen) {
   num = front = rear = 0;
   capacity = maxlen;
   try {
	   que = new TreeNode5[maxlen];
   } catch (OutOfMemoryError e) {        // 생성할 수 없음
       capacity = 0;
   }
}

//--- 큐에 데이터를 인큐 ---//
	public int enque(TreeNode5 x) throws OverflowQueueException {
		if (num >= capacity)
			throw new OverflowQueueException("queue full"); // 큐가 가득 찼음
		que[rear++] = x; 
		num++;

		return 1;
	}

//--- 큐에서 데이터를 디큐 ---//
	public TreeNode5 deque() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException("queue empty"); // 큐가 비어있음
		TreeNode5 x = que[front++];
		num--;

		return x;
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public TreeNode5 peek() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException("queue empty"); // 큐가 비어있음
		return que[front];
	}

//--- 큐를 비움 ---//
	public void clear() {
		num = front = rear = 0;
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(TreeNode5 x) {
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % capacity;
			if (que[idx].equals(x)) // 검색 성공
				return idx;
		}
		return -1; // 검색 실패
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return num;
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return num <= 0;
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return num >= capacity;
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if (num <= 0)
			System.out.println("큐가 비어있습니다.");
		else {
			for (int i = 0; i < num; i++)
				System.out.print(que[((i + front) % capacity)] + " ");
			System.out.println();
		}
	}
}
class Tree5 { //
	TreeNode5 root;

	Tree5() {
		root = null;
	}

	TreeNode5 inorderSucc(TreeNode5 p) { //p는 5
		TreeNode5 temp = p.RightChild;    // temp = 4
		if (p.RightChild != null)
			while (temp.LeftChild != null)
				temp = temp.LeftChild;
		System.out.println("inordersucc:: temp.data = "+temp.data);
		return temp;
	}

	boolean isLeafNode(TreeNode5 p) {
		if (p.LeftChild == null && p.RightChild == null)
			return true;
		else
			return false;
	}

	void inorder() { //inorder traversal  //driver function
		inorder(root); // -> overloading된거, 자기 자신을 부른게 아님!!!!즉, 얘가 workhorse
	}

	void preorder() {
		preorder(root);  // AB+
	}

	void postorder() {		
		postorder(root);	//+AB
	}

	void inorder(TreeNode5 pNode) {  //workhorse함수  //위에 inorder에서 오버로딩된 메서드 얘는 리커시브(재귀)다 //수정,구현 불필요
		if (pNode != null) {								//다만, 스택을 사용해서 넌리커시브로 바꿔주는 코드가 필요 아래의 NonrecInorder
			inorder(pNode.LeftChild); // L:LeftChild
			System.out.print(" " + pNode.data); //V:visit node
			inorder(pNode.RightChild);// R:RightChild
		}
	}

	void preorder(TreeNode5 pNode) {
		if (pNode != null) {
			System.out.print(pNode.data + " ");
			preorder(pNode.LeftChild);
			preorder(pNode.RightChild);
		}
	}

	void postorder(TreeNode5 pNode) {
		if (pNode != null) {
			postorder(pNode.LeftChild);
			postorder(pNode.RightChild);
			System.out.print(pNode.data + " ");
		}
	}

	void NonrecInorder()//void Tree5::inorder(TreeNode5 *pNode)와 비교
	//stack을 사용한 inorder 출력
	{
		ObjectStack5 s = new ObjectStack5(20); //스택사용
		TreeNode5 pNode = root;
		while (true) {
			while (pNode != null) { // inorder(pNode.LeftChild) 처리 스택을 이용해서 처리하고 왼쪽을 이용해서 타고 내려간다
				s.push(pNode);
				pNode = pNode.LeftChild;
			}
			if (!s.isEmpty()) {
				try {
					pNode = s.pop(); //넣고
				} catch (Chap9_Tree.ObjectStack5.EmptyGenericStackException e) {
					e.printStackTrace();
				}
				System.out.println(" " + pNode.data); //출력하고
				pNode = pNode.RightChild; //inorder(pNode.RightChild) 처리 //ex)꼬리부분제거
			}
			else break;  
		}
	}
	void levelOrder() //level 별로 출력한다. level이 증가하면 다음줄에 출력한다 
	//난이도: 최상급 구현  //화면에 출력할때 레벨별로 출력하고 칸을 띄워서 구분하여 트리 모양 처럼 출력하는거 
	{
		//	Queue<TreeNode*> q;
		//	TreeNode* CurrentNode = root;
		//	while(currentNode) {
		//		cout<<currentNode->data<<endl;
		//		if(currntNode->leftchild)
		//			q.add(currnetNode->leftchild);
		//		if(currntNode->rightchild)
		//			q.add(currnetNode->rightchild);
		//		currentNode = *q.delete(currentNode);
		//	}
		//	
		ObjectQueue5 q = new ObjectQueue5(20);
		Queue<Integer> que = new LinkedList<>();
		int oldLevel = 0,  newLevel=0;
		que.add(oldLevel+1);
		TreeNode5 pNode = root;
		newLevel = que.remove();
		while (pNode != null) {
			if (oldLevel != newLevel) {
				System.out.println(); 
				oldLevel = newLevel;
			}

			System.out.print(pNode.data + " "); 

			
	}
	

	boolean insert(int x) {// binary search tree를 만드는 입력 : left subtree < 노드 x < right subtree
		//inorder traversal시에 정렬된 결과가 나와야 한다
		TreeNode5 newNode = new TreeNode5(x);
		TreeNode5 p = root;
		TreeNode5 q = null;
		boolean tag = true;
		//p가 널이면 새로운 노드로 추가하는 로직 링크드리스트랑 비슷하나, 다르다 
		if(p==null) {
			root = newNode;
			return tag;
		}
		while(p!=null) {
			q = p;
			if(x<p.data) { //입력값에 따라 가는걸로 하는게 교수님은 좋다고 함;
				tag = true;
				p = p.LeftChild;
			}else {
				tag = false;
				p = p.RightChild;
			}
		}
		if(tag) {
			q.LeftChild = newNode; //여기까지만하면 문제가 있다. x가 작으면 왼쪽으로가서 하는데, 예를 들어서 x가 5라고 가정하게 되면 오른쪽으로 가야함 			
		}else {
			q.RightChild = newNode;
		}
		
		return true;
	}

	boolean delete(int num) {//binary search tree에서 임의 값을 갖는 노드를 찾아 삭제한다.
		//삭제 대상이 leaf node인 경우, non-leaf node로 구분하여 구현한다 
		//leaf 노드는 ㅣ,r 이 둘다 null이면 leaf node
		TreeNode5 p = root, q = null, parent = null;
		int branchMode = 0; // 1은 left, 2는 right
		if (root == null) {
			return false;
		}
		//1. 자식노드가 없는 경우
		//2. 자식노드가 하나인 경우
		//3. 자식노드가 둘인 경우
		while ( p != null) { 
	        if (p.data == num) {
	            q = p;
	            break;
	        }
	        parent = p;
	        if (num < p.data) {
	            p = p.LeftChild;
	            branchMode = 1; // 왼쪽 자식 노드
	        } else {
	            p = p.RightChild;
	            branchMode = 2; // 오른쪽 자식 노드
	        }
	    }
		if (q == null) { 
			return false;			
		}
		if (q.LeftChild == null) { 
			if(q.RightChild == null) {
				if(q == root) {
					root = null;
				}else {
					if (branchMode == 1) { //왼쪽 자식노드이면
		                parent.LeftChild = null;  //왼쪽 자식을 null로 설정
		            } else { //오른쪽 자식노드이면
		                parent.RightChild = null;  //오른쪽 자식을 null로 설정
		            }	
				}
			}
		} else {
			
		}
		
		return true;
	}



	boolean search(int num) {//num 값을 binary search tree에서 검색
		TreeNode5 p = root;
		while (p != null) {
	        if (p.data == num) {
	            return true; // 값 발견
	        } else if (num < p.data) {
	            p = p.LeftChild; // 왼쪽 서브트리로 이동
	        } else {
	            p = p.RightChild; // 오른쪽 서브트리로 이동
	        }
	    }
	    return false; // 값 발견하지 못함

	}
}

public class 실습9_1_Test_정수이진트리{
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬출력"), 
		LevelorderPrint("레벨별출력"), StackInorderPrint("스택정렬출력"), 
		PreorderPrint("prefix출력"), PostorderPrint("postfix출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner stdIn = new Scanner(System.in);
		//  참조변수 	생성자
		Tree5 t = new Tree5(); 
		Menu menu; // 메뉴
		int count = 20;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
			case Add: // 
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					input[ix] = rand.nextInt(50);
				}
				for (int n: input)
					System.out.print(n + " ");
				for (int i = 0; i < count; i++) {
					if (!t.insert(input[i]))
						System.out.println("Insert Duplicated data");
				}
				break;

			case Delete: //임의 정수 삭제
				System.out.println("삭제할 데이터:: ");
				num = stdIn.nextInt();
				if (t.delete(num))
					System.out.println("삭제 데이터 = " + num + " 성공");
				else
					System.out.println("삭제 실패");
				;
				break;

			case Search: // 노드 검색
				System.out.println("검색할 데이터:: ");

				num = stdIn.nextInt();
				result = t.search(num);
				if (result)
					System.out.println(" 데이터 = " + num + "존재합니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				System.out.println();
				//t.NonrecInorder();
				break;
			case LevelorderPrint: // 
				t.levelOrder();
				System.out.println();
				//t.NonrecInorder();
				break;
			case StackInorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.NonrecInorder();
				break;
			case PreorderPrint://prefix 출력
				t.preorder();
				System.out.println();
				break;
			case PostorderPrint://postfix로 출력
				t.postorder();
				System.out.println();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
