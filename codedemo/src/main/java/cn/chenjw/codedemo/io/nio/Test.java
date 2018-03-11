package cn.chenjw.codedemo.io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RandomAccessFile file = new RandomAccessFile("D:\\A\\aaa.txt", "rw");
			FileChannel in = file.getChannel();
			ByteBuffer bf = ByteBuffer.allocate(48);
			int byteRead =in.read(bf);
			while(byteRead!=-1){
				bf.flip();
				while(bf.hasRemaining()){
					System.out.print((char) bf.get());//中文会乱码？
				}
				bf.clear();
				byteRead = in.read(bf);
				
			}
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
