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
<h2>Media Type Administration</h2>
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
	Media Type Library

	<table border="1">

		<tr>
			<th>ID</th>		
			<th>Action</th>
			<th>MediaTypeDescription</th>
		</tr>

		

		<c:forEach items="${sessionScope.mediaTypeList}" var="mediaType">
			<c:url value="mediaTypeAdmin" var="mediaTypeServletURL">
				<c:param name="action" value="getMediaType" />
				<c:param name="ID" value="${mediaType.mediaID}" />
			</c:url>
			
			<c:url value="mediaTypeAdmin" var="mediaTypeServletURLDelete">
				<c:param name="action" value="deleteMediaType" />
				<c:param name="ID" value="${mediaType.mediaID}" />
			</c:url>
			
			<tr>
				<td>
					<c:out value="${mediaType.mediaID}" />
				</td>
 				<td>
 					<a href="${mediaTypeServletURL}">Edit</a> 
 					&nbsp; 
 					<a href="${mediaTypeServletURLDelete}">Delete</a> 
 				</td>  
				<td>${mediaType.mediaTypeDescription}</td>
			</tr>
		</c:forEach>

	</table>
	<a href="/MediaManagerFinal/admin/mediaTypeItemDetailInsert.jsp">Insert New Item</a>
	<button onclick="history.back()">Back to Previous Page</button>
</body>
</html>