package com.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RequestResult {

	private Connection connection;
	private String RequestUser;
	private String ReceiveUser;

	public RequestResult(Connection connection, String RequestUser,
			String ReceiveUser) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.RequestUser = RequestUser;
		this.ReceiveUser = ReceiveUser;
	}

	public String GetResult() {
		String State = null;
		try {
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			connection.createStatement();
			String sql = "select * from friendrequest where RequestUser=? and ReceiveUser=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, RequestUser);
			pstmt.setString(2, ReceiveUser);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				State = resultSet.getString(State);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return State;
	}
}
