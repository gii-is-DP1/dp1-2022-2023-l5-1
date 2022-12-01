<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">

    <div class="row">
<<<<<<< HEAD
    <h2> Project ${title}</h2>
    <p><h2> Group ${group}</h2></p>
    <p><ul>
        <c:forEach items="${persons}" var="person">
            <li>${person.firstName} ${person.lastName}</li>
        </c:forEach>
    </ul></p>
    </div>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}"/>
=======
        <div>
            <a class="btn btn-default" href="/games/newGame">Crear Partida</a>
            <a class="btn btn-default" href="/games/joinGame">Unirse a una Partida</a>
>>>>>>> develop
        </div>

    </div>
</petclinic:layout>
