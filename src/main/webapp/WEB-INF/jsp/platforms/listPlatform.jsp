<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>

<minesweeper:layout pageName="platforms">
    <h2 style="margin-left: 50%;">Platform List</h2>

    <table id="platformsTable" style="background-color: rgba(94, 176, 253, 0.562);" class="table">
        <thead>
        <tr>
            <th>Name</th>     
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${platforms}" var="platform">
            <tr>
                <td>
                    <c:out value="${platform.name}"/>
                </td>
                <td>
                    <a href="/platforms/update?platformId=${platform.id}" class="btn btn-primary" role="button">Edit</a>
            
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</minesweeper:layout>
