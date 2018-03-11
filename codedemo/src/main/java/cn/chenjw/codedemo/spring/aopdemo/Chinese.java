package cn.chenjw.codedemo.spring.aopdemo;

public class Chinese implements IHuman{

	@Override
	public void eat() {
		System.out.println("Chinese eat。。。");
	}

	@Override
	public void sleep() {
		System.out.println("chinese sleep。。。");
	}

}
