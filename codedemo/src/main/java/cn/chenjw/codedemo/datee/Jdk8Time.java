package cn.chenjw.codedemo.datee;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

public class Jdk8Time {

	public static void main(String[] args) {
		//simpledateformat 线程不安全
		/*FastDateFormat dataFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
		FastDateFormat dataFormat1 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		String s = dataFormat.format(new Date());
		String s2 = dataFormat1.format(new Date());
		System.out.println(s);System.out.println(s2);*/
		
		//查看操作系统类型
		//String ostype = System.getProperty("os.name");
		//System.out.println(ostype);
		
		//jdk8时间使用类型
		j8time();
	}
	/**
	 * 日期localdate
	 * 时间类：locatedatetime
	 * 时间戳：instant
	 * 时间持续计算：duration
	 * 时区 zoneld
	 * 日期间隔 period
	 */
	/**
	 * 
	 */
	private static void j8time() {
		Instant ins = Instant.now();
		System.out.println("当前时间:"+ins);
		System.out.println("-----");
		LocalTime lt = LocalTime.now();
		System.out.println("localtime= "+lt.toString());
		System.out.println("-----");
		LocalTime specific = LocalTime.of(9, 12, 15, 10);
		System.out.println("secific of date = "+specific.toString());
		
		// Getting date from the base date i.e 01/01/1970
		LocalTime specificSecondTime = LocalTime.ofSecondOfDay(60*110+11);
		System.out.println("10000th second time= " + specificSecondTime);
		
		//今天日期
		LocalDate localdate = LocalDate.now();
		System.out.println("today= "+localdate);
		
		//create 2017月的第一天
		LocalDate first2017 = LocalDate.of(2017, Month.JANUARY, 1);
		System.out.println("firstday of 2017="+first2017);
		
		LocalDate hundredDay = LocalDate.ofYearDay(2017, 200);
		System.out.println("2017 de 200th is = "+hundredDay);
		
		//plus
		System.out.println("昨天日期 ："+localdate.minusDays(1));
		System.out.println("明天日期："+ localdate.plusDays(1));
		System.out.println("上一月的今天："+localdate.minusMonths(1));
		System.out.println("下一月的今天："+localdate.plusMonths(1));
		
		// Temporal adjusters for adjusting the dates
		System.out.println("本月第一天："+ localdate.with(TemporalAdjusters.firstDayOfMonth()));
		System.out.println("本月最后一天："+ localdate.with(TemporalAdjusters.lastDayOfMonth()));
		System.out.println("本年最后一天："+ localdate.with(TemporalAdjusters.lastDayOfYear()));
		
		
		//
		System.out.println("今天是："+localdate.getYear() + "第" + localdate.get(ChronoField.ALIGNED_WEEK_OF_YEAR) + "周");
		System.out.println("今天是周：" +localdate.getDayOfWeek());
		System.out.println("今天是本周的第："+localdate.getDayOfWeek().getValue()+"天");
		
		//日期比较
		System.out.println("今天在20171001之后："+localdate.isAfter(LocalDate.of(2017, 12, 20)));
		
		//格式化
		System.out.println("格式化："+localdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		//时间戳转换
		System.out.println("当前时间："+Instant.now().atZone(ZoneId.systemDefault()));
		System.out.println("当前时间戳："+Instant.now().toEpochMilli());
		System.out.println("时间戳转为时间字符串：" + Instant.ofEpochMilli(Instant.now().toEpochMilli()).atZone(ZoneId.systemDefault()));
		System.out.println("---2--");
		LocalDateTime today = LocalDateTime.now();
		System.out.println("curr datetime: "+today);
		
		today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		System.out.println("ldate + localtime de curr datetime:"+today);
		//得到指定时间
		LocalDateTime ldt = LocalDateTime.of(2017, Month.DECEMBER, 20, 12, 23, 34);
		System.out.println("specifi datetime :"+ldt);
		
		LocalDateTime dateFromBase = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
		System.out.println("10000th second time from 01/01/1970= " + dateFromBase);

		// Duration example
		Duration thirtyDay = Duration.ofDays(30);
		System.out.println("30天:" + thirtyDay);
		
		Instant second = Instant.now();
		// 计算两个时间，之间的长度 持续运行时间
		Duration duration = Duration.between(ins, second);
		System.out.println("程序运行时间：" + duration+",转,等价于"+duration.toMillis()+"ms");
		// 日期间隔计算
		Period period = localdate.until(localdate.with(TemporalAdjusters.lastDayOfYear()));
		System.out.println("Period Format= " + period);
		System.out.println("Months remaining in the year= " + period.getMonths());
	}

}
