package cn.chenjw.codedemo.regular;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtils {

	/**
	 * 校验15或者18位身份证号码
	 * @param idCard
	 * @return
	 */
	public static boolean checkIdCard(String idCard){

		idCard = idCard.trim();
		boolean flag = true;
		int idCardLen = idCard.length();
		
		if(idCard==null || idCard =="" || (idCardLen!=15 && idCardLen!=18)){
			return false;	
		}
		if(idCardLen==15){
			//
			//判断位数
			if(!checkIsNumber(idCard)){
				return false;
			}
			//判断省份
			if(!checkProvince(idCard.substring(0, 2))){
				return false;
			}
			
			//校验出生日期
			if(!checkBirthday("19"+idCard.substring(6, 12))){
				return false;
			}
		}else{
			
			//判断位数
			if(!checkIsNumber(idCard.substring(0, idCardLen-1))){
				return false;
			}
			//判断省份
			if(!checkProvince(idCard.substring(0, 2))){
				return false;
			}
			//第18位校验正确
			if(!checkLastNum(idCard)){
				return false;
			}
			
			//校验出生日期
			if(!checkBirthday(idCard.substring(6, 14))){
				return false;
			}
		}
		return flag;
	
	}

	private static boolean checkBirthday(String birthday) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date now = new Date();
			Date bir = sdf.parse(birthday);
			Calendar c = Calendar.getInstance();
			c.setTime(bir);
			if(bir.after(now) || c.get(Calendar.YEAR)<1900){//大于当前，小于1900
				return false;
			}
			if(!birthday.equals(sdf.format(bir))){
				return false;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	private static boolean checkLastNum(String param) {
		int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		int[] parity = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
		String[] paramArr = param.split("");
		int sum = 0;
		
		for(int i=0;i<17;i++){
			sum += power[i] * Integer.valueOf(paramArr[i]);
		}
		if(parity[sum%11] == Integer.valueOf(paramArr[17])){
			return true;
		}
		return false;
	}

	private static boolean checkProvince(String province) {
		String proStr = "11,12,13,14,15,21,22,23 ,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54 ,61,62,63,64,65,71,81,82,91";
		return proStr.indexOf(province) == -1 ? false : true;
		
	}

	private static boolean checkIsNumber(String param) {
		final Pattern pat = Pattern.compile("^[0-9]+$");
	    final Matcher mat = pat.matcher(param);
		return mat.find();
		
	}
}
