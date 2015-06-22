package com.UserManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.VO.UserData;

public class UserRegister {

	private Connection connection;
	private UserData userData;
	private String Password;
	
	public UserRegister(Connection connection,UserData userData,String Password) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.userData = userData;
		this.Password = Password;
	}

	public String Register() {
		String Register = null;
		int Result = 0;
		PreparedStatement pstmt = null;
		try {
			connection.createStatement();
			String sql = "insert into userdata values(NULL,?,?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userData.getUserName());
			pstmt.setString(2, Password);
			pstmt.setString(3, userData.getName());
			pstmt.setString(4, userData.getPhone());
			pstmt.setString(5, userData.getSex());
			pstmt.setString(6, userData.getLongitude());
			pstmt.setString(7, userData.getLatitude());
			pstmt.setString(8, userData.getOnline());
			Result = pstmt.executeUpdate();
			Register = String.valueOf(Result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Register;
	}
}
