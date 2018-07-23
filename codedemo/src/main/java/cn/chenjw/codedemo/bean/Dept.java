package cn.chenjw.codedemo.bean;

public class Dept {
	private String id;
	private String pid;
	private String name;
	
	
	public Dept(String id, String pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Dept [id=" + id + ", pid=" + pid + ", name=" + name + "]";
	}

	
}
