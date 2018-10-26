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
	</tr>
<c:forEach var="todo" items="${TODOS_LIST }">
	<tr>
		<td> ${todo.getID()}</td>
		<td> ${todo.getDescription()}</td>
	<tr>
</c:forEach>

</table>
</div>
</div>

<div style="clear:both;">


<a href="LoginPage">Logout</a>
</div>
</body>



</html>