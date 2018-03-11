package cn.chenjw.codedemo.guava;
import static com.google.common.base.Preconditions.*;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
public class CheckBefore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		checkArgument(true); //传进的参数是false的时候或抛异常IllegalArgumentException 参数不合法
		checkArgument(true, "传进的参数不合法");
		checkNotNull("", "参数不能为null");//传进的数为null会抛异常
		
		boolean re = Strings.isNullOrEmpty(""); //判断是否为空或null
		System.out.println(re);
		String s = "abc";
		String n2e = Strings.nullToEmpty(null);
		System.out.println(n2e);
		String e2n = Strings.emptyToNull("");
		System.out.println(e2n);
		System.out.println(Strings.emptyToNull(s));
		System.out.println(Strings.nullToEmpty(s));
		
		//===帮助你执行null敏感的equals判断，从而避免抛出NullPointerException。
		System.out.println(Objects.equal(null, 123));//return false
		System.out.println(Objects.equal(null, null));//return true;
		
		//==hashcode
		System.out.println(Objects.hashCode("apple","banana"));
		
		//tostring
		
		
	}

}
