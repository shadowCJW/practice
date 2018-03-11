package cn.chenjw.codedemo.regular;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TEST {
	public static void main(String[] args) {
		//t1();
		//t2();
		t3();
		//t4();
		//t5();
	}

	private static void t5() {
		String rule = "^[\u4E00-\u9FA5]{1,6}$";
		String value = "你好1";
		//String value = "13902335104";
		final Pattern pat = Pattern.compile(rule);
	     final Matcher mat = pat.matcher(value);
	     System.out.println(mat.find());
		
		
	}

	private static void t4() {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "123456");
		map.put("b", "2222");
		for(Entry<String, String> m:map.entrySet()){
			System.out.println(m.getKey()+"==="+m.getValue());
		}
		
	}

	private static void t3() {
		String rule = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]|(19[0-9])))\\d{8}$";
		String value = "13902335104";
		//String value = "13902335104";
		final Pattern pat = Pattern.compile(rule);
	     final Matcher mat = pat.matcher(value);
	     System.out.println(mat.groupCount());
	     System.out.println(mat.find());
	}

	private static void t2() {
		String rule = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
		String value = "粤A123452";
		final Pattern pat = Pattern.compile(rule);
	     final Matcher mat = pat.matcher(value);
	     System.out.println(mat.find());
	}

	private static void t1() {
		String rule = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
		String value = "12.20.12"; 
		final Pattern pat = Pattern.compile(rule);
	     final Matcher mat = pat.matcher(value);
	     System.out.println(mat.find());
		
	}
}
