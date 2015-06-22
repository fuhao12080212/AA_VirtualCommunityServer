package com.UserManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.VO.UserData;

public class UserVerification {

	private Connection connection;
	private UserData userData;
	
	public UserVerification(Connection connection,UserData userData) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.userData = userData;
	}

	public String Verification() {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection.createStatement();
			String sql = "select * from userdata where User_name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userData.getUserName());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String Phone = resultSet.getString("Phone");
				String Name = resultSet.getString("Name");
				if (Phone.equals(userData.getPhone())&&Name.equals(userData.getName())) {
					return "pass";
				} else {
					return "not pass";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "not pass";
	}
}
