package cn.chenjw.codedemo.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;

import cn.chenjw.codedemo.bean.User;
/**
 * 下面csv用的是这个包
 * org.marketcetera.fork
 * @author Administrator
 *
 */
public class CsvDemo {/*

	public static void main(String[] args) {
		try {
			FileOutputStream out = new FileOutputStream("D:/0529.csv");
			String[] head = new String[]{"号码","名字","年龄"};
			List<User> list = new ArrayList<User>();
			User u = new User();
			u.setAge(1);u.setId("a");u.setName("xiaoming");
			list.add(u);
			
			 final String[] string = new String[] {
		               "用户标识符", "单位名称", "单位代码", "用户名", "操作时间", "终端标识", "操作类型", "操作结果", "失败原因代码", "操作名称", "操作模块"
		        };
			 final CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(out, "GB2312"));
			 if (list.size() > 0) {
		            csvPrinter.println(head);
		            for (int i =0,len = list.size();i<len;i++) {
		                csvPrinter.println(list.get(i).toValueArray());
		            }
		        } else {
		            csvPrinter.println("你查询到的数据为空！！！");
		        }

		        out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

*/}
