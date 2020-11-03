<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Служба доставки - регистация</title>
<link rel="stylesheet" href="style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
	
<c:if test="${not empty sessionScope.local}">
        <fmt:setLocale value="${sessionScope.local}"/>
</c:if>

    <fmt:setBundle basename="resources.localization.app" var="loc"/>
	
	<fmt:message bundle="${loc}" key="login.link.to_main" var="to_main"/>
    <fmt:message bundle="${loc}" key="login.field.login" var="login"/>
    <fmt:message bundle="${loc}" key="login.field.password" var="password"/>

    <fmt:message bundle="${loc}" key="login.button.sign_in" var="sign_in"/>
    <fmt:message bundle="${loc}" key="login.link.register" var="register"/>
    
	
</head>
<body>
	<a href="controller?command=go_to_main_page">${to_main}</a>
	<form action="controller?command=authorization" id="login-form" method="post">
		<label for="login">${login}</label>
		<input type="text" name="login" id="login"> 
		<label for="password">${password}</label>
		<input type="password" name="password" id="password">
		<input type="submit" value="${sign_in}" id="submit">
		<a href="controller?command=go_to_registration_page">${register}</a>
	</form>
</body>
</html>