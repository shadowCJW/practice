package cn.chenjw.codedemo.datee;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {
	public static void main(String[] args) {
		Date d = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前时刻："+sdf1.format(d));
		Calendar ca = Calendar.getInstance();
		System.out.println("年份："+ca.get(Calendar.YEAR));
		
		ca.setTime(d);
		ca.roll(Calendar.DATE, 1);
		System.out.println("后一天日期是："+ca.getTime());
	}
}
