package cn.chenjw.codedemo.serurity.symmetric;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import cn.chenjw.codedemo.utils.Base64;


/**
 * DES安全编码组件
 *
 * <pre>
 * 支持 DES、DESede(TripleDES,就是3DES)、AES、Blowfish、RC2、RC4(ARCFOUR)
 * DES                  key size must be equal to 56
 * DESede(TripleDES)    key size must be equal to 112 or 168
 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
 * RC2                  key size must be between 40 and 1024 bits
 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
 * 具体内容 需要关注 JDK Document http://.../docs/technotes/guides/security/SunProviders.html
 * </pre>
 *
 * @author
 * @version 1.0
 * @since 1.0
 */
public class DES4 {

	private static String Algorithm = "DESede"; // 定义 加密算法,可用DES,DESede,Blowfish

	static {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}

	// 生成密钥, 注意此步骤时间比较长
	public static byte[] getKey() throws Exception {
		final KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
		final SecretKey deskey = keygen.generateKey();
		return deskey.getEncoded();
	}

	// 对文件进行加解密

	/**
	 * 加密
	 *
	 * @param enfile
	 *            要加密的文件
	 * @param defile
	 *            加密后的文件
	 * @param key
	 *            密钥
	 * @throws Exception
	 */
	public static void encode(final String enfile, final String defile,
			final byte[] key) throws Exception {
		// 秘密（对称）密钥(SecretKey继承(key))
		// 根据给定的字节数组构造一个密钥。
		final SecretKey deskey = new SecretKeySpec(key, Algorithm);
		// 生成一个实现指定转换的 Cipher 对象。Cipher对象实际完成加解密操作
		final Cipher c = Cipher.getInstance(Algorithm);
		// 用密钥初始化此 cipher
		c.init(Cipher.ENCRYPT_MODE, deskey);

		final byte[] buffer = new byte[1024];
		final FileInputStream in = new FileInputStream(enfile);
		final OutputStream out = new FileOutputStream(defile);
		final CipherInputStream cin = new CipherInputStream(in, c);

		int i;
		while ((i = cin.read(buffer)) != -1) {
			out.write(buffer, 0, i);
		}
		out.close();
		cin.close();
	}

	// 解密
	public static void decode(final String file, final String defile,
			final byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源
		final SecureRandom sr = new SecureRandom();
		// 创建一个 DESKeySpec 对象,指定一个 DES 密钥
		// DESKeySpec ks = new DESKeySpec(key); //factroy.generateSecret(ks)
		// 生成key 时会判断类型
		final DESedeKeySpec ks = new DESedeKeySpec(key);
		// 生成指定秘密密钥算法的 SecretKeyFactory 对象。
		final SecretKeyFactory factroy = SecretKeyFactory
				.getInstance(Algorithm);
		// 根据提供的密钥规范（密钥材料）生成 SecretKey 对象,利用密钥工厂把DESKeySpec转换成一个SecretKey对象
		final SecretKey sk = factroy.generateSecret(ks);
		// 生成一个实现指定转换的 Cipher 对象。Cipher对象实际完成加解密操作
		final Cipher c = Cipher.getInstance(Algorithm);
		// 用密钥和随机源初始化此 cipher 主要是这里确定了是加密还是解密
		c.init(Cipher.DECRYPT_MODE, sk, sr);

		final byte[] buffer = new byte[1024];
		final FileInputStream in = new FileInputStream(file);
		final OutputStream out = new FileOutputStream(defile);
		// CipherOutputStream cout = new CipherOutputStream(out, c);
		// //可以读的时候加密，也可以在写的时候加密，解密同理
		final CipherInputStream cin = new CipherInputStream(in, c);// 创建CipherInputStream对象
		int i;
		while ((i = cin.read(buffer)) != -1) {
			out.write(buffer, 0, i);
		}
		out.close();
		cin.close();
	}

	/**
	 * des,对数据进行对称加密
	 * 
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] DESencrypt(final byte[] key, final byte[] data)
			throws Exception {

		final SecretKey deskey = new SecretKeySpec(key, Algorithm);
		// 生成一个实现指定转换的 Cipher 对象。Cipher对象实际完成加解密操作
		final Cipher c = Cipher.getInstance(Algorithm);
		c.init(Cipher.ENCRYPT_MODE, deskey);

		if (data == null) {
			return null;
		}
		return c.doFinal(data);
	}

	/**
	 * des对称加密
	 * 
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] DESdecerypt(final byte[] key, final byte[] data)
			throws Exception {
		final SecureRandom sr = new SecureRandom();
		final DESedeKeySpec ks = new DESedeKeySpec(key);
		final SecretKeyFactory factroy = SecretKeyFactory
				.getInstance(Algorithm);
		final SecretKey sk = factroy.generateSecret(ks);
		final Cipher c = Cipher.getInstance(Algorithm);
		c.init(Cipher.DECRYPT_MODE, sk, sr);
		if (data == null) {
			return data;
		}
		return c.doFinal(data);
	}

	public static void main(final String[] args) throws Exception {
		//test();
		test2();
	}

	private static void test2() throws Exception {
		byte[] key = "c3VuLnNlY3VyaXR5LnJzYS22".getBytes();
		String str = "你好123adg";
		byte[] encByte = DESencrypt(key, str.getBytes());
		String encStr = Base64.encode(encByte);
		String decStr = new String(DESdecerypt(key, encByte)); 
		System.out.println(encStr);
		System.out.println(decStr);
		
	}

	private static void test() throws Exception {
		final byte[] key = "c3VuLnNlY3VyaXR5LnJzYS22".getBytes();
		System.out.println(key.length);
		// 字节数必须是24的整数倍
		// 文件加密
		encode("D:/web.xml", "D:/b.xml", key);

		// 文件解密
		decode("D:/b.xml", "D:/c.xml", key);
		
	}
}
