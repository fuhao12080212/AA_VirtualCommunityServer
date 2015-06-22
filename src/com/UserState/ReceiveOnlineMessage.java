package com.UserState;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReceiveOnlineMessage {
	
	private Connection connection;
	private String Username;
	
	public ReceiveOnlineMessage(Connection connection,String Username) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.Username = Username;
	}
	
	public void update() {
		try {
			PreparedStatement pstmt = null;
			connection.createStatement();
			String sql = "update userdata set Online='true' ,HeartBeat = 0 where User_name = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Username);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
