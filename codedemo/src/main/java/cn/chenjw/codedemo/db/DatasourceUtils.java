package cn.chenjw.codedemo.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.alibaba.fastjson.JSONObject;

public class DatasourceUtils {
	public static String USERNAME = "username";
	public static String PASSWD = "password";
	public static String URL = "url";
	public static String DRIVER = "driverClassName";
	/**
	 * 得到數據庫信息
	 * @param db
	 * @return
	 */
	public static DataSource getDatasource(DataSourceBean db){
		Properties pro = new Properties();
		pro.setProperty(USERNAME, db.getDbUsername());
		pro.setProperty(PASSWD, db.getDbPassword());
		pro.setProperty(URL, db.getDbUrl());
		pro.setProperty(DRIVER, db.getDbDriver());
		try {
			DataSource dbsource = BasicDataSourceFactory.createDataSource(pro);
			return dbsource;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 得到数据库连接
	 * @param ds
	 * @return
	 */
	public static Connection getConnection(DataSourceBean ds){
		try {
			DataSource datasource = getDatasource(ds);
			Connection conn = datasource.getConnection();
			datasource = null;
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 测试数据库能不能连上
	 * @param ds
	 * @return
	 */
	public static boolean testConnection(DataSourceBean ds){
		Connection conn = null;
		try {
			conn = getConnection(ds);
			if(conn == null){
				return false;
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					conn=null;
				}
			}
		}
		
	}
	/**
	 * 获取指定数据源对应的数据库
	 * 
	 * @param dataSource
	 * @return
	 */
	public static Database readDatabase(DataSource dataSource) {
		Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);
		Database db = platform.readModelFromDatabase("model");

		return db;
	}
	/**
	 * 查数据库里面某张表的数据量
	 * @param ds
	 * @param tableName
	 * @return
	 */
	public static int getTableDataCount(DataSource datasource ,String tableName){
		JdbcTemplate jt = new JdbcTemplate(datasource);
		int count = jt.queryForObject("select count(*) from "+tableName, Integer.class);
		return count;
		
	}
	/**
	 * 测试一张表是否存在
	 * @param ds
	 * @param tableName
	 * @return
	 */
	public static boolean testTableExists(DataSourceBean ds, final String tableName){
		String sql = "select count(*) from "+tableName;
		boolean result = false;
		try {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = getConnection(ds);
				ps = conn.prepareStatement(sql);
				ps.execute();
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(ps != null){
					ps.close();
				}
				if(conn != null){
					conn.close();
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 删除表
	 * @param ds
	 * @param tableName
	 * @return
	 */
	public static boolean dropTable(DataSourceBean ds, String tableName){
		String sql = "drop table "+tableName;
		Connection conn = null;
		Statement st = null;
		try {
			
			try {
				conn = getConnection(ds);
				st = conn.createStatement();
				st.execute(sql);
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				if(st != null){
					st.close();
				}
				if(conn != null){
					conn.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
		
	}
	/**
	 * 获取表结构
	 * @param dataSource
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static List<Column> getStructureByTableName(DataSource dataSource, final String tableName) throws SQLException{
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "select * from "+tableName+" where 1=2";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();
			//主键
			ResultSet pkRS = dbmd.getPrimaryKeys(null, null, tableName);
			final Map<String, Boolean> pkMap = new HashMap<String, Boolean>();
			while(pkRS.next()){
				pkMap.put(pkRS.getString("COLUMN_NAME").toUpperCase(), true);
			}
			
			return jt.query(sql, new ResultSetExtractor<List<Column>>(){
				@SuppressWarnings("static-access")
				public List<Column> extractData(ResultSet rs) throws SQLException,
				DataAccessException {
					List<Column> columns = new ArrayList<Column>();

					ResultSetMetaData md = rs.getMetaData();
					int colCount = md.getColumnCount();
					Column col = null;
					for(int i=1; i<=colCount; i++){
						col = new Column();
						col.setName(md.getColumnName(i));
						col.setTypeCode(md.getColumnType(i));
						col.setSize(String.valueOf(md.getColumnDisplaySize(i)));
						col.setScale(md.getScale(i));
						col.setRequired(md.isNullable(i)==md.columnNoNulls);
						if(pkMap.get(md.getColumnName(i).toUpperCase())!=null){
							col.setPrimaryKey(true);
						}
						columns.add(col);
					}
					return columns;
				}
			});
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}finally{
			conn.close();
		}
		
	}
	/**
	 * 创建目标表到指定的数据源中
	 * @param dataSource
	 * @param table
	 * @return
	 */
	public static JSONObject writeTableToDatabase(DataSource dataSource, Table table) {
		JSONObject result = new JSONObject();
		try{
			Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);
			Database db = new Database();
			db.addTable(table);
			System.out.println("建表语句:["+platform.getCreateTablesSql(db, false, true)+"]");
			platform.createTables(db, false, true);
			result.put("success", true);
			result.put("message", "建表成功！");
			return result;
		}catch (Exception e) {
			result.put("success", false);
			result.put("message", e.getMessage());
			return result;
		}
		
	}
	
	
	//======
	
}
