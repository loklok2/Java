package com.ruby.java.ch12;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;


public class Test04 {
	
	public static void main(String[] args) {													//a.txt 파일과 연결된 FileInputStream을 생성하고
		try(BufferedInputStream in = new BufferedInputStream(new FileInputStream("a.txt"));    // 추가 버퍼로 처리가능한  BufferedInputStream 생성 
			ByteArrayOutputStream out = new ByteArrayOutputStream();) {							
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = in.read(buf)) != -1) {
			out.write(buf, 0 ,len);
		}
		byte[] arr = out.toByteArray();
		String s = new String(arr);
		System.out.println(s);
		
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}