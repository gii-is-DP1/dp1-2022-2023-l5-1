<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>

<minesweeper:layout pageName="users">

	<<h2>
		<c:if test="${user['new']}">New </c:if>
		User
	</h2>
	
	<form:form modelAttribute="user" class="form-horizontal"
		id="add-user-form">
		<div class="form-group has-feedback">
			<minesweeper:inputField label="First Name" name="firstName" />
			<minesweeper:inputField label="Last Name" name="lastName" />
			<c:choose>
				<c:when test="${user['new']}">
					<minesweeper:inputField label="Username" name="user.username" />
				</c:when>
				<c:otherwise>
					<form:hidden path="user.username" />
				</c:otherwise>
			</c:choose>

			<minesweeper:inputField label="Password" name="user.password" />
			<minesweeper:inputField label="City" name="city" />
			<minesweeper:inputField label="Address" name="address" />
			<minesweeper:inputField label="Telephone" name="telephone" />
			<minesweeper:inputField label="Email" name="email" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${user['new']}">
						<button class="btn btn-default" type="submit">Add user</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update user</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>>
</minesweeper:layout>