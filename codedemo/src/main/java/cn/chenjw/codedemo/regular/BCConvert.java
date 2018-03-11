package cn.chenjw.codedemo.regular;

public class BCConvert {
	/**
	 * ASCII表中可见字符从!开始，偏移位值为33(Decimal)
	 */
	static final char  DBC_CHAR_START = 33;// 半角!
	/**
	 * ASCII表中可见字符到~结束，偏移位值为126(Decimal)
	 */
	static final char DBC_CHAR_END   =  126; // 半角~
	/**
	 * 全角对应于ASCII表的可见字符从！开始，偏移值为65281
	 */
	static final char SBC_CHAR_START = 65281; // 全角！

	/**
	 * 全角对应于ASCII表的可见字符到～结束，偏移值为65374
	 */
	static final char SBC_CHAR_END = 65374; // 全角～
	/**
	 * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
	 */
	static final int CONVERT_STEP = 65248; // 全角半角转换间隔

	/**
	 * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
	 */
	static final char SBC_SPACE = 12288; // 全角空格 12288
	/**
	 * 半角空格的值，在ASCII中为32(Decimal)
	 */
	static final char DBC_SPACE = ' '; // 半角空格
	/**
	半角字符->全角字符转换  
	 * 只处理空格，!到˜之间的字符，忽略其他
	 * </PRE>
	 * @param str
	 * @return
	 */
	public static String bj2qj(String str){
		if (str == null){
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		char[] c = str.toCharArray();
		for(int i = 0 ,len = c.length;i< len ;i++){
			if(c[i]==DBC_SPACE){
				 // 如果是半角空格，直接用全角空格替代
				sb.append(SBC_SPACE);
			}else if ((c[i] >= DBC_CHAR_START) && (c[i] <= DBC_CHAR_END)){
				// 字符是!到~之间的可见字符
				sb.append((char) (c[i]+CONVERT_STEP));
			}else{
				// 不对空格以及ascii表中其他可见字符之外的字符做任何处理
				sb.append(c[i]);
			}
		}
		return sb.toString();
	}
	/**
	 * 全角字符->半角字符转换  
	 * 只处理全角的空格，全角！到全角～之间的字符，忽略其他
	 * @param str
	 * @return
	 */
	public static String qj2bj(String str){
		if (str == null){
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		char[] c = str.toCharArray();
		for(int i = 0 ,len = c.length;i< len ;i++){
			if (c[i] >= SBC_CHAR_START && c[i] <= SBC_CHAR_END) { // 如果位于全角！到全角～区间内
				sb.append((char) (c[i] - CONVERT_STEP));
			} else if (c[i] == SBC_SPACE) { // 如果是全角空格
				//sb.append(DBC_SPACE);
			} else { // 不处理全角空格，全角！到全角～区间外的字符
					sb.append(c[i]);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String s = "nihaoｈｋ　｜　　　ｎｉｈｅｈｅ　，。　７８　　７　";
		s=BCConvert.qj2bj(s);
		System.out.println(s);
	}
}
