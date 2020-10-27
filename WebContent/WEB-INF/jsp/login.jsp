<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Служба доставки - регистация</title>
<link rel="stylesheet" href="style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
	<form action="controller?command=authorization" id="login-form" method="post">
		<label for="login">Login</label>
		<input type="text" name="login" id="login"> 
		<label for="password">Password</label>
		<input type="password" name="password" id="password">
		<input type="submit" value="Sign in" id="submit">
		<a href="controller?command=go_to_registration_page">Register</a>
	</form>
</body>
</html>