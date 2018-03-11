package cn.chenjw.codedemo.guava.evenbus;

import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class TestEventBus {
	
	@Test
	public void test(){
		EventBus eb = new EventBus("test");
		EventListen el = new EventListen();
		eb.register(el);
		eb.post(new EventMessage("apple"));
		
	}
	@Test
	public void testmui(){
		EventBus bus = new EventBus("say");
		EventListen listen = new EventListen();
		bus.register(listen);
		
		bus.post(new EventMessage("banana"));
		bus.post(Integer.valueOf(12));
		
	}
}
