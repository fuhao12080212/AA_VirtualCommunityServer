package com.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FriendRequestState {

	private Connection connection;
	private String Username;
	
	public FriendRequestState(Connection connection,String Username) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.Username = Username;
	}
	
	public String GetState(){
		String State = null;
		try {
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			connection.createStatement();
			String sql = "select * from userdata where User_name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Username);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				State = resultSet.getString("FriendRequest");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return State;
	}

}
