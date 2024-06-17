package Chap6_Sorting;

class Term implements Comparable<Term>{
    double coef;           // 계수
    int    exp;            // 지수
    
	public Term(double coef, int exp) { //생성자
		this.coef = coef;
		this.exp = exp;
	}
	@Override
	public int compareTo(Term o) {
		return Integer.compare(this.exp, o.exp);
	}
	
	@Override
    public String toString() {
        return coef + "x^" + exp;  // ^= 지수표현
    }

}
public class Test_다항식merge연산 {

	static void merge(Term[] a, int lefta, int righta, int leftb, int rightb ) {
		Term[] temp = new Term[a.length]; //병합결과 임시로 저장할 배열
		int p = lefta;	//첫번째 배열 인덱스 
		int q = leftb;	//두번째 배열 인덱스
		int ix = lefta;	//임시배열 인덱스
		
		while (p <= righta && q <= rightb) {  // 두 부분의 배열의 모든 요소가 확인할때까지 반복
	        if (a[p].compareTo(a[q]) <= 0) { // 첫 배열의 현재 요소가 두번째 부분의 배열의 현재요소보다 작거나 같으면
	            temp[ix++] = a[p++];		// 첫번째 부분의 배열의 현재 요소를 ix에 추가하고 두 포인터 증가
	        } else {						// 첫번째 부분의 배열의 현재 요소가 두번쨰 부분의 배열의 요소보다 크면
	            temp[ix++] = a[q++];		// 두번쨰 부분의 배열의 요소를 ix에 추가하고 두 포인터 증가
	        }
	    }
		while (p <= righta) {  // 첫 번째 부분 배열에 남아 있는 요소가 있으면
	        temp[ix++] = a[p++];  // 남아 있는 요소를 모두 임시 배열에 추가
	    }	
	    while (q <= rightb) {  // 두 번째 부분 배열에 남아 있는 요소가 있으면
	        temp[ix++] = a[q++];  // 남아 있는 요소를 모두 임시 배열에 추가
	    }
	    // 임시 배열을 원래 배열에 복사
	    for (int i = lefta; i <= rightb; i++) {  // 임시 배열의 모든 요소를 원래 배열에 복사
	        a[i] = temp[i];  // 임시 배열의 각 요소를 원래 배열의 해당 위치에 복사
	    }
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Term[] a, int left, int right) {  // 병합 정렬을 수행하는 메소드
		int mid = (left+right)/2;  // 배열의 중간 인덱스를 계산
		if (left == right) return;  // 부분 배열의 크기가 1이면 리턴
		MergeSort(a, left, mid);  // 왼쪽 부분 배열에 대해 재귀적으로 병합 정렬
		MergeSort(a, mid+1, right);  // 오른쪽 부분 배열에 대해 재귀적으로 병합 정렬
		merge(a, left, mid, mid+1, right);  // 두 개의 정렬된 부분 배열을 병합
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
		
		
		int nx = PolynomialX.length;  // PolynomialX의 길이를 nx에 저장
		ShowPolynomial(PolynomialX);  // PolynomialX를 출력하는 메소드를 호출
		ShowPolynomial(PolynomialY);  // PolynomialY를 출력하는 메소드를 호출
		MergeSort(PolynomialX, 0, PolynomialX.length - 1);  // PolynomialX 배열을 병합 정렬하는 메소드를 호출
		MergeSort(PolynomialY, 0, PolynomialY.length - 1);  // PolynomialY 배열을 병합 정렬하는 메소드를 호출
		ShowPolynomial(PolynomialX);  // 정렬된 PolynomialX를 출력하는 메소드를 호출
		ShowPolynomial(PolynomialY);  // 정렬된 PolynomialY를 출력하는 메소드를 호출
		Term[] z = new Term[20];  // 다항식의 덧셈과 곱셈 결과를 저장할 Term 배열 z를 선언하고 초기화
		AddPolynomial(PolynomialX,PolynomialY,z);  // PolynomialX와 PolynomialY를 더하는 메소드를 호출하고, 그 결과를 z에 저장
		ShowPolynomial(z);  // 다항식의 덧셈 결과를 출력하는 메소드를 호출
		ShowPolynomial(PolynomialY);  // PolynomialY를 출력하는 메소드를 호출
		MultiplyPolynomial(PolynomialX,PolynomialY,z);  // PolynomialX와 PolynomialY를 곱하는 메소드를 호출하고, 그 결과를 z에 저장
		ShowPolynomial(PolynomialY);  // PolynomialY를 출력하는 메소드를 호출
		int result = EvaluatePolynomial(z, 10);  // 다항식 z를 계산하는 메소드를 호출하고, x=10일 때의 값을 result에 저장
		System.out.println(" result = " + result );  
	}

	static void MultiplyPolynomial(Term[] polynomialX, Term[] polynomialY, Term[] z) {
	    // 이 메소드는 두 다항식을 곱하는 연산을 수행
	    for (int i = 0; i < z.length; i++) {
	        z[i] = null;  // 결과를 저장할 배열 z를 초기화
	    }

	    for (Term termX : polynomialX) {  // 첫 번째 다항식의 모든 항에 대해 반복
	        if (termX == null) continue;  // 현재 항이 null이면 다음 항으로
	        for (Term termY : polynomialY) {  // 두 번째 다항식의 모든 항에 대해 반복
	            if (termY == null) continue;  // 현재 항이 null이면 다음 항으로
	            int newExp = termX.exp + termY.exp;  // 새 항의 지수는 두 항의 지수의 합
	            double newCoef = termX.coef * termY.coef;  // 새 항의 계수는 두 항의 계수의 곱
	            boolean found = false;
	            for (int k = 0; k < z.length; k++) {  // 결과 배열 z의 모든 항에 대해 반복
	                if (z[k] != null && z[k].exp == newExp) {  // 같은 지수를 가진 항이 이미 존재하면
	                    z[k].coef += newCoef;  // 그 항의 계수에 새 계수를 더함
	                    found = true;
	                    break;
	                }
	            }
	            if (!found) {  // 같은 지수를 가진 항이 존재하지 않으면
	                for (int k = 0; k < z.length; k++) {
	                    if (z[k] == null) {  // 빈 위치를 찾아
	                        z[k] = new Term(newCoef, newExp);  // 새 항을 추가
	                        break;
	                    }
	                }
	            }
	        }
	    }
	}

	static void AddPolynomial(Term[] polynomialX, Term[] polynomialY, Term[] z) {
	    // 이 메소드는 두 다항식을 더하는 연산을 수행
	    int i = 0, j = 0, k = 0;
	    while (i < polynomialX.length && j < polynomialY.length) {  // 두 다항식의 모든 항을 확인할 때까지 반복
	        if (polynomialX[i].exp == polynomialY[j].exp) {  // 두 항의 지수가 같으면
	            z[k++] = new Term(polynomialX[i].coef + polynomialY[j].coef, polynomialX[i].exp);  // 두 항의 계수를 더하고, 그 결과를 새 항으로 만들어 결과 다항식에 추가
	            i++;  // 첫 번째 다항식의 다음 항으로 이동
	            j++;  // 두 번째 다항식의 다음 항으로 이동
	        } else if (polynomialX[i].exp < polynomialY[j].exp) {  // 첫 번째 다항식의 현재 항의 지수가 두 번째 다항식의 현재 항의 지수보다 작으면
	            z[k++] = polynomialX[i++];  // 첫 번째 다항식의 현재 항을 결과 다항식에 추가하고, 첫 번째 다항식의 다음 항으로 이동
	        } else {  // 두 번째 다항식의 현재 항의 지수가 첫 번째 다항식의 현재 항의 지수보다 작으면
	            z[k++] = polynomialY[j++];  // 두 번째 다항식의 현재 항을 결과 다항식에 추가하고, 두 번째 다항식의 다음 항으로 이동
	        }
	    }
	    while (i < polynomialX.length) {  // 첫 번째 다항식에 남아 있는 항이 있으면
	        z[k++] = polynomialX[i++];  // 남아 있는 항을 모두 결과 다항식에 추가
	    }
	    while (j < polynomialY.length) {  // 두 번째 다항식에 남아 있는 항이 있으면
	        z[k++] = polynomialY[j++];  // 남아 있는 항을 모두 결과 다항식에 추가
	    }
	}

	static int EvaluatePolynomial(Term[] z, int x) {
	    // 이 메소드는 다항식 z를 계산하는 연산을 수행
	    double result = 0;
	    for (Term term : z) {  // 다항식 z의 모든 항에 대해 반복
	        if (term != null) {  // 현재 항이 null이 아니면
	            result += term.coef * Math.pow(x, term.exp);  // 현재 항의 계수와 x의 지수승을 곱한 값을 결과에 더함
	        }
	    }
	    return (int) result;  // 결과를 정수로 변환하여 리턴
	}

	static void ShowPolynomial(Term[] polynomial) {
	    // 이 메소드는 다항식을 출력하는 연산을 수행
	    boolean first = true;
	    for (Term term : polynomial) {  // 다항식의 모든 항에 대해 반복
	        if (term != null) {  // 현재 항이 null이 아니면
	            if (!first) {  // 첫 항이 아니면
	                System.out.print(" + ");  // 덧셈 기호를 출력
	            }
	            System.out.print(term);  // 현재 항을 출력
	            first = false;  // 첫 항이 아님을 표시
	        }
	    }
	    System.out.println(); 
	}    
}