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
	Media Type Update

	<form action="/MediaManagerFinal/admin/purchaseInfoAdmin" method="get">
		<table border="1">
			<c:forEach items="${sessionScope.purchaseInfoList}" var="purchaseInfo">

				<tr>
					<th>Purchase ID</th>
					<td><input type="text" id="purchaseID" name="purchaseID" readonly="true"
						value="<c:out value="${purchaseInfo.purchaseID}"/>"></td>
				</tr>
				<tr>

					<th>Purchase Location</th>
					<td><input type="text" id="purchaseLocation" name="purchaseLocation"
						value="<c:out value="${purchaseInfo.purchaseLocation}"/>"></td>
				</tr>

			</c:forEach>
		</table>
		<input type="hidden" name="action" value="updatePurchaseInfo">
		<button type="submit">Update Purchase Info Table</button>
	</form>
	<button onclick="history.back()">Back to Previous Page</button>
</body>
</html>