package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;

/**
 * @author hao
 *客户端发送请求，服务器发送通知
 */
public class NoticeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public NoticeServlet() {
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
		String NoticeJson = null;
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		NoticeJson = jdbc.GetNotice(Username);
		
		PrintWriter out = response.getWriter();
		out.print(NoticeJson);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
