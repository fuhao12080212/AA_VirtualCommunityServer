package com.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.VO.Request;

public class ReceiveFriendRequest {

	private Connection connection;
	private Request request;

	public ReceiveFriendRequest(Connection connection, Request request) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.request = request;
	}

	public String RequestToDB() {
		String result = null;
		int Re = 0;
		try {
			PreparedStatement pstmt = null;
			connection.createStatement();
			String sql = "insert into friendrequest values(NULL,?,?,?,?,'false')";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, request.getRequestUser());
			pstmt.setString(2, request.getReceiveUser());
			pstmt.setString(3, request.getIntroduction());
			pstmt.setString(4, request.getTime());
			Re = pstmt.executeUpdate();
			
			pstmt = null;
			String updatasql = "update userdata set FriendRequest='true' where User_name=?";
			pstmt = connection.prepareStatement(updatasql);
			pstmt.setString(1, request.getReceiveUser());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		result = String.valueOf(Re);
		return result;
	}
}
