package com.DBConnector;

import java.sql.*;

public class DBConnector {
	public Connection connection;

	public static Connection DBcon() throws SQLException {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test", "root", "");
 
		} catch (Exception e) {

			System.out.println("Error : " + e.getMessage());

			e.printStackTrace();
			return conn;
		}

		return conn;

	}

	public static Boolean Login(String username, String password) {
		String sql = "select * from user where User_Name=? and User_Password=?";
		Connection conn = null;
		PreparedStatement sqlquery = null;
		try {
			conn = DBcon();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			sqlquery = conn.prepareStatement(sql);
			sqlquery.setString(1, username );
			sqlquery.setString(2, password);
	 
			ResultSet rs = sqlquery.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
