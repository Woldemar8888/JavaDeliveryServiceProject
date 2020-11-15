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
    <fmt:message bundle="${loc}" key="userdata.link.show_orders" var="show_orders"/>
    <fmt:message bundle="${loc}" key="userdata.link.hide_orders" var="hide_orders"/>
    <fmt:message bundle="${loc}" key="userdata.link.add_order" var="add_order"/>
    <fmt:message bundle="${loc}" key="userdata.modal.question" var="sure"/>
    <fmt:message bundle="${loc}" key="userdata.modal.answer.yes" var="yes"/>
    <fmt:message bundle="${loc}" key="userdata.modal.answer.no" var="no"/>
    <fmt:message bundle="${loc}" key="userdata.orders.order_id" var="order_id"/>
    <fmt:message bundle="${loc}" key="userdata.orders.status" var="status"/>
    <fmt:message bundle="${loc}" key="userdata.orders.date_in" var="date_in"/>
    <fmt:message bundle="${loc}" key="userdata.orders.town" var="town"/>
    <fmt:message bundle="${loc}" key="userdata.order.goods.name" var="product_name"/>
    <fmt:message bundle="${loc}" key="general.link.details" var="details"/>
    <fmt:message bundle="${loc}" key="general.link.hide" var="hide"/>
    <fmt:message bundle="${loc}" key="general.link.show" var="show"/>
    <fmt:message bundle="${loc}" key="general.count" var="count"/>
    <fmt:message bundle="${loc}" key="general.weight" var="weight"/>
    <fmt:message bundle="${loc}" key="general.total_weight" var="total_weight"/>
    <fmt:message bundle="${loc}" key="general.volume" var="volume"/>
    <fmt:message bundle="${loc}" key="general.total_volume" var="total_volume"/>
   
	
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
	        		<div class="separator"></div>
	        		<c:if test="${sessionScope.isShowOrdersMode}">
       					<a href="controller?command=hide_user_orders">${hide_orders}</a>
    				</c:if>
    				<c:if test="${not sessionScope.isShowOrdersMode}">
       					<a href="controller?command=show_user_orders">${show_orders}</a>
    				</c:if>
	        		<a href="controller?command=add_order">${add_order}</a>
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
    <c:if test="${sessionScope.isShowOrdersMode}">
        <div>
        	<table id="user_orders">
        		<tr>
        			<th>${order_id}</th>
        			<th>${status}</th>
        			<th>${date_in}</th>
        			<th>${town}</th>
        			<th>${details}</th>
        		<tr>
        		<c:forEach var="order" items="${sessionScope.userOrderList}">
    				<tr>
    				
    					<td>${order.order_id}</td>
    					
    					<td>
    						<c:if test="${user.role_id eq 3 or user.role_id eq 2 or  user.role_id eq 4}">
    							<form action="controller?command=save_orderdata_changes" method="post">
    								<input type="hidden" name="order_id" value="${order.order_id}"/>
    								<select id="${order.order_id}" name="order_status" defaultValue="${order.status}">
    									<option value="не подтвержден" <c:if test="${order.status eq \"не подтвержден\"}">selected</c:if>>Не подтвержден</option>
    									<option value="принят" <c:if test="${order.status eq \"принят\"}">selected</c:if>>Принят</option>
    									<option value="выполнен" <c:if test="${order.status eq \"выполнен\"}">selected</c:if> >Выполнен</option>
    							</select>
    								<input type="submit" value="${save}"/>
    							</form>
    						</c:if>
    						
    						<c:if test="${user.role_id eq 1 }">
    							${order.status}
    						</c:if>
    					</td>
    					
    					<td>${order.date_in}</td>
    					
    					<td>${order.town}</td>
    					
    					<td>
    						<c:if test="${not sessionScope.isShowOrderDelailsMode}">
    							<form action="controller?command=show_order_details" method="post">
    								<input type="hidden" name="order_id" value="${order.order_id}"/>
    								<input type="submit" value="${show}"/>
    							</form>
    						</c:if>
    						<c:if test="${sessionScope.isShowOrderDelailsMode}">
    							<c:if test = "${sessionScope.currentOrderId eq order.order_id}">
    								<form action="controller?command=hide_order_details" method="post">
    									<input type="hidden" name="order_id" value="${order.order_id}"/>
    									<input type="submit" value="${hide}"/>
    								</form>
    							</c:if>
    							
    						</c:if>
    					</td>
    				</tr>
				</c:forEach>
        	</table>
        </div>
    </c:if>
    <c:if test="${sessionScope.isShowOrderDelailsMode }">
    	<div class="separator"></div>
    	<div>
    		<table id="order_goods">
    			<tr>
        			<th>${product_name}</th>
        			<th>${count}</th>
        			<th>${weight}</th>
        			<th>${total_weight}</th>
        			<th>${volume}</th>
        			<th>${total_volume}</th>
        		<tr>
        		<c:forEach var="product" items="${sessionScope.userOrderProductList}">
        			<tr>
        				<td>${product.name}</td>
        				<td>${product.count}</td>
        				<td>${product.weight}</td>
        				<td>${product.weight*product.count}</td>
        				<td>${product.volume}</td>
        				<td>${product.volume*product.count}</td>
        			</tr>
        		</c:forEach>
    		</table>
    	</div>
	</c:if>
	
	
</body>
</html>