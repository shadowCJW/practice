package cn.chenjw.codedemo.db;

public class DataSourceBean {
	private String  dbUrl;//数据库连接url，对应ODPS的odpsUrl，也即odps endpoint
	private String  dbUsername;//数据库连接用户名，对应ODPS的AccessId
	private String  dbPassword;//数据库连接密码，对应ODPS的AccessKey
	private String  dbDriver;//数据库驱动，对应ODPS的TunnelUrl，也即tunnel endpoint
	
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getDbDriver() {
		return dbDriver;
	}
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

}
