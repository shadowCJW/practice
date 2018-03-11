package cn.chenjw.codedemo.spring.aopcglib;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testdm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  ApplicationContext ctx = new ClassPathXmlApplicationContext("aopZhujie.xml");  
		  IPerson cp =  (IPerson) ctx.getBean("personServiceBean");
		  cp.eat();
		  cp.sleep();
	}

}
