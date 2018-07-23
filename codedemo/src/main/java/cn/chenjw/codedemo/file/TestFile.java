package cn.chenjw.codedemo.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestFile {

	public static void main(String[] args) {
		System.out.println(args[0]);System.out.println(args[1]);System.out.println(args[2]);
		// TODO Auto-generated method stub
		try {
		BufferedReader br = new BufferedReader(new FileReader(new File("E:/A/abc/query.xml")));
		String s = null;
		while((s = br.readLine())!=null){
		
			System.out.println(s);
		}
		br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
