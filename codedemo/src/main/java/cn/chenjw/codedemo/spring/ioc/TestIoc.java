package cn.chenjw.codedemo.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestIoc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//iocByXML();
		//iocByBean();
		iocByBeansConfiguration();
	}

	private static void iocByBeansConfiguration() {
		 AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BeansConfiguration.class);  
	        Student student= (Student) context.getBean("student");  
	        Teacher teacher= (Teacher) context.getBean("teacher");  
	        System.out.println("学生的姓名："+student.getName()+"。老师是"+student.getTeacher().getName());  
	        System.out.println("老师的姓名："+teacher.getName()); 	
		
	}

	private static void iocByBean() {
		ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext2.xml");
		Student2 student = (Student2) context.getBean("student2");
		Teacher2 teacher = (Teacher2) context.getBean("teacher2");
		System.out.println("学生的姓名："+student.getName()+"。老师是"+student.getTeacher().getName());  
	    System.out.println("老师的姓名："+teacher.getName()); 
	}

	private static void iocByXML() {
		ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
		Student student = (Student) context.getBean("student");
		Teacher teacher = (Teacher) context.getBean("teacher");
		System.out.println("学生的姓名："+student.getName()+"。老师是"+student.getTeacher().getName());  
	    System.out.println("老师的姓名："+teacher.getName()); 
	}

}
