package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import com.JDBC.JDBC;

public class FriendListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FriendListServlet() {
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
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		List<String> friendList = jdbc.GetFriendList(Username);
		
		JSON json = JSONSerializer.toJSON(friendList);
		String FriendListJson = json.toString();
		PrintWriter out = response.getWriter();
		out.print(FriendListJson);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
