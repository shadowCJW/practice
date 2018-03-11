package cn.chenjw.codedemo.spring.aopcglib;

public class ChinesePerson implements IPerson {

	@Override
	public void eat() {
		System.out.println("中国人吃饭。。。");
	}

	@Override
	public void sleep() {
		System.out.println("中国人睡觉。。。");

	}

}
