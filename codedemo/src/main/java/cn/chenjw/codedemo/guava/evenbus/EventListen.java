package cn.chenjw.codedemo.guava.evenbus;

import com.google.common.eventbus.Subscribe;

public class EventListen {
	@Subscribe
	public void listen(EventMessage em){
		System.out.println("recv msg is :"+em.getMessage());
	}
	//只需要在要订阅消息的方法上加上@Subscribe注解即可实现对多个消息的订阅，
	@Subscribe
	public void say(Integer i){
		System.out.println("want to say :"+i);
	}
}
