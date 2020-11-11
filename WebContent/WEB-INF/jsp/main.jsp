<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Служба доставки - главная</title>

<link rel="stylesheet"  type="text/css" href="style.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/script.js"></script>
	
	<c:if test="${not empty sessionScope.local}">
        <fmt:setLocale value="${sessionScope.local}"/>
    </c:if>

    <fmt:setBundle basename="resources.localization.app" var="loc"/>

    <fmt:message bundle="${loc}" key="header.button.ru" var="button_ru"/>
    <fmt:message bundle="${loc}" key="header.button.en" var="button_en"/>

    <fmt:message bundle="${loc}" key="hello" var="hello"/>
    <fmt:message bundle="${loc}" key="log_in" var="log_in"/>
    <fmt:message bundle="${loc}" key="log_out" var="log_out"/>
    <fmt:message bundle="${loc}" key="registration" var="registration"/>
    <fmt:message bundle="${loc}" key="copyright" var="copyright"/>
	
</head>
<body>	
	<header>
		<div class="container">
			<div id="logo">
				L O G O
			</div>
			<div id="localization">
	            <form action="controller?command=change_locale" method="post">
	                <input type="hidden" name="local" value="ru"/>
	                <button type="submit">${button_ru}</button>
	            </form>
	            <form action="controller?command=change_locale" method="post">
	                 <input type="hidden" name="local" value="en"/>
	                 <button type="submit">${button_en}</button>
	            </form>
	        </div>
			<nav>
				<ul>
					<c:if test="${empty sessionScope.user}">
	        			<li><a href="controller?command=go_to_authorization_page">${log_in}</a><li>
	        			<li><a href="controller?command=go_to_registration_page">${registration}</a><li>
	    			</c:if>
	    				<c:if test="${not empty sessionScope.user}">
	        				<li><a href="controller?command=logout">${log_out}</a><li>
	    			</c:if>
				</ul>
			</nav>
			<c:if test="${not empty sessionScope.user}">
	        	<div id="helloUser">
					${hello} <a href="controller?command=go_to_user_page"
									class="user-page-link"
									title="to user page">${user.name}</a>
				</div>
	    	</c:if>
		</div>	
	</header>
	<footer>
			${copyright}
	</footer>
	
	
</body>
</html>