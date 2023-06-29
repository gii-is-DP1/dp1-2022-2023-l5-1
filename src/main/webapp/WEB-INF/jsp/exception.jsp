<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">
    <center>

        <spring:url value="/resources/images/bombatriste.jpg" var="bombImage"/>
        <img src="${bombImage}"/>

        <h2>Something happened...</h2>

        <p>${exception.message}</p>
    </center>
</petclinic:layout>
