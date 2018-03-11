package cn.chenjw.codedemo.thread.bank;

public class ATM implements Runnable{
	Bank bank;
	public ATM(Bank bank){
		this.bank = bank;
	}
	@Override
	public void run() {
		while(bank.money >= 200){
			bank.ATM(200);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
