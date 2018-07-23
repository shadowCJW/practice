package cn.chenjw.codedemo.file.monitor;

import net.contentobjects.jnotify.JNotifyListener;

public class Listenner implements JNotifyListener {

	@Override
	public void fileCreated(int wd, String rootPath, String name) {
	System.out.println(wd+",path="+rootPath+",name="+name);
	}

	@Override
	public void fileDeleted(int wd, String rootPath, String name) {
		System.out.println(wd+",path="+rootPath+",name="+name);

	}

	@Override
	public void fileModified(int wd, String rootPath, String name) {
		System.out.println(wd+",path="+rootPath+",name="+name);

	}

	@Override
	public void fileRenamed(int wd, String rootPath, String name, String name2) {
		System.out.println(wd+",path="+rootPath+",name="+name+"-->"+name2);

	}

}
