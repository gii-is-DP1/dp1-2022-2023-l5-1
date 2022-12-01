<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">

    <div class="row">
        <div>
            <a class="btn btn-default" href="/games/newGame">Crear Partida</a>
            <a class="btn btn-default" href="/games/joinGame">Unirse a una Partida</a>
        </div>

    </div>
</petclinic:layout>
