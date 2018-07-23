package cn.chenjw.codedemo.j8new.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalDemo {
	public static void main(String[] args) {
		// 字符串连接，concat = "ABCD"
		String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat); 
		System.out.println(concat);
		//
		limitAskip();
		match();
	}

	private static void match() {
		List<Integer> ilist = Arrays.asList(1,3,5,9,22);
		boolean r1 = ilist.stream().allMatch(p -> p>5 );
		System.out.println("是否全部大于5："+r1);
		System.out.println("是否存在大于5："+ilist.stream().anyMatch(p -> p>5));
		System.out.println("是否全部小于5："+ilist.stream().noneMatch(p -> p>5));
		
	}

	private static void limitAskip() {
		List<String> list = Arrays.asList("a","b","c","d","e","f");
		List<String> list2 = list.stream().limit(3).skip(2).collect(Collectors.toList());
		System.out.println(list2);
		//
		List<String> list3 = Arrays.asList("g","b","a","z","e","f");
		List<String> list4 = list3.stream().sorted((a,b)-> a.compareTo(b)).limit(3).collect(Collectors.toList());
		System.out.println(list4);
		
		
		
	}
}
