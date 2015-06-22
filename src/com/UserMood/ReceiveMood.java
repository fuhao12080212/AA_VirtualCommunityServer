package com.UserMood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.Friend.GetFriendList;
import com.VO.UserMood;

public class ReceiveMood {

	private Connection connection;
	private UserMood userMood;
	private String Username;
	private List<String> FriendList = new ArrayList<String>();

	public ReceiveMood(Connection connection, UserMood userMood, String Username) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.userMood = userMood;
		this.Username = Username;
		GetFriendList getFriendList = new GetFriendList(connection, Username);
		FriendList = getFriendList.GetFriend();
	}

	public String MoodToDB() {
		int result = 0;
		try {
			PreparedStatement pstmt = null;
			connection.createStatement();
			String sql = "insert into mood_" + Username + " values(NULL,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userMood.getLongitude());
			pstmt.setString(2, userMood.getLatitude());
			pstmt.setString(3, userMood.getTime());
			pstmt.setString(4, userMood.getMood());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		for (int i = 0; i < FriendList.size(); i++) {
			try {
				PreparedStatement pstmt = null;
				connection.createStatement();
				String sql = "update userdata set MoodRefresh='true' where User_name=?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, FriendList.get(i));
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return String.valueOf(result);
	}
}
