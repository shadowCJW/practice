package cn.chenjw.codedemo.vm;

public class JavaVMStackSOF {
	private int stackLength = 1;
	
	public void stackeLeak(){
		stackLength++;
		stackeLeak();
	}
	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try{
			
			oom.stackeLeak();
		}catch(Throwable e){
			System.out.println("stack length :"+ oom.stackLength);
			System.out.println();
			throw e;
		}
	}
}
