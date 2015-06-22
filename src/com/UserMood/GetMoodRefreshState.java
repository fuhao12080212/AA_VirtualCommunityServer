package com.UserMood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMoodRefreshState {

	private Connection connection;
	private String Username;
	public GetMoodRefreshState(Connection connection,String Username) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.Username = Username;
	}

	public String GetMoodState() {
		String State = null;
		try {
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			connection.createStatement();
			String sql = "select * from userdata where User_name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Username);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				State = resultSet.getString("MoodRefresh");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return State;
	}
}
