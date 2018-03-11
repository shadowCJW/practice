package cn.chenjw.codedemo.guava.collecttion;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

public class Immutable {

	public static void main(String[] args) {
		//不可变集合
		// TODO Auto-generated method stub
/*		copyOf方法，如ImmutableSet.copyOf(set);
		of方法，如ImmutableSet.of(“a”, “b”, “c”)或 ImmutableMap.of(“a”, 1, “b”, 2);
		Builder工具，如*/
	/*	public static final ImmutableSet<Color> GOOGLE_COLORS =
		        ImmutableSet.<Color>builder()
		            .addAll(WEBSAFE_COLORS)
		            .add(new Color(0, 191, 255))
		            .build();
*/
		ImmutableSet<String> of = ImmutableSet.of("b", "c","a","a");
		ImmutableMap<String, String> of2 = ImmutableMap.of("a", "aa", "b", "bb");
		System.out.println(of);System.out.println(of2);
		System.out.println(ImmutableSortedSet.of("b", "c","a","a"));
		
		//比想象中更智能的copyOf
		System.out.println(ImmutableList.copyOf(of));
	}

}
