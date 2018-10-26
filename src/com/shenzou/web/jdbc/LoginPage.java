package com.shenzou.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet 
	{
		private static final long serialVersionUID = 1L;
		private TodoDBUtil TodoDbUtil;
		@Resource(name="jdbc/tododb")
		private DataSource dataSource;
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if (cookie.getName().equals("username")) {
	                    request.setAttribute("username", cookie.getValue());
	                }
	                if (cookie.getName().equals("password")) {
	                    request.setAttribute("password", cookie.getValue());
	                }
	            }
	        }
			try 
			{
				loginRequest(request,response);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		private void loginRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
	        request.getSession().invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		
		
		@Override
		public void init() throws ServletException 
		{
			// TODO Auto-generated method stub
			super.init();
			TodoDbUtil = new TodoDBUtil(dataSource);
		}
	}