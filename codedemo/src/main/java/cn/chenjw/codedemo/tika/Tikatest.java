package cn.chenjw.codedemo.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;


public class Tikatest {
	public static void main(String[] args) {
		
		t1();
		//t2();
		System.out.println(t3()); ;
	}

	private static int t3() {
		int sum = 0;
		for(int i=0;i<10;i++){
			sum+=i;
			if(i==5){
				return sum;
			}
		}
		return sum;
	}

	//文件内容
	private static void t2() {
		File file = new File("D:\\A\\qww.txt");
			try {
				InputStream is = new FileInputStream(file);
				BodyContentHandler handler = new BodyContentHandler();
				Metadata medata = new Metadata();
				FileInputStream fis = new FileInputStream(new File("D:\\A\\qww.txt"));
				ParseContext pcontext = new ParseContext();
				
				TXTParser tp = new TXTParser();
				tp.parse(fis, handler, medata);
				System.out.println("contents of document ="+handler.toString());
				System.out.println("metadata===");
				String[] metaNames = medata.names();
				for(String s : metaNames){
					System.out.println("name++=="+s+"："+medata.get(s));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TikaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
//文件属性
	private static void t1() {
		Tika tk = new Tika();
		File file = new File("D:\\A\\test2.txt");
		File file2 = new File("D:\\A\\test.png");
		File file3 = new File("D:\\A\\0529.csv");
		try {
			System.out.println(tk.detect(file)+"==");
			System.out.println(tk.detect(file2)+"=2=");
			System.out.println(tk.detect(file3)+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\1621.xls"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\AServlet.java"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\t2001.txt"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\qww.doc"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\Acce.xml"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\123456.txt"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\094914530271.jpg"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\20140224031232880.zip"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\aaa.rar"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\1124.xls"))+"=3=");
			System.out.println(tk.detect(new File("D:\\A\\1124.xlsx"))+"=3=");
			System.out.println(MediaType.APPLICATION_ZIP);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
