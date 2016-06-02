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

<link rel="stylesheet" type="text/css"
	href="/MediaManagerFinal/css/main.css" />
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
		<li><a href="/MediaManagerFinal/index.jsp">Home</a></li>
		<li><a href="/MediaManagerFinal/admin/index.jsp">Admin Tools</a></li>
	</div>
</ul>
<br />
</header2>

<body>
	Media Library Item Update

	<form action="/MediaManagerFinal/admin/mediaItemAdmin" method="get">
		<table border="1">
			<c:forEach items="${sessionScope.mediaItemList}" var="mediaItem">

				<tr>
					<th>ID</th>
					<td><input type="text" id="ID" name="ID" readonly="true"
						value="<c:out value="${mediaItem.ID}"/>"></td>
				</tr>
				<tr>

					<th>Name</th>
					<td><input type="text" id="name" name="name"
						value="<c:out value="${mediaItem.name}"/>"></td>
				</tr>
				<tr>

					<th>GenreID</th>
					<td><select id="genreID" name="genreID">
							<c:forEach items="${sessionScope.genreList}" var="itemCount">
								<option value="${itemCount.ID}" ${mediaItem.genreID == itemCount.ID ? 'selected' : ''}>${itemCount.description}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<th>MediaID</th>							
					<td><select id="mediaTypeID" name="mediaTypeID">
							<c:forEach items="${sessionScope.mediaTypeList}" var="itemCount">
								<option value="${itemCount.ID}" ${mediaItem.mediaTypeID == itemCount.ID ? 'selected' : ''}>${itemCount.description}</option>
							</c:forEach>
					</select>
					</td>
							
				</tr>
				<tr>
					<th>Year</th>
					<td><input type="text" id="year" name="year"
						value="<c:out value="${mediaItem.year}"/>"></td>
				</tr>

				<tr>
					<th>Comments</th>
					<td><input type="text" id="comments" name="comments"
						value="<c:out value="${mediaItem.comments}"/>"></td>
				</tr>
				<tr>
					<th>CurrentValue</th>
					<td><input type="text" id="currentvalue" name="currentvalue"
						value="<c:out value="${mediaItem.currentValue}" />"></td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="action" value="updateItemDetail">
		<button type="submit">Update Table</button>
	</form>
	<button onclick="history.back()">Back to Previous Page</button>
</body>
</html>