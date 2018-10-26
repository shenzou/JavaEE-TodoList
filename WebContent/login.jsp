<%@ page import="java.util.*,com.shenzou.web.jdbc.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
<link type="text/css" rel="stylesheet" href="css/style.css">
<title>Login page</title>
</head>

<body>

<%
String username=null;
String password = null;
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	 for (Cookie cookie : cookies) {
         if (cookie.getName().equals("username")) username=cookie.getValue();
         if (cookie.getName().equals("password")) password=cookie.getValue();
 }
 }
%>


<div id="wrapper">
<div id="header">
<h2>ESILV Engineer School</h2>
</div>
</div>

<div id="container">
<h3> Login page</h3>
<form action="TodoControllerServlet" method="post">
<table>
<tbody>
<tr>
	<td>
	<label>Username: </label>
	</td>
	<td>
	<input type="text" name="username" value="<%= username %>" id="username"/>
	</td> 
</tr> 

<tr>
	<td>
	<label>Password: </label>
	</td>
	<td>
	<input type="text" name="password" value="<%= password %>" id="password"/>
	</td>
</tr>

<tr>
<td><label></label></td>

<td>
<input type="submit" value="Send"/>
</td>
</tr>
</tbody>
</table>

</form>

</div>
</body>

</html>