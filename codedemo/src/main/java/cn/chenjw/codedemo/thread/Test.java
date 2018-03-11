package cn.chenjw.codedemo.thread;

public class Test {

	public static void main(String[] args) {
		//test1();
		//test2();
		//test3();
		try {
			test4();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//saletick();
	}

	private static void saletick() {
		SaleTickets st = new SaleTickets();
		Thread t1 = new Thread(st, "窗口1");
		Thread t2 = new Thread(st, "窗口2");
		Thread t3 = new Thread(st,"窗口3");
		t1.start();
		t2.start();
		t3.start();
	}

	private static void test4() throws InterruptedException {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		
		PrinterABC pa = new PrinterABC("A", c, a);
		PrinterABC pb = new PrinterABC("B", a, b);
		PrinterABC pc = new PrinterABC("c", b, c);
		
		new Thread(pa).start();
		Thread.sleep(100);
		new Thread(pb).start();
		Thread.sleep(100);
		new Thread(pc).start();
		Thread.sleep(100);
	}

	//测试testthread2的方法
	private static void test3() {
		 System.out.println(Thread.currentThread().getName()+"主线程运行开始!");  
	        TestThread2 mTh1=new TestThread2("A");  
	        TestThread2 mTh2=new TestThread2("B");  
	        mTh1.start();  
	        mTh2.start();  
	        try {  
	            mTh1.join();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        try {  
	            mTh2.join();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");  
	  
	}

	private static void test2() {
		TestRunnable t = new TestRunnable();
		new Thread(t, "a").start();
		new Thread(t, "b").start();
		new Thread(t, "c").start();;
	}

	private static void test1() {
		Thread t1 = new TestThread("A");
		Thread t2 = new TestThread("B");
		t1.start();
		t2.start();
	}

}
