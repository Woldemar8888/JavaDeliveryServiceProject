<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Служба доставки - личный кабинет</title>
<link rel="stylesheet" href="style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
	
	<c:if test="${not empty sessionScope.local}">
        <fmt:setLocale value="${sessionScope.local}"/>
    </c:if>

    <fmt:setBundle basename="resources.localization.app" var="loc"/>
	
	<fmt:message bundle="${loc}" key="login.link.to_main" var="to_main"/>
	<fmt:message bundle="${loc}" key="account.title" var="title"/>
    <fmt:message bundle="${loc}" key="register.field.name" var="name"/>
    <fmt:message bundle="${loc}" key="register.field.surname" var="surname"/>

    <fmt:message bundle="${loc}" key="register.field.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="register.field.login" var="login"/>
    <fmt:message bundle="${loc}" key="register.field.password" var="password"/>
    <fmt:message bundle="${loc}" key="userdata.link.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="userdata.link.save" var="save"/>
    <fmt:message bundle="${loc}" key="userdata.link.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="userdata.modal.question" var="sure"/>
    <fmt:message bundle="${loc}" key="userdata.modal.answer.yes" var="yes"/>
    <fmt:message bundle="${loc}" key="userdata.modal.answer.no" var="no"/>
    
	
</head>
<body>
	
	<c:if test="${not  sessionScope.isDeleteUserMode}">
        <a href="controller?command=go_to_main_page">${to_main}</a>
		<div class="user-page">
			<h3>${title}</h3>
			<c:if test="${sessionScope.isEditMode}">
				<form action="controller?command=save_userdata_changes" id="register-form" method="post">
					<label for="register-name">${name}</label>
					<input type="text" name="register-name" id="register-name"  value="${user.name}">
					<label for="register-surname">${surname}</label>
					<input type="text" name="register-surname" id="register-surname" value="${user.surname}">
					<label for="phone">${phone}</label>
					<input type="text" name="register-phone" id="register-phone" value="${user.phone}"> 
					<label for="password">${password}</label>
					<input type="text" name="register-password" id="register-password" value="${user.password}">
					<input type="submit" value="${save}" id="submit">
				</form>
	    	</c:if>
	    	
	    	<c:if test="${not sessionScope.isEditMode}">
				<p>${name}: ${user.name} </p>
				<p>${surname}: ${user.surname}</p>
				<p>${phone}: ${user.phone} </p>
				<p>${login}: ${user.login} </p>
				<p>${password}: ${user.password} </p>
				<div class="link-block">
					<a href="controller?command=edit_userdata">${edit}</a>
	        		<a href="controller?command=show_delete_profile_modal">${delete}</a>
				</div>
	        	
	    	</c:if>
		</div>
    </c:if>
    <c:if test="${sessionScope.isDeleteUserMode}">
        <div id="delete-user-modal">
        	<h4>${delete}.<br/> ${sure}</h4>
        	<div class="link-block">
        		<a href="controller?command=delete_profile">${yes}</a>
        		<a href="controller?command=cancel_delete_profile">${no}</a>
        	</div>
        </div>
    </c:if>
	
	
	
</body>
</html>