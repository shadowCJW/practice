package cn.chenjw.codedemo.serurity;

import java.security.MessageDigest;

public class MD5Utils {
	/**
	 * 得到32位md5加密的值
	 * @param inStr 返回32位md5加密字符串(16位加密取substring(8,24)) 或者自己定义截取位数
	 * @return
	 */
	public static String getMD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
	// 可逆的加密算法     
	 public static String KL(String inStr) {     
	  // String s = new String(inStr);     
	  char[] a = inStr.toCharArray();     
	  for (int i = 0; i < a.length; i++) {     
	   a[i] = (char) (a[i] ^ 't');     
	  }     
	  String s = new String(a);     
	  return s;     
	 }     
	    
	 // 加密后解密     
	 public static String JM(String inStr) {     
	  char[] a = inStr.toCharArray();     
	  for (int i = 0; i < a.length; i++) {     
	   a[i] = (char) (a[i] ^ 't');     
	  }     
	  String k = new String(a);     
	  return k;     
	 }     
    /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
    
    public static void main(String[] args) {
    	/**
    	 * 有问题的 md5不可逆的，这里解密的是md5加密后的密码，结果还是原来的md5加密的密码
    	 */
    	String s = new String("sys");     
    	  System.out.println("原始：" + s);     
    	  System.out.println("MD5后：" + getMD5(s));     
    	  System.out.println("MD5后再加密：" + KL(getMD5(s)));     
    	  System.out.println("解密为MD5后的：" + JM(KL(getMD5(s))));       
	}
}
