package com.shenzou.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TodoDBUtil {
	
	private DataSource dataSource;
	
	public TodoDBUtil(DataSource theDataSource) 
	{
		dataSource = theDataSource;
	}
	
	public List<Todos> getTodos() throws Exception
	{
		List<Todos> todos = new ArrayList<Todos>();
		
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from id order by id";
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())
			{
				int id = myRs.getInt("id");
				String description = myRs.getString("description");
				Todos tempTodo= new Todos(id,description);
				todos.add(tempTodo);
			}
			return todos;
			} 
		finally
		{
			close(myConn,myStmt,myRs);
		}
	}
	
	public User verifyUser(String username, String password) throws Exception
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "select role from students where username='"+username+"' and password='"+password+"';";
			myRs = myStmt.executeQuery(sql);
			myRs.next();
			String role = myRs.getString("role");
			User user = new User(username, password, role);
			return user;
		}
		catch (Exception e)
		{
			User user = new User("null","null","null");
			return user;
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
	}
	
	public void deleteTodo(int id)
	{
		Connection myConn=null;
		Statement myStmt=null;
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "delete from id where id="+id;
			myStmt.execute(sql);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			close(myConn, myStmt, null);
		}
	}
	
	public Todos fetchTodo(int id)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		Todos todo = null;
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "select * from id where id="+id;
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()) {
				String description = myRs.getString("description");
				todo = new Todos(id, description);
			}
			return todo;
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	public void updateTodo(Todos todo)
	{
		Connection myConn=null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "update id set description=? where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, todo.getDescription());
			myStmt.setInt(2, todo.getID());
			myStmt.execute();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			close(myConn, myStmt, null);
		}
	}
	
	public void AddTodo(Todos todo)
	{
		Connection myConn=null;
		PreparedStatement myStmt = null;
		ResultSet myRs=null;
		try {
			myConn = dataSource.getConnection();
			String sql = "insert into id (description) values (?)";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, todo.getDescription());
			myStmt.execute();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) 
	{
		try
		{
			if(myStmt!=null) myStmt.close();
			if(myRs!=null) myRs.close();
			if(myConn!=null) myConn.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}


