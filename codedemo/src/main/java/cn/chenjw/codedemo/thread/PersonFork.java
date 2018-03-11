package cn.chenjw.codedemo.thread;
/**
 * 实例2 人和叉子的问题，有5个人，5个叉，他们围城一圈，叉子依次摆在他们相邻的地方，
 * 只有一个人的左右手边叉子都没被用的时候，这个人才拿起叉子(左右2个都被拿起)吃饭，
 * 吃完后1秒，将叉子放下，若每个人吃一次之后就不再吃了，模仿他们吃饭的顺序。
 * @author cjw
 *这里的Person是不同的5个人，所以在实现Runnable接口的方法中也并没有将其共享资源，而是放到5个不同的线程中。
 */
public class PersonFork {
	public static void main(String[] args) {
		Forks fork = new Forks();
		Person p1 = new Person("p1",fork);
		Person p2 = new Person("p2",fork);
		Person p3 = new Person("p3",fork);
		Person p4 = new Person("p4",fork);
		Person p5 = new Person("p5",fork);
		new Thread(p1, "0").start();
		new Thread(p2, "1").start();
		new Thread(p3, "2").start();
		new Thread(p4, "3").start();
		new Thread(p5, "4").start();
	}
}
class Person implements Runnable{
	private String personName;
	private Forks fork;
	Person (String name ,Forks forkInput){
		this.personName =name;
		this.fork = forkInput;
	}
	@Override
	public void run() {
		fork.getFork();
		System.out.println("i am eating "+ personName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fork.putFork();
	}
	
}
class Forks{
	private int forks[] = {0,0,0,0,0};
	public synchronized void getFork(){
		String threadName = Thread.currentThread().getName();
		int num = Integer.parseInt(threadName);
		while(1 == forks[num] || 1 == forks[(num+1)%5]){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		forks[num]=1;
		forks[(num+1)%5]=1;
	}
	public synchronized void putFork(){
		String threadName = Thread.currentThread().getName();
		int num = Integer.parseInt(threadName);
		forks[num] = 0 ;
		forks[(num +1)%5]=0;
		notifyAll();
	}
}