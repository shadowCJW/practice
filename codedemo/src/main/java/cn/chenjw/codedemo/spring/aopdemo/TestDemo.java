package cn.chenjw.codedemo.spring.aopdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ApplicationContext context = new ClassPathXmlApplicationContext("aopbean.xml");
	        //要用接口接收
	        IHuman c= (IHuman)context.getBean("chinese");
	        c.eat();
	        c.sleep();
	        System.out.println("*********************************");
	        //要用接口接收
	        IHuman a= (IHuman) context.getBean("american");
	        a.eat();
	        a.sleep();
	}

}
