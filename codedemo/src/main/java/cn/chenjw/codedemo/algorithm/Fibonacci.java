package cn.chenjw.codedemo.algorithm;

public class Fibonacci {
	public static int getAcc(int i){
		if (i <=2){
			return 1;
		}else{
			return getAcc(i-1)+getAcc(i-2);
		}
	}
	public static void main(String[] args) {
		System.out.println(getAcc(5));//1.1.2.3.5
	}
}
