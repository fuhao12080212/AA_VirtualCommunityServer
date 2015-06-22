package com.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetFriendList {

	private Connection connection;
	private String Username;
	
	public GetFriendList(Connection connection , String Username) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.Username = Username;
	}

	public List<String> GetFriend() {
		List<String> friendList = new ArrayList<String>();
		try {
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			connection.createStatement();
			String sql = "select * from friend_" + Username;
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				String friend = resultSet.getString("Friend");
				friendList.add(friend);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return friendList;
	}
}
