package cn.chenjw.codedemo.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testSort();
		testStr();
	}

	private static void testStr() {
		List<String> proNames = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
		List<String> up = proNames.stream().map(name->{return name.toLowerCase();}).collect(Collectors.toList());
		System.out.println(up);
	}

	private static void testSort() {
		List<Integer> list = Arrays.asList(new Integer[]{1 ,5,6,2,4,3,8});
		Collections.sort(list,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
			
		});
		System.out.println("方式1"+list);
		//使用lambeda
		List<Integer> list2 = Arrays.asList(new Integer[]{1 ,5,6,2,4,3,8});
		Collections.sort(list2,(a,b)->a.compareTo(b) );
		System.out.println("方式2"+list2);
	}

}
