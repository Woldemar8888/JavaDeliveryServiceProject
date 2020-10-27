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
	<form action="controller?command=registration" id="register-form" method="post">
		<label for="register-name">Name</label>
		<input type="text" name="register-name" id="register-name">
		<label for="register-surname">Surname</label>
		<input type="text" name="register-surname" id="register-surname">
		<label for="phone">Phone</label>
		<input type="text" name="register-phone" id="register-phone">	
		<label for="register-login">Login</label>
		<input type="text" name="register-login" id="register-login"> 
		<label for="password">Password</label>
		<input type="text" name="register-password" id="register-password">
		<input type="submit" value="Register" id="submit">
		<a href="controller?command=go_to_authorization_page">already have an account - login</a>
	</form>
</body>
</html>