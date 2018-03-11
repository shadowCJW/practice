package cn.chenjw.codedemo.thread;
/**
 * join()
 * @author cjw
 *
 */
public class TestThread2 extends Thread {
	private String name ;
	public TestThread2(String name){
		this.name=name;
	}
	public void run() {
		System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
		for(int i =0;i<5;i++){
			 System.out.println("子线程"+name + "运行 : " + i);
		}
		System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
	}
}
