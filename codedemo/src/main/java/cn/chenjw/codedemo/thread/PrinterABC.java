package cn.chenjw.codedemo.thread;
/**
 *   建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，
 *   要求线程同时运行，交替打印10次ABC。这个问题用Object的wait()，notify()就可以很方便的解决。
 * @author cjw
 *有问题不会自动停止
 */
public class PrinterABC implements Runnable{

	private String name;
	private Object prev;
	private Object self;
	
	public PrinterABC(String name,Object prev,Object self){
		this.name = name;
		this.prev = prev;
		this.self = self;
	}
	
	@Override
	public void run() {
		int count = 10;
		while(count>0){
			synchronized (prev) {
				synchronized (self) {
					System.out.print(name);
					count--;
					self.notify();
				}
				try {
					prev.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
