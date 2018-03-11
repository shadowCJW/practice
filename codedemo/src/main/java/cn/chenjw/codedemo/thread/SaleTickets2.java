package cn.chenjw.codedemo.thread;
/**
 * 使用同步方法
 * @author chenjw
 * 2017年11月5日
 */
public class SaleTickets2 implements Runnable {
	private int tickets = 10;
	@Override
	public void run() {
		for(int i =0 ;i<20;i++){
			if(tickets>0){
				sale();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private synchronized void sale() {
		if(tickets>0){
			System.out.println("第" + Thread.currentThread().getName() + "窗口卖出的票号为：" + tickets);
			tickets--;
		}
	}
	public static void main(String[] args) {
		SaleTickets2 s = new SaleTickets2();
		Thread t1 = new Thread(s, "A");
		Thread t2 = new Thread(s, "B");
		Thread t3 = new Thread(s, "c");
		t1.start();t2.start();t3.start();
	}

}
