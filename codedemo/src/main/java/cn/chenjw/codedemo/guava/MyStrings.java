package cn.chenjw.codedemo.guava;

import com.google.common.base.Strings;

public class MyStrings {

	public static void main(String[] args) {
		String[] s1 = {"",null,"afx"};
		for(String s : s1){
			//判断是不是空
			boolean b = Strings.isNullOrEmpty(s);
			System.out.println(s+" 是空或null："+b);
			System.out.println("["+s+"]--->"+Strings.emptyToNull(s));//空的变成null
			System.out.println("["+s+"]--->"+Strings.nullToEmpty(s));//null的变成空
		}
		
		System.out.println(Strings.commonSuffix("asdf", "123asdf"));//后缀相同
		System.out.println(Strings.commonPrefix("asf", "asdf123"));//前缀相同的
		System.out.println(Strings.repeat("abc", 2));//指定字符串重复n次
		System.out.println(Strings.padEnd("abc", 6, '*'));//指定字符串不够n位就用指定的字符加上去
		System.out.println(Strings.padStart("abc", 6, '*'));//指定字符串不够n位就用指定的字符加上去
	}

}
