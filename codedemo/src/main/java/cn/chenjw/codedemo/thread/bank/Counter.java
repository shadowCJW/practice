package cn.chenjw.codedemo.thread.bank;

public class Counter implements Runnable{

	Bank bank;
	
	public Counter(Bank bank){
		this.bank = bank;
	}
	@Override
	public void run() {
		while(bank.money >= 100){
			bank.Counter(100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
