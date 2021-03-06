<%@ page import="java.util.*,com.shenzou.web.jdbc.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>To do List</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

 
<body>
<div id="wrapper">
	<div id="header">
	<h2>TO DO LIST</h2>
	</div>
</div>

<div id="container">
<div id="content">

<p>Hello, ${ sessionScope.name } !</p>

<table>
	<tr>
		<th>ID </th>
		<th>Description</th>
		<th>Options</th>
	</tr>
<c:forEach var="todo" items="${TODOS_LIST }">
	<c:url var="EditLink" value="EditTodoServlet">
	<c:param name="todoId" value="${todo.getID()}"/>
	</c:url>
	<c:url var="DeleteLink" value="DeleteTodoServlet">
	<c:param name="todoId" value="${todo.getID()}"/>
	</c:url>
	<tr>
		<td> ${todo.getID()}</td>
		<td> ${todo.getDescription()}</td>
		<td> <a class="option-button" href="${EditLink}"> Edit</a>|<a class="option-button" href="${ DeleteLink }">Delete</a></td>
	<tr>
</c:forEach>

</table>
</div>
</div>

<div id="container">
<div id="content">
<form action="AddTodoServlet" method="get">
<td>
	<input class="add-button" type="submit" value="Add a To Do"/>
</td>
<td>
	<a class="add-button" href="LoginPage">Logout</a>
</td>
	
</form>
</div>
</div>


</body>



</html>