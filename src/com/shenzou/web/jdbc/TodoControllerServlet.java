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

@WebServlet("/TodoControllerServlet")
public class TodoControllerServlet extends HttpServlet 
	{
		private static final long serialVersionUID = 1L;
		private TodoDBUtil TodoDbUtil;
		@Resource(name="jdbc/tododb")
		private DataSource dataSource;
		String role;
		String instructor;
		String student;
		String username;
		HttpSession session;
				
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			try 
			{
				role = (String)request.getSession().getAttribute("role");
				if(role.equals(instructor))
				{
					listTodosInstruct(request, response);
				}
				else if(role.equals(student))
				{
					listTodos(request, response);
				}
				else
				{
					System.out.println("Error here");
				}
			}
			catch (Exception e)
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/loginerror.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}
		}
		
		
		private void listTodos(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			List<Todos> todos = TodoDbUtil.getTodos();
			request.setAttribute("TODOS_LIST", todos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/todos.jsp");
			dispatcher.forward(request, response);
		}
		
		private void listTodosInstruct(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			List<Todos> todos = TodoDbUtil.getTodos();
			request.setAttribute("TODOS_LIST", todos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/todosInstruct.jsp");
			dispatcher.forward(request, response);
		}
		
		private void falseUser(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginerror.jsp");
			dispatcher.forward(request, response);
		}
		
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			username = req.getParameter("username");
			String password= req.getParameter("password");
			
			session = req.getSession();
			session.setAttribute("name", username);
			
			
			Cookie cookie = new Cookie("username", username);
	        cookie.setMaxAge(60 * 60 * 24 * 30);
	        resp.addCookie(cookie);
	        Cookie pass = new Cookie("password", password);
	        cookie.setMaxAge(60 * 60 * 24 * 30);
	        resp.addCookie(pass);
	        
			try {
				
				User user = TodoDbUtil.verifyUser(username, password);
				instructor = "instructor";
				student = "student";
				role = user.getRole();
				req.getSession().setAttribute("role", role);
				if(role.equals(instructor))
				{
					listTodosInstruct(req, resp);
				}
				else if(role.equals(student))
				{
					listTodos(req, resp);
				}
				else
				{
					falseUser(req, resp);
					System.out.println("Unknown error.");
				}
				
			} catch (Exception e)
			{
				
				e.printStackTrace();
			}
		}
		
		@Override
		public void init() throws ServletException 
		{
			// TODO Auto-generated method stub
			super.init();
			TodoDbUtil = new TodoDBUtil(dataSource);
		}
	}

