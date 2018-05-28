package cn.chenjw.codedemo.spring.ioc;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("student2")
public class Student2 {
	@Value("cjw")
	private String name;
	@Resource  
	private Teacher2 teacher;
	public String getName() {
		return name;
	}
	public Teacher2 getTeacher() {
		return teacher;
	}
	
	

	
}
