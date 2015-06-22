package com.servlet;


import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.JDBC.JDBC;
import com.UserState.HeartBeatThread;

public class AutoUpdateUserStateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HeartBeatThread thread;
	private Connection connection;
	public AutoUpdateUserStateServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
		thread.flag = false;
	}

	public void init() throws ServletException {
		// Put your code here
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		connection = jdbc.getConnection();
		thread = new HeartBeatThread(connection);
		thread.start();
	}

}
