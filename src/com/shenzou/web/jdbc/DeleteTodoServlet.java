package com.shenzou.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDBUtil TodoDbUtil;
	@Resource(name="jdbc/tododb")
	private DataSource dataSource;
	@Override 
	public void init() throws ServletException 
	{
		super.init();
		TodoDbUtil = new TodoDBUtil(dataSource);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String role = (String)request.getSession().getAttribute("role");
			if(role.equals("instructor"))
			{
				int id = Integer.parseInt(request.getParameter("todoId"));
				TodoDbUtil.deleteTodo(id);
				response.sendRedirect("TodoControllerServlet");
			}
			else
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/loginerror.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch (Exception e)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginerror.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}
}