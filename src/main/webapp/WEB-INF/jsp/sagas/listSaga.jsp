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
        <c:forEach items="${itemsListed}" var="saga">
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
    <table border="1" cellpadding="5" cellspacing="5">

        <tr>
        <c:if test="${hasPrevious}">
        <td><a href="/sagas?page=${pageNumber - 1}"
                class="btn btn-default">Previous</a></td>
        
        </c:if>
            
            <c:forEach begin="0" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${i.equals(pageNumber)}">
                        <td><b href="/sagas?page=${i}">&nbsp ${i} &nbsp</b></td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="/sagas?page=${i}">&nbsp ${i} &nbsp</a></td>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
            
        <c:if test="${pageNumber != totalPages}">
        <td><a href="/sagas?page=${pageNumber + 1}" 
                class="btn btn-default">Next</a></td>
        </c:if>
        
        </tr>
    </table>
</minesweeper:layout>
