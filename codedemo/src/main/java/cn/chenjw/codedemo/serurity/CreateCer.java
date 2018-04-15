package cn.chenjw.codedemo.serurity;

import java.io.File;

public class CreateCer {
	public static void main(String[] args) {
		String keyStorePath = "E:/A/test.keystore";// 指定密钥库文件路径
		String keyStoreAlias = "testkey";//密钥库别名 
		String keyStorePassword = "123456a";//密钥库的密码  
	    String cerSavePath = "E:/A/test0416.cer";//安全证书文件保存路径  
	    createCer(keyStorePath, keyStoreAlias, keyStorePassword, cerSavePath);
	}

	private static void createCer(String keyStorePath, String keyStoreAlias,
			String keyStorePassword, String cerSavePath) {
		 if(!new File(keyStorePath).exists()){  
	            System.err.println("密钥库文件不存在，无法生成安全证书");  
	            return;  
	        }  
	        File file = new File(cerSavePath);  
	        if (file.isFile() && file.exists()) {  
	            file.delete();//删除原有安全证书  
	        }  
	        StringBuffer command = new StringBuffer();  
	        command.append("cmd /k start")// cmd Shell命令  
	        .append(" keytool")  
	        .append(" -exportcert") // 指定为导出操作  
	        .append(" -keystore "+"\""+keyStorePath+"\"")// 指定密钥库文件路径  
	        .append(" -alias "+keyStoreAlias) // 指定密钥库别名  
	        .append(" -storepass "+keyStorePassword)// 指定密钥库的密码  
	        .append(" -file "+"\""+cerSavePath+"\""); //指安全证书文件保存路径  
	  
	        System.out.println(command);  
	        try {  
	            Runtime.getRuntime().exec(command.toString());  
	            System.out.println("成功导出证书");  
	        } catch (Exception e) {  
	            System.err.println("导出证书失败");  
	        }  
	}
}
