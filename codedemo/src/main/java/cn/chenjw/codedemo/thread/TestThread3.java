package cn.chenjw.codedemo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestThread3 implements Callable<Integer>{
	/**
	 * 	（1）创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且有返回值。
		（2）创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值。
		（3）使用FutureTask对象作为Thread对象的target创建并启动新线程。
		（4）调用FutureTask对象的get()方法来获得子线程执行结束后的返回值
	 */
	
	public static void main(String[] args) {
		TestThread3 t3 = new TestThread3();
		FutureTask<Integer> ft = new FutureTask<Integer>(t3);
		for(int i =0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"循环变量i的值："+i);
			if(i==5){
				new Thread(ft, "callable线程").start();;
			}
		}
		try {
			System.out.println("callable线程的返回值："+ft.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i =0;i<10;i++){
			sum+=i;
			System.out.println(Thread.currentThread().getName()+": "+i);  
			Thread.sleep(100);
		}
		return sum;
	}
}
