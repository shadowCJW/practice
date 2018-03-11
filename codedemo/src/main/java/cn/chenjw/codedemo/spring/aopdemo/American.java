package cn.chenjw.codedemo.spring.aopdemo;

public class American implements IHuman{

	@Override
	public void eat() {
		System.out.println("american eat . . . ");
	}

	@Override
	public void sleep() {
		System.out.println("american sleep . . . ");
	}

}
