package com.JDBC;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.Notice.GetUserNotice;
import com.UserManage.UserLogin;
import com.UserManage.UserChangePW;
import com.UserManage.UserRegister;
import com.UserManage.UserRegister_Exist;
import com.UserManage.UserVerification;
import com.UserMood.GetMoodRefreshState;
import com.UserMood.GetUserMood;
import com.Notice.GetNoticeRefreshState;
import com.VO.UserData;
import com.VO.UserMood;
import com.Friend.GetFriendList;
import com.UserMood.ReceiveMood;
import com.Friend.GetFriendInfo;

public class JDBC {

	//huhaha
	private Connection connection;

	public JDBC() {

	}

	public Connection getConnection() {
		return connection;
	}

	public void ConnectToSQL() {
		String USERNAME = "root";
		String PASSWORD = "root";
		String URL = "jdbc:mysql://localhost:3306/virtualcommunity";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// TODO 用户管理
	public String UserLogin(String Username, String password) {

		UserLogin userLogin = new UserLogin(connection, Username, password);
		String LoginResult = null;
		LoginResult = userLogin.GetLoginResult();
		return LoginResult;
	}

	public String UserExist(String Username) {
		UserRegister_Exist userRegister_Exist = new UserRegister_Exist(
				Username, connection);
		String Exist = null;
		Exist = userRegister_Exist.judge();
		return Exist;
	}

	public String UserRegister(UserData userData, String Password) {
		UserRegister userRegister = new UserRegister(connection, userData,
				Password);
		String RegisterResult = null;
		RegisterResult = userRegister.Register();
		return RegisterResult;
	}

	public String UserVerification(UserData userData) {
		UserVerification userVerification = new UserVerification(connection,
				userData);
		String VerificationResult = null;
		VerificationResult = userVerification.Verification();
		return VerificationResult;
	}

	public int UserChangePW(String Username, String password) {
		UserChangePW userChangePW = new UserChangePW(connection, Username,
				password);
		int ChangeResult = 0;
		ChangeResult = userChangePW.ChangePW();
		return ChangeResult;
	}

	// TODO 消息通知
	public String GetNotice(String Username) {
		String NoticeJson = null;
		GetUserNotice getUserNotice = new GetUserNotice(connection, Username);
		NoticeJson = getUserNotice.GetNoticeList();
		return NoticeJson;
	}

	public String GetNoticeRefreshState(String Username) {
		String State = null;
		GetNoticeRefreshState getNoticeRefreshState = new GetNoticeRefreshState(
				connection, Username);
		State = getNoticeRefreshState.GetRefreshState();
		return State;
	}

	// TODO 状态通知
	public String GetMood(String Username) {
		String MoodJson = null;
		GetUserMood getUserMood = new GetUserMood(connection, Username);
		MoodJson = getUserMood.GetMood();
		return MoodJson;
	}

	public String GetMoodRefresh(String Username) {
		String State = null;
		GetMoodRefreshState getMoodRefreshState = new GetMoodRefreshState(
				connection, Username);
		State = getMoodRefreshState.GetMoodState();
		return State;
	}

	public String ReceiveMood(String Username, UserMood userMood) {
		String result = null;
		ReceiveMood receiveMood = new ReceiveMood(connection, userMood,
				Username);
		result = receiveMood.MoodToDB();
		return result;
	}

	// TODO Friend处理
	public List<String> GetFriendList(String Username) {
		List<String> friendList = new ArrayList<String>();
		GetFriendList getFriendList = new GetFriendList(connection, Username);
		friendList = getFriendList.GetFriend();
		return friendList;
	}

	public String GetFriendInfo(String Username) {
		GetFriendInfo friendInfo = new GetFriendInfo(connection, Username);
		String Info = friendInfo.GetInfo();
		return Info;
	}
}
