package test;

class Term implements Comparable<Term>{
    double coef;           // 계수
    int    exp;            // 지수
    
	public Term(double coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}
	@Override
	public int compareTo(Term o) {
		return Integer.compare(this.exp, o.exp);
	}

}
public class Test_다항식merge연산 {

	static void merge(Term[] a, int lefta, int righta, int leftb, int rightb ) {
		Term[] temp = new Term[a.length];
		int p = lefta;
		int q = leftb;
		int ix = lefta;
		
		while (p <= righta && q <= rightb) {
	        if (a[p].compareTo(a[q]) <= 0) {
	            temp[ix++] = a[p++];
	        } else {
	            temp[ix++] = a[q++];
	        }
	    }
	    while (p <= righta) {
	        temp[ix++] = a[p++];
	    }	
	    while (q <= rightb) {
	        temp[ix++] = a[q++];
	    }
	    // 임시 배열을 원래 배열에 복사
	    for (int i = lefta; i <= rightb; i++) {
	        a[i] = temp[i];
	    }
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Term[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}

	public static void main(String[] args) {   //지수부터 먼저 정렬해
		Term[] PolynomialX = {
		         new Term(1.5, 3),
		         new Term(2.5, 7),
		         new Term(3.3, 2),
		         new Term(4.0, 1),
		         new Term(2.2, 0),
		         new Term(3.1, 4),
		         new Term(3.8, 5),
		     };
		Term[] PolynomialY = {
		         new Term(1.5, 1),
		         new Term(2.5, 2),
		         new Term(3.3, 3),
		         new Term(4.0, 0),
		         new Term(2.2, 4),
		         new Term(3.1, 5),
		         new Term(3.8, 6),
		     };
		int nx = x.length;


		ShowPolynomial(x);  //f(x) = 5x**3+4x**2
		ShowPolynomial(y);
		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		ShowPolynomial(x);
		ShowPolynomial(y);
		Polynomial[] z = new Polynomial[20];
		AddPolynomial(x,y,z);//다항식 덧셈 z = x + y
		ShowPolynomial(z);
		ShowPolynomial(y);
		MultiplyPolynomial(x,y,z);//다항식 곱셈 z = x * y
		ShowPolynomial(y);
		int result = EvaluatePolynomial(z, 10);//다항식 값 계산 함수 z(10) 값 계산한다 
		System.out.println(" result = " + result );
	}
}
