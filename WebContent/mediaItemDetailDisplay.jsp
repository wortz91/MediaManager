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
	Media Library Item Detail

	<table border="1">
		<c:forEach items="${sessionScope.mediaItemList}" var="mediaItem">

			<tr>
				<th>ID</th>
				<td><c:out value="${mediaItem.ID}" /></td>
			</tr>
			<tr>

				<th>Name</th>
				<td><c:out value="${mediaItem.name}"/></td>
			</tr>
			<tr>

				<th>GenreID</th>
				<td><c:out value="${mediaItem.genreDescription}" /> (<c:out value="${mediaItem.genreID}" />)</td>
			</tr>
			<tr>
				<th>MediaID</th>
				<td><c:out value="${mediaItem.mediaTypeDescription}" /> (<c:out value="${mediaItem.mediaTypeID}" />)</td>
			</tr>
			<tr>
				<th>Year</th>
				<td><c:out value="${mediaItem.year}" /></td>
			</tr>
			<tr>
				<th>Comments</th>
				<td><c:out value="${mediaItem.comments}" /></td>
			</tr>
			<tr>
				<th>CurrentValue</th>
				<td><c:out value="${mediaItem.currentValue}" /></td>
			</tr>
		</c:forEach>
	</table>
	<button onclick="history.back()">Back to Previous Page</button>
</body>
</html>