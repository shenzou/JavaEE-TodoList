package com.shenzou.web.jdbc;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/EditTodoServlet") 
public class EditTodoServlet extends HttpServlet 
{	
	private static final long serialVersionUID = 1L;
	private TodoDBUtil todoDbUtil;
	@Resource(name="jdbc/tododb")
	private DataSource dataSource;
	int id;
	
	@Override 
	public void init() throws ServletException 
	{
		super.init();
		todoDbUtil = new TodoDBUtil(dataSource);
	}
	
	public EditTodoServlet() 
	{
		super();// TODO Auto-generated constructor stub    
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String role = (String)request.getSession().getAttribute("role");
			if(role.equals("instructor"))
			{
				id = Integer.parseInt(request.getParameter("todoId"));
				Todos todo = todoDbUtil.fetchTodo(id);
				request.setAttribute("id", todo);
				request.getRequestDispatcher("edit-todo.jsp").forward(request, response);
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String description = request.getParameter("description");
		Todos todo = new Todos(id,description);
		todoDbUtil.updateTodo(todo);
		response.sendRedirect("TodoControllerServlet");}

}
	