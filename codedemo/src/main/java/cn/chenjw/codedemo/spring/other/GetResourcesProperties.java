package cn.chenjw.codedemo.spring.other;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetResourcesProperties {

	public static void main(String[] args) {
		type1();
		System.out.println(GetResourcesProperties.class.getName());
	}

	private static void type1() {
		InputStream is = GetResourcesProperties.class.getResourceAsStream("/test.properties");
		String path = GetResourcesProperties.class.getResource("/").getPath();
		System.out.println(path);
		Properties properties = new Properties();
		try {
			properties.load(is);
			String name = properties.getProperty("name");
			System.out.println(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}
