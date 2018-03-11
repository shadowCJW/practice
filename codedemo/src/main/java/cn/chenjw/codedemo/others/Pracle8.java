package cn.chenjw.codedemo.others;

public class Pracle8 {
	
	public Wrapping wrapping(int x){
		return new Wrapping(x){
			public int value(){
				return super.value()*47;
			}
		};
	}
	public static void main(String[] args) {
		Pracle8 p = new Pracle8();
		Wrapping w = p.wrapping(10);
	}

}
class Wrapping{
	private int i;
	public Wrapping(int x){
		i =x;
	}
	public int value(){
		return i;
	}
}
