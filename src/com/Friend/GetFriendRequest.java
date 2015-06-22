package com.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import com.VO.Request;

public class GetFriendRequest {

	private Connection connection;
	private String Username;
	
	public GetFriendRequest(Connection connection, String Username) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.Username = Username;
	}

	public String GetRequest() {
		List<Request> RequestList = new ArrayList<Request>();
		try {
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			connection.createStatement();
			String sql = "select * from friendrequest where ReceiveUser = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Username);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Request request = new Request();
				request.setRequestUser(resultSet.getString("RequestUser"));
				request.setRequestUser(resultSet.getString("ReceiveUser"));
				request.setIntroduction(resultSet.getString("Introduction"));
				request.setTime(resultSet.getString("Time"));
				request.setState(resultSet.getString("State"));
				RequestList.add(request);
			}
			
			pstmt = null;
			String updatasql = "update userdata set FriendRequest='false' where User_name=?";
			pstmt = connection.prepareStatement(updatasql);
			pstmt.setString(1, Username);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		JSON json = JSONSerializer.toJSON(RequestList);
		return json.toString();
	}
}
