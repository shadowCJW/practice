package cn.chenjw.codedemo.serurity;

import java.io.File;

public class CreateKeystore {
	public static void main(String[] args) {
		
		String keyStoreAlias = "testkey";//别名 
		String keystoreSavePath = "E:/A/test.keystore";//密钥库存储位置 
		String keyStorePassword = "123456a";// 密钥库口令(获取keystore信息所需的密码)  
		String dname = "CN=cjw.zhangjie123.net.cn,OU=服务运营部,O=科技有限公司,L=gz,ST=guangdong,C=cn";
		String keyPassword = "asdfgh";// 指定别名条目的密码(私钥的密码)  
		createKeystore(keyStoreAlias, keystoreSavePath, keyStorePassword, dname, keyPassword); 
	}

	private static void createKeystore(String keyStoreAlias,
			String keystoreSavePath, String keyStorePassword, String dname,
			String keyPassword) {
		File file = new File(keystoreSavePath);  
        if (file.isFile() && file.exists()) {  
            file.delete();//删除原有密钥库文件  
        }  
        StringBuffer command = new StringBuffer();  
        command.append("cmd /k start ")// cmd Shell命令  
        .append(" keytool")  
        .append(" -genkey")// -genkey 表示生成密钥  
        .append(" -keyalg RSA")// -keyalg 指定密钥的算法,诸如 RSA、DSA（默认值）  
        .append(" -keysize 1024")// -keysize 指定密钥长度  
        .append(" -validity 365")// -validity指定证书有效期(单位：天)  
        .append(" -alias "+keyStoreAlias)// -alias 指定别名  
        .append(" -keystore \""+keystoreSavePath+"\"")// -keystore指定存储位置  
        .append(" -storepass "+keyStorePassword)// -storepass 密钥库口令(获取keystore信息所需的密码)  
        .append(" -dname \""+dname+"\"")// -dname CN=(名字与姓氏), OU=(组织单位名称), O=(组织名称), L=(城市或区域名称), ST=(省/市/自治区名称), C=(单位的两字母国家代码)
        .append(" -keypass "+keyPassword)// -keypass 指定别名条目的密码(私钥的密码)  
        .append(" -v");// -v 显示密钥库中的证书详细信息  
        
        System.out.println(command);  
        try {  
            Runtime.getRuntime().exec(command.toString());  
            System.out.println("密钥库创建成功");  
        } catch (Exception e) {  
            System.out.println("密钥库创建失败");  
        }  
	}
	 
}
