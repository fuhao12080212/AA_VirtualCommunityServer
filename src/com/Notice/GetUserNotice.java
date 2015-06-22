package com.Notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import com.VO.Notice;

public class GetUserNotice {

	private List<Notice> noticeList = new ArrayList<Notice>();
	private Connection connection;
	private String User;

	public GetUserNotice(Connection connection, String user) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
		this.User = user;
	}

	public String GetNoticeList() {

		String NoticeJson = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			//TODO 获取通知消息
			connection.createStatement();
			String sql = "select * from notice" + "_" + User;
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Notice notice = new Notice();
				notice.setID(String.valueOf(resultSet.getInt("Id")));
				notice.setEmphasis(resultSet.getString("Emphasis"));
				notice.setTime(resultSet.getString("NoticeTime"));
				notice.setPlace(resultSet.getString("Place"));
				notice.setDetails(resultSet.getString("Details"));
				notice.setSender(resultSet.getString("Sender"));
				noticeList.add(notice);
			}
			
			//TODO 更新好友表通知刷新状态
			String RefreshSql = "update userdata set NoticeRefresh='false' where User_name=?";
			pstmt = connection.prepareStatement(RefreshSql);
			pstmt.setString(1, User);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSON json = JSONSerializer.toJSON(noticeList);
		NoticeJson = json.toString();
		return NoticeJson;
	}
}
