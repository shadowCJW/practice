package cn.chenjw.codedemo.file.xml.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.common.io.Files;

/**
 * xml简单使用
 * @author chenjw
 * 2017年12月14日
 */
public class XMLbase {

	/**
	 * 根据xml路径获取val
	 * @param xml
	 * @param path
	 * @return
	 */
	public static String getValByXPath(String xml,String path){
		XPath xpath = XPathFactory.newInstance().newXPath();
		DocumentBuilderFactory buildFactory = DocumentBuilderFactory.newInstance();
		StringReader sr = new StringReader(xml);
		InputSource is = new InputSource(sr);
		
		try {
			DocumentBuilder documentBuilder = buildFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(is);
			
			String valueNode =   (String) xpath.evaluate(path, doc, XPathConstants.STRING);
			return valueNode;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Test
	public void testGetVal(){
		try {
			FileInputStream is = new FileInputStream("src/main/resources/sjcx.txt");
			String val = getValByXPath(Files.toString(new File("src/main/resources/sjcx.txt"),Charset.forName("UTF-8")), "/RBSPMessage/ServiceID");
			System.out.println(val);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
