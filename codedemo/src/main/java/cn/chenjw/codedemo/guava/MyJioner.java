package cn.chenjw.codedemo.guava;

import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MyJioner {
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList();
		list.add("a");list.add(null);list.add("c");list.add("d");
		
		normal(list);
		jioner(list);
		
		Map<String, String> map = Maps.newHashMap();
		map.put("name", "chen");
		map.put("id", "123");
		mapjoiner(map);
	}

	/**
	 * 将map的key和value打印出来
	 * @param map
	 */
	private static void mapjoiner(Map<String, String> map) {
		MapJoiner mj = Joiner.on(",").withKeyValueSeparator("=");
		System.out.println(mj.join(map));
	}

	private static void jioner(List<String> list) {
		//on指分隔的符号
		//会去掉null
		String ss = Joiner.on(":").skipNulls().join(list);
		//会将null换成你要的值
		String ss2 = Joiner.on("+").useForNull("N").join(list);
		System.out.println("1: "+ss);
		System.out.println("2: "+ss2);
	}

	private static void normal(List<String> list) {//原生java写的拼接字符串
		StringBuffer sb = new StringBuffer();
		for(String s :list){
			if(s!=null){
				sb.append(s);
				if(list.indexOf(s)+1!=list.size()){
					sb.append(",");
				}
			}
		}
		System.out.println(sb);
	}
}
