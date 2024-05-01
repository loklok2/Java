package com.ruby.java.ch10.collection;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Test10_hashset {
public static void main(String[] args) {
	/*
	* 로또는 1부터 45까지의 숫자 중 6개의 번호를 선택합니다.
	* 그 후에는 보너스 번호가 무작위로 선택되는데,
	* 이 보너스 번호는 이전에 선택된 6개의 번호와 중복될 수 있습니다.
	* 일반 번호 6개와 보너스 번호 1개는 각각 다른 범주에서 추출됩니다.
	* 보너스 번호는 그 중에서 추출되지 않은 숫자 중에서 하나가 선택됩니다. 즉, 보너스 번호는 일반 번호와 중복됩니다.
	* 출력: lotto = [34, 38, 22, 23, 39, 27]보너스번호 [2]
	* 다음 프로그램을 수정한다 
	*/
	lotto_generator(5);
}
public static void lotto_generator(int n) {
	Random number = new Random();
	HashSet<Integer> lotto = null;					//로또 1장
	for (int i = 0; i < n; i++) {
		lotto = new HashSet<Integer>();				//hashset 순서가 없는거 만들기
		for (int j = 0; lotto.size() < 6; j++) {	//6개 이하 난수 생성
			lotto.add(number.nextInt(46));			
		}
		System.out.print("\nlotto = " + lotto.toString());
		List<Integer> L = new ArrayList<Integer>(lotto);	//순서를 정해서 출력
		int bonus = number.nextInt(46);						//난수생성
		System.out.print("보너스번호 ["+bonus+"]\n");
		Collections.sort(L);;								//6개 숫자 정렬
		L.add(bonus);										//보너스번호 추가

		System.out.println("List = " + L);
	}
}
}
