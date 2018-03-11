package cn.chenjw.codedemo.guava.evenbus;

public class EventMessage {
	private String message;
	public EventMessage(String msg){
		this.message = msg;
		System.out.println("event message is :"+message);
	}
	public String getMessage() {
		return message;
	}

}
