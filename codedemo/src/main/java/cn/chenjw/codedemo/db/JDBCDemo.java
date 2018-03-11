package cn.chenjw.codedemo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCDemo {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/cjw?useUnicode=true&characterEncoding=utf8&autoReconnect=true&autoReconnectForPools=true";
			String user = "root";
			String password = "root";
			
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from tb_user where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 1);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				String username = rs.getString("username");
				String passwd = rs.getString("password");
				System.out.println("user "+username + "'s password is "+ passwd);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

}
