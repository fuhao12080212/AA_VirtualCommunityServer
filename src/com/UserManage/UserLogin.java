package com.UserManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {

	private Connection connection;
	private String Username;
	private String Password;

	public UserLogin(Connection connection, String Username, String Password) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.Username = Username;
		this.Password = Password;
	}

	public String GetLoginResult() {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			connection.createStatement();
			String sql = "select * from userdata where User_name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Username);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				String SQLpw = resultSet.getString("Pass_word");
				if (SQLpw.equals(Password)) {
					return "pass";
				} else {
					return "not pass";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "not find";
	}
}
