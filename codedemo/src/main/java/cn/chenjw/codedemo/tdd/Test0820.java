package  cn.chenjw.codedemo.tdd;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Test0820 {

	@Test
	public void test() {
		int h = Testcase.add(1, 3);
		assertEquals(4, h);
	}
	@Test
	public void test2() {
		int h = Testcase.add(2, 3);
		
		assertEquals(5, h);
	}
	@Test
	public void test3(){
		List<String> list = mock(ArrayList.class);
		list.add("once");
		list.add("twice");
		list.add("twice");
		list.add("three");
		list.add("three");
		list.add("three");
		verify(list).add("once");
		//verify(list,times(2)).add("once");和
		
		verify(list, atLeastOnce()).add("once");
		verify(list,atLeast(2)).add("once");
	}
}
