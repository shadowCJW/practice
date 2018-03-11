package cn.chenjw.codedemo.thread;

public class TestRunnable implements Runnable{

	private int count = 15;
	@Override
	public void run() {
		for(int i = 0 ;i< 5;i++){
			System.out.println(Thread.currentThread().getName()+"执行了，count="+count--);
		}
	}

}
