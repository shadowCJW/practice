package cn.chenjw.codedemo.serurity.asymmetry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang3.text.StrBuilder;

import cn.chenjw.codedemo.utils.Base64;


public class RSAUtils {
	/**
	 * 字节数据转字符串专用集合
	 */
	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 随机生成密钥对
	 */
	public static void genKeyPair(final String filePath) {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(1024, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		final KeyPair keyPair = keyPairGen.generateKeyPair();
		// 得到私钥
		final RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 得到公钥
		final RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		try {
			// 得到公钥字符串
			final String publicKeyString = Base64
					.encode(publicKey.getEncoded());
			// 得到私钥字符串
			final String privateKeyString = Base64.encode(privateKey
					.getEncoded());
			// 将密钥对写入到文件
			final FileWriter pubfw = new FileWriter(filePath
					+ "/publicKey.keystore");
			final FileWriter prifw = new FileWriter(filePath
					+ "/privateKey.keystore");
			final BufferedWriter pubbw = new BufferedWriter(pubfw);
			final BufferedWriter pribw = new BufferedWriter(prifw);
			pubbw.write(publicKeyString);
			pribw.write(privateKeyString);
			pubbw.flush();
			pubbw.close();
			pubfw.close();
			pribw.flush();
			pribw.close();
			prifw.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从文件中输入流中加载公钥
	 *
	 * @param in
	 *            公钥输入流
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static String loadPublicKeyByFile(final String path)
			throws Exception {
		try {
			final BufferedReader br = new BufferedReader(new FileReader(path
					+ "/publicKey.keystore"));
			String readLine = null;
			final StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (final IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (final NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 * 从字符串中加载公钥
	 *
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static RSAPublicKey loadPublicKeyByStr(final String publicKeyStr)
			throws Exception {
		try {
			final byte[] buffer = Base64.decode(publicKeyStr);
			final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (final NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (final InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (final NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 从文件中加载私钥
	 *
	 * @param keyFileName
	 *            私钥文件名
	 * @return 是否成功
	 * @throws Exception
	 */
	public static String loadPrivateKeyByFile(final String path)
			throws Exception {
		try {
			final BufferedReader br = new BufferedReader(new FileReader(path
					+ "/privateKey.keystore"));
			String readLine = null;
			final StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (final IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (final NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}

	public static RSAPrivateKey loadPrivateKeyByStr(final String privateKeyStr)
			throws Exception {
		try {
			final byte[] buffer = Base64.decode(privateKeyStr);
			final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (final NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (final InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (final NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	/**
	 * 公钥加密过程
	 *
	 * @param publicKey
	 *            公钥
	 * @param plainTextData
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static byte[] encrypt(final RSAPublicKey publicKey,
			final byte[] plainTextData) throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			final byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (final NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (final NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (final InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (final IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (final BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	/**
	 * 私钥加密过程
	 *
	 * @param privateKey
	 *            私钥
	 * @param plainTextData
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static byte[] encrypt(final RSAPrivateKey privateKey,
			final byte[] plainTextData) throws Exception {
		if (privateKey == null) {
			throw new Exception("加密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			final byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (final NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (final NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (final InvalidKeyException e) {
			throw new Exception("加密私钥非法,请检查");
		} catch (final IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (final BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	/**
	 * 私钥解密过程
	 *
	 * @param privateKey
	 *            私钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public static byte[] decrypt(final RSAPrivateKey privateKey,
			final byte[] cipherData) throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			final byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (final NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (final NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (final InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (final IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (final BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	/**
	 * 公钥解密过程
	 *
	 * @param publicKey
	 *            公钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public static byte[] decrypt(final RSAPublicKey publicKey,
			final byte[] cipherData) throws Exception {
		if (publicKey == null) {
			throw new Exception("解密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			final byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (final NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (final NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (final InvalidKeyException e) {
			throw new Exception("解密公钥非法,请检查");
		} catch (final IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (final BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	/**
	 * 字节数据转十六进制字符串
	 *
	 * @param data
	 *            输入数据
	 * @return 十六进制内容
	 */
	public static String byteArrayToString(final byte[] data) {
		final StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			// 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
			stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
			// 取出字节的低四位 作为索引得到相应的十六进制标识符
			stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
			if (i < (data.length - 1)) {
				stringBuilder.append(' ');
			}
		}
		return stringBuilder.toString();
	}

	// 以上是网页教程：
	// src/test/resources/keystore/keyStore.jks

	/**
	 * 得到公钥
	 *
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKey(String insType, String trustStore,
			String trustStorePassword, String trustStoreAlias) throws Exception {
		KeyStore ks;
		FileInputStream fin = null;
		RSAPublicKey rsapublicKey = null;
		try {

			ks = KeyStore.getInstance(insType);
			fin = new FileInputStream(trustStore);
			ks.load(fin, trustStorePassword.toCharArray());

			// 读取公钥
			final java.security.cert.Certificate cert = ks
					.getCertificate(trustStoreAlias);
			if (cert != null) {
				rsapublicKey = (RSAPublicKey) cert.getPublicKey();
			}
		} catch (final Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (fin != null) {

				fin.close();
			}
		}
		return rsapublicKey;
	}

	/**
	 * 得到私钥
	 *
	 * @return
	 * @throws Exception
	 */
	public static RSAPrivateKey getPrivateKey(String keyStoreType, String keyStore,
			String keyStorePassword, String keyStoreAlias, String keyPassword)
			throws Exception {
		KeyStore ks;
		FileInputStream fin = null;
		RSAPrivateKey rsaprivateKey = null;
		try {
			ks = KeyStore.getInstance(keyStoreType);
			fin = new FileInputStream(keyStore);
			ks.load(fin, keyStorePassword.toCharArray());

			rsaprivateKey = (RSAPrivateKey) ks.getKey(keyStoreAlias,
					keyPassword.toCharArray());
		} catch (final Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (fin != null) {

				fin.close();
			}
		}
		return rsaprivateKey;
	}
public static void main(String[] args) {
	test1();
}

private static void test1() {
	try {//cjw.cn.com/src/test/resources/key/keyStore.jks
		//PrivateKey pk =  getPrivateKey("JKS", RSAUtils.class.getClassLoader().getResource("key/keyStore.jks").getPath(), "123456", "1", "123456");
		//System.out.println(pk.toString());
		 // 获取classpath路径
        System.out.println("classpath路径: " + RSAUtils.class.getClassLoader().getResource("key/keyStore.jks").getPath());
        // 获取当前类的加载路径
      //  System.out.println("当前类加载路径: " + RSAUtils.class.getResource("").getPath());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
