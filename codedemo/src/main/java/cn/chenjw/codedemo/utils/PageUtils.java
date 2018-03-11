package cn.chenjw.codedemo.utils;

import java.util.ResourceBundle;

public class PageUtils {
	//bundle:一批，附随的,可以读取到resource文件下面的配置文件，根据key获取对应的值
	 private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application");
	 public static String getString(final String key) {
	        return BUNDLE.getString(key);
	    }
	 
	 public static void main(String[] args) {
		System.out.println(getString("name"));
		System.out.println(getString("user.name"));
		
	}
}
