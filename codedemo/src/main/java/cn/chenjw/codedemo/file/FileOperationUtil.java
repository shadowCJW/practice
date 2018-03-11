package cn.chenjw.codedemo.file;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileOperationUtil {
	/**
	 * 删除某一个文件目录
	 * @param file
	 * @return
	 */
	public static boolean deleteFolder(File file){
		try {
		FileUtils.deleteDirectory(file);
		FileUtils.deleteQuietly(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		boolean bb = deleteFolder(new File("D:\\A\\abc\\a"));
		System.out.println(bb);

	}
	
}
