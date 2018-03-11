package cn.chenjw.codedemo.guava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.google.common.io.Closer;


public class SimpleIo {
	public static void main(String[] args) {
		test();
	}
/**
 *将输入流和输出流注册到closer里面，到后面统一关闭
 */
	private static void test() {
		Closer closer = Closer.create();
		try {
			File file = new File("D:/A/aaa.txt");
			InputStream is = closer.register(new FileInputStream(file));
			FileOutputStream fos = closer.register(new FileOutputStream(new File("D:/A/aaa720.txt")));
			IOUtils.copy(is, fos);
			System.out.println("成功了");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				closer.close();//
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
