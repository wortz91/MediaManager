<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List,java.util.ArrayList,edu.franklin.beans.MediaItem" %>

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
	Media Purchase Info Page
	
	<table border="1">

			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>GenreID</th>
				<th>MediaID</th>
				<th>Year</th>
				<th>Comments</th>
				<th>CurrentValue</th>
				
				<th>Purchase Price</th>
				<th>Purchase Date</th>
				<th>Purchase Info ID</th>
				<th>Media Item ID</th>
			</tr>
			
		    <c:forEach items="${sessionScope.mediaPurchaseItemList}" var="mediaPurchaseItem">
		      <tr>
		      	<th><c:out value="${mediaPurchaseItem.mediaItem.ID}" /></th>
		      	<th><c:out value="${mediaPurchaseItem.mediaItem.name}" /></th>
		      	<th><c:out value="${mediaPurchaseItem.mediaItem.genreID}" /></th>
		      	<th><c:out value="${mediaPurchaseItem.mediaItem.mediaTypeID}" /></th>
		      	<th><c:out value="${mediaPurchaseItem.mediaItem.year}" /></th>
		      	<th><c:out value="${mediaPurchaseItem.mediaItem.comments}" /></th>
		      	<th><c:out value="${mediaPurchaseItem.mediaItem.currentValue}" /></th>

		        <th><c:out value="${mediaPurchaseItem.purchasePrice}" /></th>	        
		        <th><c:out value="${mediaPurchaseItem.purchaseDate}" /></th>	        
		        <th><c:out value="${mediaPurchaseItem.purchaseInfoID}" /></th>	        
		        <th><c:out value="${mediaPurchaseItem.mediaItemID}" /></th>	        
		      		        
		      </tr>
		    </c:forEach>

	</table>
</body>
</html>