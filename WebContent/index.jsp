<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error-404.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Media Home Page - Demo</title>

<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>



<body>
	<header> <img
		style="height: auto; width: auto; max-width: 300px; max-height: 300px;"
		src="images/mediaLibrary.png" />
	<h1>Media Library</h1>
	<h2>All Sorts of Media</h2>
	</header>
	<header2>
	<ul>
		<div class="bar">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="mediaItemSearch.jsp">Media Item Search</a></li>
			<li><a href="genre?action=getItemCounts">Genre Counts</a></li>
			<li><a href="mediaType?action=getItemCounts">Media Type Counts</a></li>
			<li><a href="purchaseInfo?action=getItemCounts">Purchase Info Counts</a></li>
			<li><a href="admin/index.jsp">Admin Tools</a></li>
			<li><a href="angular/index.html">angularJS Example</a></li>
		</div>
	</ul>
	<br />
	</header2>

	This is the website hub that will allow you to search for media in the
	database. We are a fledgling company that hopes to become your one stop
	shop for storing media information about your physical and digital
	media (books, e-books, movies, CDs etc.).
	<br>
	<br> We look forward to your next visit!

</body>
</html>