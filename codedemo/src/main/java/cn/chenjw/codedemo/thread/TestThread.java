package cn.chenjw.codedemo.thread;

public class TestThread extends Thread{
	private String name;
	public TestThread(String name){
		this.name = name;
	}
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(name +"运行了:"+ i);
			
		}
	}
}
