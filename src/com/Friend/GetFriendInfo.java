package com.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import com.VO.UserData;

public class GetFriendInfo {

	private Connection connection;
	private String Username;

	public GetFriendInfo(Connection connection, String Username) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.Username = Username;
	}

	public String GetInfo() {
		UserData userData = new UserData();
		try {
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			connection.createStatement();
			String sql = "select * from userdata where User_name = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Username);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				userData.setID(String.valueOf(resultSet.getInt("User_id")));
				userData.setUserName(resultSet.getString("User_name"));
				userData.setName(resultSet.getString("Name"));
				userData.setPhone(resultSet.getString("Phone"));
				userData.setSex(resultSet.getString("Sex"));
				userData.setLongitude(String.valueOf(resultSet
						.getDouble("Local_longitude")));
				userData.setLatitude(String.valueOf(resultSet
						.getDouble("Local_latitude")));
				userData.setOnline(resultSet.getString("Online"));
				userData.setMotto(resultSet.getString("Motto"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (userData.getID() == null) {
			return "not find";
		} else {
			JSON json = JSONSerializer.toJSON(userData);
			return json.toString();
		}
	}

}
