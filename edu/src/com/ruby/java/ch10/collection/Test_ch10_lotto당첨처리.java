package com.ruby.java.ch10.collection;
//구현과제로 실습
//hashset을 arraylist로 변환하여 정렬하기 구현

/*
 * 로또 당첨 규칙:
 * 꽝: 번호 2개, 1개
 * 5등: 번호 3개 - 5000원
 * 4등: 번호 4개 - 5만원
 * 3등: 번호 5개 - 150만원 - 판매금액에 변동(판매금액의 12.5%)
 * 2등: 3등번호 + 보너스번호 - 3000만원 - 판매 금액에 변동, 당첨 확률: 1,300,000분의1
 * 1등: 6개 숫자 - 당첨 확률 8,000,000 분의 1, 10억 ~ 30억
 */
//hashset을 arraylist로 변환하여 정렬하기 구현
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
class ListComparator implements Comparator<List<Integer>>{
	@Override
	public int compare(List<Integer> l1, List<Integer> l2) {        //compare->두 특정 객체를 비교 즉, l1과 l2를 비교
		Iterator<Integer> ait = l1.iterator();                      //l1초기화
		Iterator<Integer> bit = l2.iterator();						//l2초기화
		while (ait.hasNext()) {
			int anum = ait.next();									//anum은 l1
			int bnum = bit.next();									//bnum은 l2
			if ( anum > bnum) return 1;								//anum이 bnum보다 크면 1을 반환
			else if (anum < bnum) return -1;						//anum이 bnum보다 작으면 -1을 반환
			
		}
		return 0;
	}
}
public class Test_ch10_lotto당첨처리 {

	public static void main(String[] args) {

		lotto_generator(10);
	}
	public static void lotto_generator(int n) {														//변수3개써서 구현
		Random number = new Random();
		HashSet<HashSet<Integer>> lot = new HashSet<>();  									//10000장 복권을 갖은 변수
		HashSet<Integer> lotto = null; 																//복권 1장
		List<List<Integer>> al = new ArrayList<>();

		for (int i = 0; i < n; i++) {  															 //n=10000, lot변수가 10000개 복권을 갖고 있어야함
			lotto = new HashSet<>();
			while (lotto.size() < 7) {
				lotto.add(number.nextInt(46));
			}
			List<Integer> L = new ArrayList<>(lotto);
			Collections.sort(L);
			lot.add(lotto);
			al.add(L);
			System.out.println(lotto.toString());
		}
		
		System.out.println("\nlot hashset을 출력\n");
		for (HashSet<Integer> eachLotto : lot) {
			lotto = new HashSet<Integer>(); //복권 1장
			for (int j = 0; lotto.size() < 7; j++) {	//6개 이하 난수 생성
				lotto.add(number.nextInt(46));			
			}
			List<Integer> lottoList = new ArrayList<>(lotto);
            // 로또 복권을 정렬
            Collections.sort(lottoList);

            // 보너스 번호는 정렬된 목록의 마지막 요소
            int bonus = lottoList.remove(lottoList.size() - 1);

            // 로또 번호 6개와 보너스 번호를 원하는 형식으로 출력
            System.out.println(lottoList + " + 보너스번호: " + bonus);	
            
		}
		System.out.println("복권 정렬전::lot = " + al);
		al.sort(new ListComparator());
		System.out.println("복권 정렬후::lot = " + al);
		//hashset의 리스트를 정렬하는 알고리즘 개발
		//hashset를 arrayList로 변환
		//당첨번호 추첨
		//6개는 hashset으로 하고 보너스는 따로 작성
		HashSet<Integer> win = new HashSet<>();
		for (int j = 0; win.size() < 6; j++) {//6개 번호와 보너스 번호
			win.add(number.nextInt(46));
		}
		System.out.print("당첨번호: " + win);//6개의 당첨번호와 보너스번호
		// 6개를 맞힌 복권을 찾는다 
		System.out.println();
		winnerLotto(win, al);//1등을 찾는다
		
	}
	static void winnerLotto(HashSet<Integer> w,List<List<Integer>> al ) {
		// 당첨번호 w에 대하여 발행된 복권 리스트 al의 모든 원소 elem에 대하여 조사한다
		for (int i = 0; i < al.size(); i++) {
			List<Integer> elem = al.get(i);
			checkWinner(w, elem);
		}
	}
	static void checkWinner(HashSet<Integer> w,List<Integer> elem) {
		// 당첨번호 w의 각 숫자를 꺼내 복권 엔트리에 포함되어 있는지를 조사
		List<Integer> L = new ArrayList<>(w);
		int count = 0;
		for (int i = 0; i < L.size()-1; i++) {//숫자가 몇개가 맞는지 체크 
			if(elem.contains(L.get(i))) {
				count++;
			}
		}
		switch (count) {
		case 0:
		case 1:
		case 2:
			System.out.println("꽝");
			break;
		case 3:
			System.out.println("5등 복권 " + elem + " 당첨번호:" + w);
			break;
		case 4:
			System.out.println("4등 복권 " + elem + " 당첨번호:" + w);
			break;
		case 5:
			if (elem.get(6).equals(L.get(6))) { //보너스 번호가 일치하는 지를 조사 
				System.out.println("2등 복권 " + elem + " 당첨번호:" + w);
				break;
			}
			else {
				System.out.println("3등 복권 " + elem + " 당첨번호:" + w);
				break;
			}
			
		case 6:
			System.out.println("1등 복권 " + elem + " 당첨번호:" + w);
			break;
		}


	}
}
