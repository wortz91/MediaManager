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
	Purchase Info

	<table border="1">

		<tr>
			<th>ID</th>		
			<th>Action</th>
			<th>PurchaseLocation</th>
		</tr>

		

		<c:forEach items="${sessionScope.purchaseInfoList}" var="purchaseInfo">
			<c:url value="purchaseInfoAdmin" var="purchaseInfoServletURL">
				<c:param name="action" value="getPurchaseInfo" />
				<c:param name="ID" value="${purchaseInfo.purchaseID}" />
			</c:url>
			
			<c:url value="purchaseInfoAdmin" var="purchaseInfoServletURLDelete">
				<c:param name="action" value="deletePurchaseInfo" />
				<c:param name="purchaseID" value="${purchaseInfo.purchaseID}" />
			</c:url>
			
			<tr>
				<td>
					<c:out value="${purchaseInfo.purchaseID}" />
				</td>
 				<td>
 					<a href="${purchaseInfoServletURL}">Edit</a> 
 					&nbsp; 
 					<a href="${purchaseInfoServletURLDelete}">Delete</a> 
 				</td>  
				<td>${purchaseInfo.purchaseLocation}</td>
			</tr>
		</c:forEach>

	</table>
	<a href="/MediaManagerFinal/admin/purchaseInfoInsert.jsp">Insert New Purchase Info</a>
	<button onclick="history.back()">Back to Previous Page</button>
</body>
</html>