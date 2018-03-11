package cn.chenjw.codedemo.guava.collecttion;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

public class Mycollection {
	public static void main(String[] args) {
		keyMvalue();
		//create();
	}

	/**
	 * 创建和初始化
	 */
	private static void create() {
		List<String> strlist = Lists.newArrayList();
		List<String> strlist2 = Lists.newArrayList("str1","str2");
		
		Map<String, Object> m1 = Maps.newHashMap();
		Map<String, String> m2 = ImmutableMap.of("m1", "MM");
	}

	/**
	 * 一个key多个value
	 */
	private static void keyMvalue() {
		Multimap<String, Object> mp = ArrayListMultimap.create();
		mp.put("a", "a1");
		mp.put("a", "a2");
		
		System.out.println(mp.toString());
		
	}
	
}	
