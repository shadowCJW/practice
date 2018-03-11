package cn.chenjw.codedemo.tdd.mockito;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cn.chenjw.codedemo.bean.User;
import static org.mockito.Mockito.*;;
public class Test234 {
	@Mock   User user;
	
	 @Before  
	    public void initMocks() {  
	        // 初始化当前测试类所有@Mock注解模拟对象  
	        MockitoAnnotations.initMocks(this);  
	    }  
	 
	@Test
	public void test(){
		// mock creation 创建mock对象 
		List list = mock(List.class);
		list.add("aa");
		list.clear();
		 //1验证行为有没有发生
		verify(list).add("aa");
		verify(list).clear();
		//verify(list).add("bb");
		
		 // 2测试桩
		 when(list.get(2)).thenReturn("first");
		 System.out.println("测试桩："+list.get(0));
		 
		 //3参数匹配器
		 when(list.get(anyInt())).thenReturn("any");//还有其他的匹配器如eq()/、、、
		 System.out.println("参数匹配器："+list.get(99));
		
		 
		 //4验证函数的确切、最少、从未调用次数
		 list.add("a");
		 list.add("bb");
		 list.add("bb");
		 //下面两行一样
		 verify(list).add("a");
		 verify(list,times(1)).add("a");
		 //具体次数
		 verify(list ,times(2)).add("bb");
		 //从不
		 verify(list,never()).add("c");
		 //最少最多
		 verify(list,atLeastOnce()).add("a");
		 verify(list,atLeast(2)).add("bb");
		 verify(list,atMost(1)).add("a");
		 
		 //验证执行顺序
		 InOrder ino = inOrder(list);
		 
		//验证执行顺序
		 list.add("11");
		 list.add("22");
		 ino.verify(list).add("11");
		 ino.verify(list).add("22");
		 
	}
	@Test
	public void test1(){
		List list = mock(List.class);
		//查找冗余的调用
		list.add("aa");
		verify(list).add("aa");
		verifyNoMoreInteractions(list);
		
		
	/*	when(this.user.getAge()).thenReturn(11);
		System.out.println(user.getAge());*/
		
		//为连续的调用做测试桩 (stub)
		/*when(user.getAge()).thenThrow(new RuntimeException())
		.thenReturn(12);
		System.out.println(this.user.getAge());
		System.out.println(this.user.getAge());*/
		when(user.getAge()).thenReturn(11, 12,23);
		System.out.println(this.user.getAge());
		System.out.println(this.user.getAge());
		System.out.println(this.user.getAge());
		System.out.println(this.user.getAge());
		
		
		
	}
	
	@Test
	public void test2(){
		List list = new ArrayList();
		List spy = spy(list);
		when(spy.size()).thenReturn(100);
		
		spy.add("a");spy.add("b");
		System.out.println(spy.get(0).toString()+spy.get(1).toString());
		System.out.println(spy.size());
	}

}
