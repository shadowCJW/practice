package cn.chenjw.codedemo.serializable;

import java.io.Serializable;

/**
 * 
 * @author chenjw
 * 2017年9月24日
 * 实现java序列化的简单例子
 * 方式一，实现Serializable接口
 */
public class Article implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
