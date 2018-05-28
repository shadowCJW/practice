package cn.chenjw.codedemo.spring.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("teacher2")
public class Teacher2 {
	@Value("laoshi")
	private String name;

	public String getName() {
		return name;
	}

	
}
