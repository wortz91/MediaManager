<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page isErrorPage="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>404 Error Page</title>

	<link rel="stylesheet" type="text/css" href="/MediaManagerFinal/css/main.css" />
</head>

<header> <img
	style="height: auto; width: auto; max-width: 300px; max-height: 300px;"
	src="/MediaManagerFinal/images/mediaLibrary.png">
<h1>404</h1>
<h2>Page Not Found</h2>
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
We apologize but the page you are looking for does not exist. please click the "Home" button at the top of the page to be redirected to the main page.
	<br>
    <button onclick="history.back()">Back to Previous Page</button>
    <h1>404 Page Not Found.</h1><br />
    <p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
    <p><b>Request URI:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</p><br />
</body>
</html>