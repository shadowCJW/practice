package cn.chenjw.codedemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component
public class Testss {
	private String id = "aa";
	private String name = "22";
	@PostConstruct
	public void init(){
		System.out.println(id+":"+name);
	}
	 @PreDestroy  
	    public void destroyMethod() throws Exception {  
	        System.out.println("destroyMethod 被执行");  
	    }  
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Testss.class);
          System.out.println("#################################");
          Testss s = new Testss();
          System.out.println(s.id);
          context.close();
	}

}
