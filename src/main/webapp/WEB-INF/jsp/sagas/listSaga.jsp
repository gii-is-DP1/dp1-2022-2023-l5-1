<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>

<minesweeper:layout pageName="sagas">
    <h2 style="margin-left: 50%;">Saga List</h2>

    <table id="sagasTable" style="background-color: rgba(94, 176, 253, 0.562);" class="table">
        <thead>
        <tr>
            <th>Name</th>     
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sagas}" var="saga">
            <tr>
                <td>
                    <c:out value="${saga.name}"/>
                </td>
                <td>
                    <a href="/sagas/update?sagaId=${saga.id}" class="btn btn-primary" role="button">Edit</a>
            
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</minesweeper:layout>
