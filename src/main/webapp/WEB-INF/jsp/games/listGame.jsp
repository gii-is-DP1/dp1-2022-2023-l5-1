<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>

<minesweeper:layout pageName="games">
    <c:choose>
        <c:when test="${active}">
            <h2 style="margin-left: 50%;">Active Games List</h2>
        </c:when>
        <c:otherwise>
            <h2 style="margin-left: 50%;">Finished Games List</h2>
        </c:otherwise>
    </c:choose>

    <table id="gamesTable" style="background-color: rgba(250, 153, 25, 0.562);" class="table">
        <thead>
        <tr>
            <th>Username</th>
            <th>Difficulty</th>
            <c:if test="${!active}">
                <th>Outcome</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                <td>
                    <c:out value="${game.user.username}"/>
                </td>
                <td>
                    <c:out value="${game.difficulty}"/>
                </td>
                <c:if test="${!active}">
                    <td>
                        <c:choose>
                            <c:when test="${game.success}">
                                Victory
                            </c:when>
                            <c:otherwise>
                                Defeat
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</minesweeper:layout>
