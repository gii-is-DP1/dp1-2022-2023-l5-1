<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>

<minesweeper:layout pageName="audits">
	<h2>Audits</h2>

	<table id="auditsTable" style="background-color: #778899;" class="table">
		<thead>
			<tr>
				<th style="width: 150px;">Start Date</th>
				<th style="width: 150px;">End Date</th>
				<th style="width: 150px;">Game level</th>
				<th style="width: 150px;">Player</th>
				<th style="width: 150px;">Game Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${itemsListed}" var="audit">
				<tr>
					<td><c:out value="${audit.startDate}" /></td>
					
					<c:choose>
					<c:when test="${not empty audit.endDate}">
						<td><c:out value="${audit.endDate}" /></td>
					</c:when>
					<c:otherwise>
						<td><c:out value="NOT FINISHED" /></td>
					</c:otherwise>
					</c:choose>
					
					<td><c:out value="${audit.difficulty}" /></td>
					<td><c:out value="${audit.user.username}" /></td>
					<c:choose>
						<c:when test="${audit.inProgress==false && audit.success==true}">
							<td style="font-weight:bold;background-color:black;color:green">Win</td>
						</c:when>
						<c:when test="${audit.inProgress==false && audit.success==false}">
							<td style="font-weight:bold;background-color:black;color:red">Lost</td>
						</c:when>
						<c:when test="${audit.inProgress==true}">
							<td style="font-weight:bold;background-color:black;color:yellow">In Progress</td>
						</c:when>
					</c:choose>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<table border="1" cellpadding="5" cellspacing="5">

			<tr>
			<c:if test="${hasPrevious}">
			<td><a href="/audits?page=${pageNumber - 1}"
					class="btn btn-default">Previous</a></td>
			
			</c:if>
				
				<c:forEach begin="0" end="${totalPages}" var="i">
					<c:choose>
						<c:when test="${i.equals(pageNumber)}">
							<td><b href="/audits?page=${i}">&nbsp ${i} &nbsp</b></td>
						</c:when>
						<c:otherwise>
							<td><a href="/audits?page=${i}">&nbsp ${i} &nbsp</a></td>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				
			<c:if test="${pageNumber != totalPages}">
			<td><a href="/audits?page=${pageNumber + 1}" 
					class="btn btn-default">Next</a></td>
			</c:if>
			
			</tr>
		</table>
</minesweeper:layout>