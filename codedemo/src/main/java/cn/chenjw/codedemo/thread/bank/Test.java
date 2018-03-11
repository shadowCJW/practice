package cn.chenjw.codedemo.thread.bank;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank bank = new Bank();
		Counter c = new Counter(bank);
		ATM atm = new ATM(bank);
		PersonA pa = new PersonA(bank);
		PersonB pb = new PersonB(bank);
		/*pa.start();
		pb.start();*/
		new Thread(c).start();
		new Thread(atm).start();
		
		
		
	}

}
