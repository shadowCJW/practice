package cn.chenjw.codedemo.designpattern.singleton;



public class Singletontest  {

	private static class SingletontestHolder{
		private static final Singletontest INSTANCE = new Singletontest();
	}
	private Singletontest(){}
	public static final Singletontest getInstance(){
		return SingletontestHolder.INSTANCE;
	}

	private  String name;
	private  String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void info(){
		System.out.println(name +"'s sex is "+sex);
	}
	public static void main(String[] args) {
		
		Singletontest boy = Singletontest.getInstance();
		boy.setName("xiaom");boy.setSex("man");
		boy.info();
		Singletontest girle = Singletontest.getInstance();
		girle.setName("xiaohong");girle.setSex("woman");
		girle.info();
		boy.info();
		
		
	}
}
