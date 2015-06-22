package com.UserMood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import com.Friend.GetFriendList;
import com.VO.UserMood;

public class GetUserMood {

	private Connection connection;
	private List<String> friendList = new ArrayList<String>();
	private String Username;
	public GetUserMood(Connection connection, String Username) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		GetFriendList getFriendList = new GetFriendList(connection, Username);
		friendList = getFriendList.GetFriend();
		this.Username = Username;
	}

	public String GetMood() {
		Map<String, List<UserMood>> UserMoodMap = new Hashtable<String, List<UserMood>>();
		String JsonString = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		for (int i = 0; i < friendList.size(); i++) {
			try {
				connection.createStatement();
				String sql = "select * from mood_" + friendList.get(i);
				pstmt = connection.prepareStatement(sql);
				resultSet = pstmt.executeQuery();
				List<UserMood> moodList = new ArrayList<UserMood>();
				while (resultSet.next()) {
					UserMood mood = new UserMood();
					mood.setID(String.valueOf(resultSet.getInt("ID")));
					mood.setLongitude(resultSet.getString("Longitude"));
					mood.setLatitude(resultSet.getString("Latitude"));
					mood.setTime(resultSet.getString("Time"));
					mood.setMood(resultSet.getString("Mood"));
					moodList.add(mood);
				}
				UserMoodMap.put(friendList.get(i), moodList);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		try {
			//TODO 更新好友表通知刷新状态
			String RefreshSql = "update userdata set MoodRefresh='false' where User_name=?";
			pstmt = connection.prepareStatement(RefreshSql);
			pstmt.setString(1, Username);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSON json = JSONSerializer.toJSON(UserMoodMap);
		JsonString = json.toString();
		return JsonString;
	}
}
