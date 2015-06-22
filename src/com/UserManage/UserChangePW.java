package com.UserManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserChangePW {

	private Connection connection;
	private String Username;
	private String Password;

	public UserChangePW(Connection connection, String Username, String Password) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.Username = Username;
		this.Password = Password;
	}

	public int ChangePW() {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			connection.createStatement();
			String sql = "update userdata set Pass_word=? where User_name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Password);
			pstmt.setString(2, Username);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
