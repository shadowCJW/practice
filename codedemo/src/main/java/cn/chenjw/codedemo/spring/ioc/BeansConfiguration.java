package cn.chenjw.codedemo.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
	@Bean
	public Teacher teacher(){
		Teacher t = new Teacher();
		t.setName("语文老师");
		return t;
	}
	@Bean
	public Student student(){
		Student s = new Student();
		s.setName("学生哦");
		s.setTeacher(teacher());
		return s;
	}

}
