package cn.chenjw.codedemo.guava.collecttion;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.Multisets;
import com.google.common.collect.SetMultimap;

public class Multi {
	public static void main(String[] args) {
		//set();
		//map();
		big();
	}

	private static void big() {
		// 可以用 inverse()反转BiMap<K, V>的键值映射
		//保证值是唯一的，因此 values()返回Set而不是普通的Collection
		//在BiMap中，如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常。如果对特定值，你想要强制替换它的键，请使用 BiMap.forcePut(key, value)。
		BiMap<Integer, String> bm = HashBiMap.create();
		bm.put(1, "apple");bm.put(2, "banana");bm.put(3, "cat");
		Integer mid = bm.inverse().get("apple");//根据value找key
		String mval = bm.get(1);
		System.out.println(mid +"=="+mval);
		
		
	}

	private static void map() {
		//某处实现过Map<K, List<V>>或Map<K, Set<V>>，并且要忍受这个结构的笨拙。
		// Multimap可以很容易地把一个键映射到多个值
		//Multimap不是Map
		Multimap<String, ArrayList<String>> s = ArrayListMultimap.create();
		Multimap<String, String> mm = ArrayListMultimap.create();
		for(int i =0;i<5;i++){
			mm.put("tom", "tom"+i);
		}
		for(int i =0;i<5;i++){
			mm.put("amy", "amy"+i);
		}
		System.out.println("size:"+mm.size());
		System.out.println("keyset size:"+mm.keySet().size());
		System.out.println("if containskey tom:"+mm.containsKey("tom"));
		System.out.println("if containval amy2:"+mm.containsValue("amy2"));
		System.out.println(mm.asMap());
		
		
		//各种实现
		HashMultimap<String, String> create = HashMultimap.create();
		for(int i =0;i<5;i++){
			create.put("amy", "amy"+i);
		}
		System.out.println(create.asMap());
		
	}

	private static void set() {
		//统计一个词在文档中出现了多少次,
		// Multiset，它可以多次添加相等的元素。没有元素顺序限制的ArrayList<E>
		//Map<E, Integer>，键为元素，值为计数
		//当把Multiset看作Map<E, Integer>时，它也提供了符合性能期望的查询操作：
		Multiset<String> ms = HashMultiset.create();
		String[] str = "aa,bb,cc,dd,aa,cc,aa,bb".split(",");
		for(String s : str){
			ms.add(s);
		}
		System.out.println(ms.count("aa"));
		System.out.println(ms.count("cc"));
		System.out.println(ms.elementSet());
		System.out.println(ms.entrySet());
		//遍历
		for(Entry<String> e :ms.entrySet()){
			System.out.println(e.getCount());
			System.out.println(e.getElement());
		}
		System.out.println("======");
		Iterator<String> iterator = ms.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
