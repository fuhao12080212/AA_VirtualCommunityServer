package com.UserState;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.VO.UserLocation;

public class UpdateUserLocation {

	private Connection connection;
	private UserLocation userLocation;
	
	public UpdateUserLocation(Connection connection,UserLocation userLocation) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.userLocation = userLocation;
	}

	public String UpdateLocation() {
		int Result = 0;
		try {
			PreparedStatement pstmt = null;
			connection.createStatement();
			String sql = "update userdata set Local_longitude = ?,Local_latitude=? where User_name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userLocation.getLongitude());
			pstmt.setString(2, userLocation.getLatitude());
			pstmt.setString(3, userLocation.getUsername());
			Result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return String.valueOf(Result);
	}

}
