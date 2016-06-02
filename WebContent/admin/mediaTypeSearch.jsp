<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page errorPage="error-404.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>My Media Home Page</title>

	<link rel="stylesheet" type="text/css" href="/MediaManagerFinal/css/main.css" />
</head>

<header> <img
	style="height: auto; width: auto; max-width: 300px; max-height: 300px;"
	src="/MediaManagerFinal/images/mediaLibrary.png">
<h1>Media Library</h1>
<h2>Admin Media Type Search</h2>
</header>
<header2>
<ul>
	<div class="bar">
		<li><a href="/MediaManagerFinal/index.jsp">Home</a></li>
		<li><a href="/MediaManagerFinal/admin/index.jsp">Admin Tools</a></li>
	</div>
</ul>
<br />
</header2>

<body>
	<form action="/MediaManagerFinal/admin/mediaTypeAdmin"  method="get">
		Media Type Item Name(leave blank and search to see entire Database):<br>
		<input type="text" name="mediaTypeItemName">
		<input type="hidden" name="action" value="getAllMediaTypes">
		<input type="submit" name="btnSubmit">
	</form>
	<a href="/MediaManagerFinal/admin/mediaTypeItemDetailInsert.jsp">Insert New Media Type</a>
	<button onclick="history.back()">Back to Previous Page</button>
</body>
</html>