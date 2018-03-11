package cn.chenjw.codedemo.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strquan2ban {

	public static void main(String[] args) {
		System.out.println(replaceDBC2SBC("ab１２３ｗ我们３jk　jkdfdf４４４eeee"));

	}
	public static String replaceDBC2SBC(String input) {
	    Pattern pattern = Pattern.compile("[\u3000\uff01-\uff5f]{1}");

	    Matcher m = pattern.matcher(input);
	    StringBuffer s = new StringBuffer();
	    while (m.find()) {
	        char c = m.group(0).charAt(0);
	        char replacedChar = c == '　' ? ' ' : (char) (c - 0xfee0);
	        m.appendReplacement(s, String.valueOf(replacedChar));
	    }

	    m.appendTail(s);

	    return s.toString();
	}

}
