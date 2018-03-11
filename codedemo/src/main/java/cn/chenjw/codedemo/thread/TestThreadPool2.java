package cn.chenjw.codedemo.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool2 {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			Runnable worker = new Worker(i);
			executor.equals(worker);
		}
		executor.shutdown();
		while(!executor.isTerminated()){
			
		}
		System.out.println("Finished all threads");
	}
}
class Worker implements Runnable{
	private int command;
	public Worker(int comm){
		this.command = comm;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+ "开始工作：,command为："+command);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"结束！");
	}
	
}