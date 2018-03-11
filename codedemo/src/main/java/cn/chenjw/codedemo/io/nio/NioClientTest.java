package cn.chenjw.codedemo.io.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NioClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket s = new Socket("localhost", 8080);
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		// 输出 
		PrintWriter out = new PrintWriter(os,true);
		out.print("getpubl 你好2");
		out.flush();
		s.shutdownOutput();
		
		// 输入  
		Scanner in = new Scanner(is);
		StringBuilder sb = new StringBuilder();
		while(in.hasNextLine()){
			String line = in.nextLine();
			sb.append(line);
		}
		String response = sb.toString();
		System.out.println("resp =="+response);
	}

}
