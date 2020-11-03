<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Служба доставки - регистрация</title>
<link rel="stylesheet" href="style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
	
<c:if test="${not empty sessionScope.local}">
        <fmt:setLocale value="${sessionScope.local}"/></c:if>

    <fmt:setBundle basename="resources.localization.app" var="loc"/>

	
	<fmt:message bundle="${loc}" key="login.link.to_main" var="to_main"/>
    <fmt:message bundle="${loc}" key="register.field.name" var="name"/>
    <fmt:message bundle="${loc}" key="register.field.surname" var="surname"/>

    <fmt:message bundle="${loc}" key="register.field.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="register.field.login" var="login"/>
    <fmt:message bundle="${loc}" key="register.field.password" var="password"/>
    <fmt:message bundle="${loc}" key="register.button.register" var="register"/>
    <fmt:message bundle="${loc}" key="register.link.login" var="message"/>
	
</head>
<body>
	<a href="controller?command=go_to_main_page">${to_main}</a>
	<form action="controller?command=registration" id="register-form" method="post">
		<label for="register-name">${name}</label>
		<input type="text" name="register-name" id="register-name">
		<label for="register-surname">${surname}</label>
		<input type="text" name="register-surname" id="register-surname">
		<label for="phone">${phone}</label>
		<input type="text" name="register-phone" id="register-phone">	
		<label for="register-login">${login}</label>
		<input type="text" name="register-login" id="register-login"> 
		<label for="password">${password}</label>
		<input type="text" name="register-password" id="register-password">
		<input type="submit" value="${register}" id="submit">
		<a href="controller?command=go_to_authorization_page">${message}</a>
	</form>
</body>
</html>