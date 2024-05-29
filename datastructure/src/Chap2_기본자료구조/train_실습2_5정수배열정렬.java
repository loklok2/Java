package Chap2_기본자료구조;

import java.util.Arrays;
//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;
public class train_실습2_5정수배열정렬 {
	static int top = 0;
	static final int MAX_LENGTH = 20;
	public static void main(String[] args) {
		float []data = new float[MAX_LENGTH];// 0.0 ~ 1.0 사이의 실수를 저장
		inputData(data, 10);//10개의 난수를 입력
		showData(data);//top 개수 만큼 출력
		reverse(data);//역순으로 재배치 - 교재 67페이지 참조
		System.out.println(Arrays.toString(data));// 교재 84페이지 코드 참조
		sortData(data);//교재 205 bubbleSort() 함수 코드를 사용 - 올림차순으로 정렬
		showData(data);
		float realData = new Random().nextFloat();
		insertData(data, realData);//정렬된 것에 데이터 삽입
		showData(data);
	}
	

	static void showData(float[] data) {//실수 배열을 top 갯수만 출력
		for(int i = 0; i<top; i++) {
			System.out.println("top의 갯수까지만 출력:"+ data[i]);
		}
	}
	static void inputData(float[] data, int count) {
		Random rand = new Random();
		for (int i = 0; i < count; i++) {
			data[i] = rand.nextFloat();			
		}
		top += count;
	}
	static void reverse(float[] data) {//역순으로 top의 길이만큼 재배치
		for (int i = 0; i<top / 2; i++) {
			swap(data, i , top - i -1);
		}
	}
	static void swap(float[] data, float idx1, float idx2) {//교재 67페이지 - 맞교환
		float t = data[(int) idx1];
		data[(int)idx1] = data[(int)idx2];
		data[(int)idx2] = t;
	}
	static void sortData(float[]data) {//올림차순으로 정렬 bubble정렬205p
		int n = top;
		for (int i = 0; i < n -1; i++) {
			for(int j = 0; j < n -1; j++) {
				if(data[j]> data[j+1]) {
					swap(data, j, j+1);
				}
			}
		}
	}
	static void insertData(float[]data, float realData) {//insert되는 실수 값이 insert될 위치를 찾아 보다 큰 값은 우측으로 이동
		int i = 0;
		while (i < top && data[i] < realData) {
			i++;
		}
		for(int j = top; j > i; j--) {
			data[j] = data[j-1];
		}
		data[i] = realData;
		top++;
	}


}
