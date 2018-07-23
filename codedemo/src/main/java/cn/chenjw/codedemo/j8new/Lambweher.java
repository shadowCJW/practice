package cn.chenjw.codedemo.j8new;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.chenjw.codedemo.bean.User;

public class Lambweher {
	public static void main(String[] args) {
		List<User> ul = new ArrayList<User>();
		ul.add(new User("1", "aa", 12));
		ul.add(new User("2", "bb", 13));
		ul.add(new User("3", "cc", 14));
		ul.add(new User("4", "dd", 15));
		ul.add(new User("5", "ee", 16));
		List<User> uu = new ArrayList<User>();
		uu = ul.stream().filter(s -> s.getAge() >14).collect(Collectors.toList());
		System.out.println(ul);
		System.out.println(uu);
	}
}
