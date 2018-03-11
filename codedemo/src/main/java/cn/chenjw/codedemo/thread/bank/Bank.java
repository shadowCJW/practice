package cn.chenjw.codedemo.thread.bank;

public class Bank {
 static int money = 1000;
	
	public  synchronized void Counter(int money){
		Bank.money -= money;
		System.out.println("从柜台取走了"+money+"元钱，还剩下"+this.money);
	}
	
	public synchronized void ATM(int money){
		Bank.money -= money;
		System.out.println("从ATM取走了"+money+"元钱，还剩下"+this.money);
	}
}
