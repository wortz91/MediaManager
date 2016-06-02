<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="java.util.List,java.util.ArrayList,edu.franklin.beans.MediaItem"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page errorPage="error-404.jsp"%>


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
	Media Library

	<table border="1">

		<tr>
			<th>View ID</th>
			<th>Name</th>
			<th>GenreID</th>
			<th>MediaID</th>
			<th>Year</th>
			<th>Comments</th>
			<th>CurrentValue</th>
		</tr>

		

		<c:forEach items="${sessionScope.mediaItemList}" var="mediaItem">
			<c:url value="mediaItem" var="servletURL">
				<c:param name="action" value="getItemDetail" />
				<c:param name="ID" value="${mediaItem.ID}" />
			</c:url>
			
			<tr>
<%-- 				<td><a href="mediaItem?action=getItemDetail&ID=${mediaItem.ID}">${mediaItem.ID}</a></td> --%>
				<td><a href="${servletURL}"><c:out value="${mediaItem.ID}" /></a></td>	
								
				<td>${mediaItem.name}</td>
				<td>${mediaItem.genreID}</td>
				<td>${mediaItem.mediaTypeID}</td>
				<td><c:out value="${mediaItem.year}" /></td>
				<td><c:out value="${mediaItem.comments}" /></td>
				<td><c:out value="${mediaItem.currentValue}" /></td>
			</tr>
		</c:forEach>

	</table>
	<button onclick="history.back()">Back to Previous Page</button>
</body>
</html>