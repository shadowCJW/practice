package cn.chenjw.codedemo.guava;

import java.util.ArrayList;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;

public class Test {
	public static void main(String[] args) {
		//test();
		test1();
	}

	@SuppressWarnings("unused")
	private static void test1() {
		String s = null;
		//Preconditions.checkArgument(s!="", "bu neng weikong");
		Preconditions.checkNotNull(s);
		System.out.println(Preconditions.checkNotNull(s));
		
	}

	/**
	 * 创建
	 */
	private static void test() {
		ArrayList<String> list = Lists.newArrayList();
		list.add("a");
		System.out.println(list);
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("a", "aaa");
		System.out.println(map);
		
		String[] ss = ObjectArrays.newArray(String.class, 2);
		System.out.println(ss[0]);
		ss[0]="123";
		System.out.println(ss[0]);
	}
}
