<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<h2>All Sorts of Media</h2>
</header>
<header2>
<ul>
	<div class="bar">
		<li><a href="index.jsp">Home</a></li>
	</div>
</ul>
<br />
</header2>

<body>
	Purchase Info Location Page
	
	<table border="1">

			<tr>
				<th>Media ID</th>
				<th>Purchase Location</th>
				<th>Purchase Count</th>
			</tr>
			
		    <c:forEach items="${sessionScope.itemCountList}" var="itemCount">
		      <tr>
		        <th><c:out value="${itemCount.ID}" /></th>
		        <th><c:out value="${itemCount.description}" /></th>
		        <th><c:out value="${itemCount.count}" /></th>      
		      </tr>
		    </c:forEach>

	</table>
</body>
</html>