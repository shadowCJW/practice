package cn.chenjw.codedemo.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class COMCSV {

	public static void main(String[] args) {
		write();
		read();
	}

	private static void read() {
		final String FILE_NAME = "E:/A/csv/student.csv";
		BufferedReader br = null;
		CSVParser parser = null;
		List<String> list = new ArrayList<String>();
		String[] header = {"ID","Name","Gender","Major"};
		CSVFormat format = CSVFormat.DEFAULT.withHeader(header);
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FILE_NAME))));
			parser = new CSVParser(br, format);
			List<CSVRecord> records = parser.getRecords();
			for(int i=0;i<records.size();i++){
				CSVRecord record = records.get(i);
				System.out.println(record.toMap());
			//	System.out.println(record.toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	private static void write() {

		// TODO Auto-generated method stub
		final String[] FILE_HEADER = {"ID","Name","Gender","Major"};
        final String FILE_NAME = "E:/A/csv/student.csv";
        BufferedWriter bw = null;

        // 这里显式地配置一下CSV文件的Header，然后设置跳过Header（要不然读的时候会把头也当成一条记录）
        //CSVFormat format = CSVFormat.DEFAULT.withHeader(FILE_HEADER).withSkipHeaderRecord();
        CSVFormat format = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        CSVPrinter printer = null;
	try {
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(FILE_NAME))));
		 printer = new CSVPrinter(bw, format);
		 String[] aa = {"1","2","3","4"};
		/*
		for(String a :aa){
			printer.print(a);
		}
		printer.println();*/
		List<String> list = new ArrayList<String>();
		list.add("1");list.add("xiam");list.add("xxx");list.add("vvv");
		String[] arr =  list.toArray(new String[0]);
		printer.printRecord(aa);
		printer.printRecord(arr);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			bw.flush();
			bw.close();
			printer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

        
        
        
        
	
		
	}

}
