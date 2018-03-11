package cn.chenjw.codedemo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	private static void test() {
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("a", "apple");map.put("b", "banana");
		
		Set<Entry<String, String>> entrySet = map.entrySet();
		for(Entry<String, String> entry :entrySet) {
			System.out.println(entry.getKey()+"=="+entry.getValue());
		}
		//jdk8+lambda表达式
		StringBuilder sb = new StringBuilder();
		/*map.forEach((k,v)->System.out.println("Item : " + k + "==Count : " + v));*/
		map.forEach((k,v)->sb.append(k).append("==>").append(v).append(";"));
		System.out.println(sb.toString());
		
	}
	/**
	 * 数组array集合互换
	 */
	private static void tset4() {
		List<String> list = new ArrayList<String>(2);
		list.add("a");list.add("b");
		
		String[] arr = {"aa","bb","cc"};
		
		String[] a1 = new String[list.size()];
		a1 =  list.toArray(a1);
		
		List<String> l1 = Arrays.asList(arr);
		//l1.add("123");
		System.out.println();
		
	}

}
