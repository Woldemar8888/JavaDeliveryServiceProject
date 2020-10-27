<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Служба доставки - главная</title>

<link rel="stylesheet"  type="text/css" href="style.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
	<div class="container">
		<header>
			<div id="logo">
				L O G O
			</div>
			<nav>
				<ul>
					<li><a href="controller?command=go_to_authorization_page">LOGIN</a><li>
					<li><a href="controller?command=go_to_registration_page">REGISTRATION</a><li>
				</ul>
			</nav>
		</header>
	</div>
	<footer>
			All rights reserved
	</footer>
	
	
</body>
</html>