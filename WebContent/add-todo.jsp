<%@ page import="java.util.*,com.shenzou.web.jdbc.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/menus-style.css">
<link type="text/css" rel="stylesheet" href="css/style.css">

<title>Add a To do</title>
</head>
<body>

<div id="wrapper">
<div id="header">
<h2>ESILV Engineer School</h2>
</div></div>
<p>${ sessionScope.name }, do you want to add a To Do?</p>
<div id="container">
<h3> Add a To Do</h3>
<form action="AddTodoServlet" method="post">
<table>
	<tbody>
	<tr>
		<td>
		<label>Description: </label>
		</td>
		<td>
		<input type="text" name="description"/>
		</td>
	</tr>

	
	</tbody>
</table>
<tr>
		<td>
		<label></label></td>
		<td>
		<input class="classic-button" type="submit" value="Save"/>
		</td>
		<td> <a href="TodoControllerServlet" class="classic-button">Undo</a> </td>
	</tr>
</form>

</div>




</body>


</html>