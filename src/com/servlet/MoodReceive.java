package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;
import com.VO.UserMood;

public class MoodReceive extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public MoodReceive() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String Username = request.getParameter("Username");
		UserMood userMood = new UserMood();
		userMood.setLongitude(request.getParameter("Longitude"));
		userMood.setLatitude(request.getParameter("Latitude"));
		userMood.setTime(request.getParameter("Time"));
		userMood.setMood(request.getParameter("Mood"));
		
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		String result = jdbc.ReceiveMood(Username, userMood);
		
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
