package cn.chenjw.codedemo.file.json;

public class ShareInfo {
	private String seq;
	private String key;
	private String val;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	@Override
	public String toString() {
		return "ShareInfo [seq=" + seq + ", key=" + key + ", val=" + val + "]";
	}
	
	
}
