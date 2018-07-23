package cn.chenjw.codedemo.j8new.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamDemo {
	public static void main(String[] args) {
		//'forEach' 来迭代流中的每个数据
		Random ran = new Random();
		ran.ints().limit(5).forEach(System.out::println);
		
		//map 方法用于映射每个元素到对应的结果，
		List<Integer> num = Arrays.asList(3,2,3,5,4);
		List<Integer> square = num.stream().map(i -> i*i).distinct().collect(Collectors.toList());
		System.out.println(square);
		
		//filter 方法用于通过设置的条件过滤出元素
		List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		// 获取空字符串的数量
		int count = (int) strings.stream().filter(string -> string.isEmpty()).count();
		System.out.println(count);
		
		//sorted 方法用于对流进行排序。
		Random random = new Random();
		random.ints().limit(3).sorted().forEach(System.out::println);
		
		//parallelStream 是流并行处理程序的代替方法。
		// 获取空字符串的数量
		long count2 = strings.parallelStream().filter(string -> string.isEmpty()).count();
		System.out.println(count2);
		
		//Collectors 类实现了很多归约操作，
		
		List<String> filterd = strings.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
		System.out.println("筛选后的："+filterd);
		String mergedStr = strings.stream().filter(str -> !str.isEmpty()).collect(Collectors.joining(","));
		System.out.println("聚合后的str："+mergedStr);
		
		//一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
		IntSummaryStatistics stats = num.stream().mapToInt(x -> x).summaryStatistics();
		System.out.println("列表中最大的数 : " + stats.getMax());
		System.out.println("列表中最小的数 : " + stats.getMin());
		System.out.println("列表中数 的和: " + stats.getSum());
		System.out.println("列表中的平均数: " + stats.getAverage());
		System.out.println("列表中个数 : " + stats.getCount());
		

		
	}
}
