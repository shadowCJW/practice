package cn.chenjw.codedemo.thread;
/**
 * 使用对象锁
 * @author chenjw
 * 2017年11月5日
 */
public class SaleTickets implements Runnable{
	private int tickets = 10;
	Object lock = new Object();
	@Override
	public void run() {
		while(tickets>0){
			saleTicket();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void saleTicket() {
		synchronized (lock) {
			if(tickets>0){
				tickets--;
				System.out.println(Thread.currentThread().getName()+"正在卖票，还剩下"+tickets+"张票");
			}else{
				System.out.println("票已经卖完了");
				return;
			}
		}
	}

}
