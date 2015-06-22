package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JDBC.JDBC;
import com.VO.UserData;

public class UserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UserRegisterServlet() {
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
		PrintWriter out = response.getWriter();
		
		UserData userData = new UserData();
		userData.setUserName(request.getParameter("Username"));
		userData.setName(request.getParameter("Name"));
		userData.setOnline(request.getParameter("Online"));
		userData.setPhone(request.getParameter("Phone"));
		userData.setSex(request.getParameter("Sex"));
		userData.setLatitude(request.getParameter("Latitude"));
		userData.setLongitude(request.getParameter("Longitude"));
		String Password = request.getParameter("Password");
		JDBC jdbc = new JDBC();
		jdbc.ConnectToSQL();
		
		String Exist = jdbc.UserExist(userData.getUserName());
		if (Exist.equals("exist")) {
			out.print("exist");
		}else if (Exist.equals("not exist")) {
			String LoginResult = jdbc.UserRegister(userData, Password);
			out.print(LoginResult);
		}
		
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
