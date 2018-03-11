package cn.chenjw.codedemo.beanshell;

import bsh.EvalError;
import bsh.Interpreter;

public class BeanSheelTest {

	/**
	 * 在java代码里面运行脚本
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testZJHM();
		testBigger10( 12);
	}

	private static void testBigger10(int i) {
		String shellString = "boolean checkBig(int num){"
				+"return num - 10>0? true : false;"
				+"}"
				+"res = checkBig(testnum);	";
		Interpreter in = new Interpreter();
		try {
			in.set("testnum", i);
			in.eval(shellString);
			
			System.out.println(in.get("res"));
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testZJHM() {
		String sheelString = "import java.text.ParseException;"
				+ "import java.text.SimpleDateFormat;"
				+ "import java.util.Calendar;"
				+ "import java.util.Date;"
				+ "import java.util.regex.Matcher;"
				+ "import java.util.regex.Pattern;"
				+ "boolean checkIsNumber(String param) {"
				+ "final Pattern pat = Pattern.compile(\"^[0-9]+$\");"
				+ "final Matcher mat = pat.matcher(param);"
				+

				"return mat.find();"
				+

				"}"
				+ "boolean checkBirthday(String birthday) {"
				+ "try {SimpleDateFormat sdf = new SimpleDateFormat(\"yyyyMMdd\");"
				+ "Date now = new Date();"
				+ "Date bir = sdf.parse(birthday);"
				+ "Calendar c = Calendar.getInstance();"
				+ "c.setTime(bir);"
				+ "if(bir.after(now) || c.get(Calendar.YEAR)<1900){"
				+ "return false;}"
				+ "if(!birthday.equals(sdf.format(bir))){"
				+ "return false;}"
				+

				"} catch (ParseException e) {"
				+ "e.printStackTrace();"
				+ "return false;}"
				+ "return true;}"
				+

				"boolean checkLastNum(String param) {"
				+ "int[] power= { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };"
				+ "int[] parity = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };"
				+ "String[] paramArr = param.split(\"\");"
				+ "int sum = 0;"
				+

				"for(int i=0;i<17;i++){"
				+ "sum += power[i] * Integer.valueOf(paramArr[i]);"
				+ "}"
				+ "if(parity[sum%11] == Integer.valueOf(paramArr[17])){"
				+ "return true;"
				+ "}"
				+ "return false;"
				+ "}"
				+

				"boolean checkProvince(String province) {"
				+ "String proStr = \"11,12,13,14,15,21,22,23 ,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54 ,61,62,63,64,65,71,81,82,91\";"
				+ "return proStr.indexOf(province) == -1 ? false : true;" +

				"}" + "boolean check(String param) {" + "param = param.trim();"
				+ "boolean flag = true;" + "int paramLen = param.length();" +

				"if(param==null || param ==\"\" || (paramLen!=15 && paramLen!=18)){"
				+ "return false;" + "}" + "if(paramLen==15){" +

				"if(!checkIsNumber(param)){" + "return false;}"
				+ "if(!checkProvince(param.substring(0, 2))){"
				+ "return false;}" +

				"if(!checkBirthday(\"19\"+param.substring(6, 12))){"
				+ "return false;}" + "}else{" +

				"if(!checkIsNumber(param.substring(0, paramLen-1))){"
				+ "return false;}"
				+ "if(!checkProvince(param.substring(0, 2))){"
				+ "return false;}" + "if(!checkLastNum(param)){"
				+ "return false;}" +

				"if(!checkBirthday(param.substring(6, 14))){"
				+ "return false;}}return flag;}"
				+ "boolean result = check(checkParam);";
		Interpreter in = new Interpreter();
		String paramVal = "445381199306234516";//设置参数值
		String paramKey = "checkParam";//s设置参数名
		String resultKey = "result"; //对应shell脚本里面的返回结果
		try {
			in.set(paramKey, paramVal);
			in.eval(sheelString);
			Object object = in.get(resultKey);
			System.out.println(object);
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
