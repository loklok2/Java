package com.ruby.java.ch12;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test77 {

	public static void main(String[] args) {
		try(FileInputStream fi = new FileInputStream("m.txt");
				FileOutputStream fo = new FileOutputStream("m1.txt");) {
					int t = 0;
					while((t = fi.read()) != -1) {
						fo.write(t);
					}
					
					
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
