package com.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;

public class AcceptRequest {

	private Connection connection;
	private String RequestUser;
	private String ReceiveUser;

	public AcceptRequest(Connection connection, String RequestUser,
			String ReceiveUser) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.RequestUser = RequestUser;
		this.ReceiveUser = ReceiveUser;
	}

	public String updata() {
		// 获取系统时间
		String result = null;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		String time = year + "-" + month + "-" + day + "*" + hour + ":"
				+ minute;
		try {
			// 接收人好友添加
			int Re = 0;
			PreparedStatement pstmt = null;
			connection.createStatement();
			String sql = "insert into friend_" + ReceiveUser
					+ "values(NULL,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, RequestUser);
			pstmt.setString(2, time);
			Re = pstmt.executeUpdate();

			// 发送人好友添加
			pstmt = null;
			sql = null;
			sql = "insert into friend_" + RequestUser + "values(NULL,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ReceiveUser);
			pstmt.setString(2, time);
			Re = pstmt.executeUpdate();
			//
			// 更新Request表
			pstmt = null;
			sql = null;
			sql = "update friendrequest set State='true' " +
					"where RequestUser=? and ReceiveUser=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, RequestUser);
			pstmt.setString(2, ReceiveUser);
			Re = pstmt.executeUpdate();
			result = String.valueOf(Re);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}
}
