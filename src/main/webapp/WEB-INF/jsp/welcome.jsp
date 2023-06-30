<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>
<!-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> -->

<minesweeper:layout pageName="home">
    <center>
        <h2><fmt:message key="welcome"/></h2>
        <div class="row">
            <div class="col-md-12">
                <spring:url value="/resources/images/cosa.png" htmlEscape="true" var="bombaImage"/>
                <img class="img-responsive" src="${bombaImage}"/>
            </div>
        </div>
    </center>
</minesweeper:layout>

