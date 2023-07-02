<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>

<minesweeper:layout pageName="error">
    <center>

        <spring:url value="/resources/images/bombatriste1.png" var="bombImage"/>
        <img src="${bombImage}"/>

        <h2>Something happened...</h2>

        <p>${exception.message}</p>
    </center>
</minesweeper:layout>
