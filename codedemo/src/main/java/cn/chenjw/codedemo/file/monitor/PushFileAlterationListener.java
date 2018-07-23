package cn.chenjw.codedemo.file.monitor;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class PushFileAlterationListener implements FileAlterationListener {

	String rootPath = "";
	public PushFileAlterationListener(String rootPath){
		this.rootPath = rootPath;
	}
	@Override
	public void onStart(FileAlterationObserver observer) {
		//System.out.println("start");
	}

	@Override
	public void onDirectoryCreate(File file) {
		System.out.println("Directory create: " + file.getAbsolutePath()+" , "+file.getParent());

	}

	@Override
	public void onDirectoryChange(File file) {
		System.out.println("Directory change: " + file.getAbsolutePath()+" , "+file.getParent());

	}

	@Override
	public void onDirectoryDelete(File file) {
		System.out.println("Directory delete: " + file.getAbsolutePath()+" , "+file.getParent());
	}

	@Override
	public void onFileCreate(File file) {
		System.out.println("file create: " + file.getAbsolutePath()+" , "+file.getParent());

	}

	@Override
	public void onFileChange(File file) {
		System.out.println("file change: " + file.getAbsolutePath()+" , "+file.getParent());

	}

	@Override
	public void onFileDelete(File file) {
		System.out.println("file delete: " + file.getAbsolutePath()+" , "+file.getParent());

	}

	@Override
	public void onStop(FileAlterationObserver observer) {
		// TODO Auto-generated method stub
		//System.out.println("end");
	}

}
