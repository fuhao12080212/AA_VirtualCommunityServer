package com.UserManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRegister_Exist {
	
	private String Username;
	private Connection connection;

	public UserRegister_Exist(String Username, Connection connection) {
		// TODO Auto-generated constructor stub
		this.Username = Username;
		this.connection = connection;
	}

	public String judge() {
		// ����SQL����ִ�ж���
		PreparedStatement pstmt = null;
		// �����ѯ���صĽ������
		ResultSet resultSet = null;
		try {
			connection.createStatement();
			String sql = "select * from userdata where User_name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Username);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				return "exist";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "not exist";
	}
}
