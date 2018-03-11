package cn.chenjw.codedemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class Chinese2PinyinUtil {
	/***
	 * 将汉字转成拼音(取首字母或全拼)
	 * @param hanzi
	 * @param full 是否全拼
	 * @return
	 */
	public static String convert2Pinyin(String content , boolean full){
		 /***
		 * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言 
		 * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体 
		 * ^[\u4E00-\u9FA5]+$ 匹配简体
		 */
		String regExp = "^[\u4E00-\u9FFF]+$";
		StringBuffer sb = new StringBuffer();
		if(Strings.isNullOrEmpty(content.trim())){
			return "";
		}
		String pinyin = "";
		for(int i =0,len = content.length();i<len;i++){
			char ch = content.charAt(i);
			if(match(ch,regExp)){
				pinyin = converSingleHanzi2Pinyin(ch);
				if(full){
					sb.append(pinyin);
				}else{
					sb.append(pinyin.charAt(0));
				}
			}else{
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	private static String converSingleHanzi2Pinyin(char ch) {
		HanyuPinyinOutputFormat hpof = new HanyuPinyinOutputFormat();
		hpof.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String[] res;
		StringBuilder sb = new StringBuilder();
		try {
			res = PinyinHelper.toHanyuPinyinStringArray(ch,hpof);
			sb.append(res[0]);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return sb.toString();
	}

	private static boolean match(char ch, String regExp) {
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(String.valueOf(ch));
		
		return m.find();
	}
	
	public static void main(String[] args) {
		System.out.println(convert2Pinyin("我是中国人123abc",true));
	}
}
