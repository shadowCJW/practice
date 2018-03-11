package cn.chenjw.codedemo.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.AutoDetectReader;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class ContentCheck {
	public static void main(String[] args) {
		//t1();
		t2();
	}


	private static void t2() {
		File file = new File("D:/A/tika/1222.xml");//前面会有空格出现
		//File file = new File("D:/A/tika/1222.txt");
		//File file = new File("D:/A/tika/1222.xls");//sheet的名字和内容分别列出来了，
		//File file = new File("D:/A/tika/1222.doc");//不行
		ContentHandler handler = null;
		Parser parser = new AutoDetectParser();
		
		try {
			InputStream is = new FileInputStream(file);
			Metadata meta = new Metadata();
			handler = new BodyContentHandler();
			parser.parse(is, handler, meta, new ParseContext());
			System.out.println(handler.toString());
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

	private static void t1() {
		File file = new File("D:/A/tika/1222.xml");
		Tika tk = new Tika();
		try {
			String filetype = tk.detect(file);//文件类型
			String content = tk.parseToString(file); //文件内容
			
			AutoDetectReader adr = new AutoDetectReader(new FileInputStream(file));
			String charset = adr.getCharset().name();//文件编码格式
			
			LanguageIdentifier iden = new LanguageIdentifier("123 abc");
			String lanuage = iden.getLanguage();
			
			System.out.println(filetype + "->"+content+ "->"+charset+ "->"+lanuage);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
	}
}
