package cn.chenjw.codedemo.spring.aopcglib;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyInterceptor {
	@Pointcut("execution(* cjw.cn.com.spring.aopcglib.ChinesePerson.eat(..))")
	private void anyMethod(){}//定义一个切入点
	
	@Pointcut("execution(* cjw.cn.com.spring.aopcglib.ChinesePerson.sleep(..))")
	private void sleepMethod(){}
	/*@Pointcut("execution(* com.bird.service.impl.PersonServiceBean.*(..))")  
	这句话是方法切入点，execution为执行的意思，*代表任意返回值，然后是包名，.*意思是包下面的所有子包。(..)代
	表各种方法.*/
	@Before("anyMethod()")
	public void wash(){
		System.out.println(" 洗手 ！！");
	}
	@After("anyMethod()")
	public void clean(){
		System.out.println("刷牙！！");
	}
	@Before("sleepMethod()")
	public void bathe(){
		System.out.println("洗澡！！");
	}
}
