package cn.chenjw.codedemo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class Simple {

	public static void main(String[] args) {
		List<String> list = Lists.newArrayList();
		list.add("b");list.add("a");list.add("c");
		list.forEach(p -> {System.out.println(p);});
		
		
		List<String> str = Arrays.asList("c","b","a","d");
		List<String> res = str.stream().sorted((s1,s2) -> s1.compareTo(s2)).collect(Collectors.toList());
		System.out.println(res);
	}

}
