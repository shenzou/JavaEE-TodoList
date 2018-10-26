package com.shenzou.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/AddTodoServlet")
public class AddTodoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TodoDBUtil todoDbUtil;
	@Resource(name="jdbc/tododb")
	private DataSource dataSource;
	
	public AddTodoServlet() 
	{
		super();
	}

	@Override 
	public void init() throws ServletException 
	{
		super.init();
		todoDbUtil = new TodoDBUtil(dataSource);
	}

	protected void doGet(HttpServletRequest  request, HttpServletResponse  response)  throws ServletException, IOException 
	{
		try
		{
			String role = (String)request.getSession().getAttribute("role");
			if(role.equals("instructor"))
			{
				request.getRequestDispatcher("/add-todo.jsp").forward(request, response);
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
	
	protected void doPost(HttpServletRequest   req,   HttpServletResponse  resp)   throws ServletException, IOException 
	{
		String description= req.getParameter("description");
		Todos todo = new Todos(description);
		todoDbUtil.AddTodo(todo);
		resp.sendRedirect("TodoControllerServlet");
	}
		

}

