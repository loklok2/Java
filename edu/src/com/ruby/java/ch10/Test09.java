package com.ruby.java.ch10;

import java.util.Map;
import java.util.TreeMap;

public class Test09 {
//treemap 514p
	public static void main(String[] args) {
		TreeMap<String, String> users = new TreeMap<String, String>();

		users.put("20080319", "��Ǫ��");
		users.put("20070620", "���ϴ�");
		users.put("20050817", "������");
		users.put("20120728", "������");
		users.put("20120924", "����");

		System.out.println(users);
	//	내부
		Map.Entry<String, String> entry = null;
	
		entry = users.firstEntry();
		print("�Ի����ڰ� ���� ���� ��� ", entry);

		entry = users.lastEntry();
		print("�Ի����ڰ� ���� ���� ���", entry);

		entry = users.lowerEntry("20121231");
		print("2012�⵵�� ���� �ʰ� �Ի��� ���", entry);

		entry = users.higherEntry("20120101");
		print("2012�⵵�� ���� ������ �Ի��� ���", entry);

		while (!users.isEmpty()) {
			entry = users.pollFirstEntry();
			print("������ ���", entry);
			System.out.println("�����ִ� ���� �� : " + users.size());
		}
	}

	public static void print(String s, Map.Entry<String, String> entry) {
		System.out.println(s + " : " + entry.getKey() + " / " + entry.getValue());
	}
}