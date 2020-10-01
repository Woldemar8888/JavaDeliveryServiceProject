<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delivery service</title>
<link rel="stylesheet" href="style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
	<form action="register" id="register-form" method="post">
		<label for="register-name">Name</label>
		<input type="text" name="register-name" id="register-name">
		<label for="register-surname">Surname</label>
		<input type="text" name="register-surname" id="register-surname">
		<label for="phone">Phone</label>
		<input type="text" name="phone" id="phone">	
		<label for="register-login">Login</label>
		<input type="text" name="register-login" id="register-login"> 
		<label for="password">Password</label>
		<input type="register-password" name="register-password" id="register-password">
		<input type="submit" value="Register" id="submit">
	</form>
	
	<form action="login" id="login-form" method="post">
		<label for="login">Login</label>
		<input type="text" name="login" id="login"> 
		<label for="password">Password</label>
		<input type="password" name="password" id="password">
		<input type="submit" value="Sign in" id="submit">
	</form>
	
</body>
</html>