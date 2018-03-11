package cn.chenjw.codedemo.serurity;
public class Test {
public static void main(String[] args) throws Exception {
	 RSAUtils.generatorKeyPair();  
     String source = "���ǳ���Գ��";  
     System.out.println("����ǰ�����ݣ�\r\n" + source);  
     System.out.println("--------------------------��Կ���ܣ�˽Կ����------------------------------");  
     // ��Կ����  
     String target = RSAUtils.encryptionByPublicKey(source);  
     // ˽Կ����  
     RSAUtils.decryptionByPrivateKey(target);  
       
     System.out.println("--------------------------˽Կ���ܲ���ǩ������Կ��֤ǩ�����ҽ���------------------------------");  
     // ˽Կ����  
     target = RSAUtils.encryptionByPrivateKey(source);  
     // ǩ��  
     String sign = RSAUtils.signByPrivateKey(target);  
     // ��֤ǩ��  
     RSAUtils.verifyByPublicKey(target, sign);  
     // ��Կ����  
     RSAUtils.decryptionByPublicKey(target);  
}
}
