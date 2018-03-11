package cn.chenjw.codedemo.others;

public class Jinzhi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String binaryString = Integer.toBinaryString(5);
		System.out.println(binaryString);
		String hexString = Integer.toHexString(12);
		String octalString = Integer.toOctalString(9);
		System.out.println(hexString);
		System.out.println(octalString);
		System.out.println(101 | 111);
		System.out.println(101 & 111);
		System.out.println(101 ^ 111);
		System.out.println(~101);
	}
 }
