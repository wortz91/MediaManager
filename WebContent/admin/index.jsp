<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error-404.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Admin Page</title>

<link rel="stylesheet" type="text/css"
	href="/MediaManagerFinal/css/main.css" />
</head>



<body>
	<header> <img
		style="height: auto; width: auto; max-width: 300px; max-height: 300px;"
		src="/MediaManagerFinal/images/mediaLibrary.png" />
	<h1>Media Library</h1>
	<h2>Administration Tools</h2>
	</header>
	<header2>
	<ul>
		<div class="bar">
			<li><a href="/MediaManagerFinal/index.jsp">Home</a></li>
 			<li><a href="mediaItemSearch.jsp">Media Item Maintenance</a></li> <!--DONE--> 
			<li><a href="purchaseInfoSearch.jsp">Purchase Info Maintenance</a></li>
			<li><a href="mediaTypeSearch.jsp">Media Type Maintenance</a></li>
			<li><a href="genreSearch.jsp">Genre Maintenance</a></li>


		</div>
	</ul>
	<br />
	</header2>

	Welcome Administrator, the heading files now contain administrator
	tools.
	<br />
	<br /> Continue to browse and use the headings to delete, insert, or
	update items in the database.

</body>
</html>