package cn.chenjw.codedemo.file.monitor;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileMonitorUtil {
	public static FileAlterationMonitor addMonitor(String path){
		FileAlterationMonitor monitor = new FileAlterationMonitor(100);//监控器
		FileAlterationObserver observer = new FileAlterationObserver(path);//对某个路径的观察者
		observer.addListener(new PushFileAlterationListener(path));//添加箭头事件响应，余path无关，rsync同步需要
		monitor.addObserver(observer);//将观察者添加哦到监控器
		
		try {
			monitor.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return monitor;
	}
	
	public static void main(String[] args) {
		String path = "E:/A/jnotify";
		
		FileAlterationMonitor monitor = FileMonitorUtil.addMonitor(path);
		try {
			System.out.println("开始监控：");
			Thread.sleep(1000000);
			monitor.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
