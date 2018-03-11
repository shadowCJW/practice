package cn.chenjw.codedemo.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioUtil {

	public static void copyFileByNio(String src,String dst){
		try {
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(dst);
			//获得传输通道channel
			FileChannel inChannel = fis.getChannel();
			FileChannel outChannel = fos.getChannel();
			
			//获取容器buffer
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while(true){
				int eof = inChannel.read(buffer);
				if(eof == -1){
					break;
				}
			}
			//重设一下buffer的position=0，limit=position
			buffer.flip();
			outChannel.write(buffer);
			
			 //写完要重置buffer，重设position=0,limit=capacity
			buffer.clear();
			inChannel.close();
			outChannel.close();
			fis.close();
			fos.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		copyFileByNio("src/test/java/temp/1215.txt", "src/test/java/temp/1215-bak.txt");
		System.out.println("copy 完成");
	}
	
}
