package cn.chenjw.codedemo.guava;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public class Myspliter {
	public static void main(String[] args) {
		spliter("a, ,  b  ,c,d  ,f");
		string(null);
		
	}


	private static void string(String string) {
		String s = Strings.emptyToNull(string);//空的返回值为null
		String s1 = Strings.nullToEmpty(string);//空的返回值为""
		System.out.println("-->"+s);
		System.out.println("-->"+s1);
	}

	private static void spliter(String string) {
		//将字符串分解成数组,并且去掉空的字符串
		Iterable<String> split = Splitter.on(",").trimResults().omitEmptyStrings().split(string);
		//没有去掉空的字符串
		System.out.println(Splitter.on(",").trimResults().split(string));
		System.out.println(split);
	}
}
