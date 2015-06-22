package com.UserState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import com.Friend.GetFriendList;
import com.VO.UserLocation;

public class GetFriendLocation {

	private Connection connection;
	private String Username;

	public GetFriendLocation(Connection connection, String Username) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.Username = Username;
	}

	public String GetLocation() {
		List<UserLocation> LocationList = new ArrayList<UserLocation>();
		List<String> list = new ArrayList<String>();
		GetFriendList friendList = new GetFriendList(connection, Username);

		list = friendList.GetFriend();
		for (int i = 0; i < list.size(); i++) {
			try {
				PreparedStatement pstmt = null;
				ResultSet resultSet = null;
				connection.createStatement();
				String sql = "select * from userdata where User_name=? and Online='true'";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, list.get(i));
				resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					UserLocation userLocation = new UserLocation();

					String Longitude = resultSet.getString("Local_longitude");
					String latitude = resultSet.getString("Local_latitude");
					userLocation.setUsername(list.get(i));
					userLocation.setLongitude(Longitude);
					userLocation.setLatitude(latitude);
					LocationList.add(userLocation);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		JSON json = JSONSerializer.toJSON(LocationList);
		return json.toString();
	}
}
