package cn.chenjw.codedemo.file.monitor;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;

public class JnotifyDemo {
	public static void main(String[] args) {
		String path = "E:/A/jnotify";
		int mask = JNotify.FILE_CREATED | JNotify.FILE_DELETED | JNotify.FILE_MODIFIED | JNotify.FILE_RENAMED;
		
		//是否监控子目录
		boolean watchSubtree = false;
		try {
			System.err.println("="+System.getProperty("java.library.path"));
			//注意运行出错是要将对应的jnotify_64bit.dll的dll文件放到上面打印出来的任意一个目录里面去
			int watchId = JNotify.addWatch(path, mask, watchSubtree, new Listenner());
			System.out.println("watchid为："+watchId);
			//为啥睡眠？如果不睡眠，程序运行结束，监听就被移除 (Common-io的FileAlterationMonitor会继续运行不休眠)
			Thread.sleep(1000000);
			//根据watchID手动移除监听
			boolean res = JNotify.removeWatch(watchId);
			if (!res) {
				//返回FALSE，监听标识无效
				System.out.println("返回FALSE，监听标识无效....");
			}
		} catch (JNotifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	/*
	 * 		    create modified rename deleted
	新建空文件        	1 	0	0	0
	新建非空文件		1	1	0	0
	拷贝粘贴空文件	1	1	0	0
	拷贝粘贴非空文件	1	2	0	0
	重命名			0	1	1	0
	删除				0	0	0	1
	结论：1、无论是新建还是拷贝粘贴文件，非空文件操作比空文件操作多出发modified一次。*/
}
