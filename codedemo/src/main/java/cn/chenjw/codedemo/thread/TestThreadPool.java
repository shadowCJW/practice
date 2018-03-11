package cn.chenjw.codedemo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
		
		for(int i = 0;i<15;i++){
			Mytask myTask = new Mytask(i);
			executor.execute(myTask);
			System.out.println("线程池中线程数目："+executor.getPoolSize()
				+"，队列中等待执行的任务数目："	+executor.getQueue().size()+",已执行完表的任务数目："+executor.getCompletedTaskCount()
					);
		
	}
		executor.shutdown();
}
}
class Mytask implements Runnable{

	private int taskNum;
	public Mytask(int num){
		this.taskNum = num;
	}
	@Override
	public void run() {
		System.out.println("正在执行task："+taskNum);
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("task"+taskNum+":执行完毕！");
	}
	
}
