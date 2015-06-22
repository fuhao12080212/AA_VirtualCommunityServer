package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;

public class NoticeRefreshServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public NoticeRefreshServlet() {
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
		
		String State = jdbc.GetNoticeRefreshState(Username);
		PrintWriter out = response.getWriter();
		out.print(State);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
