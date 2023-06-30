<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>

<minesweeper:layout pageName="genres">
    <h2 style="margin-left: 50%;">Genre List</h2>

    <table id="genresTable" style="background-color: rgba(94, 176, 253, 0.562);" class="table">
        <thead>
        <tr>
            <th>Name</th>     
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${genres}" var="genre">
            <tr>
                <td>
                    <c:out value="${genre.name}"/>
                </td>
                <td>
                    <a href="/genres/update?genreId=${genre.id}" class="btn btn-primary" role="button">Edit</a>
            
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</minesweeper:layout>
