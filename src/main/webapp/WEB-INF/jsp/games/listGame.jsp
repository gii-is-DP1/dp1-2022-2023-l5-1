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
        <c:forEach items="${itemsListed}" var="game">
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
    <table border="1" cellpadding="5" cellspacing="5">

        <tr>
        <c:if test="${hasPrevious}">
            <c:choose>
                <c:when test="${active}">
                    <td><a href="/games/activeGames?page=${pageNumber - 1}"
                        class="btn btn-default">Previous</a></td>
                </c:when>
                <c:otherwise>
                    <td><a href="/games/finishGames?page=${pageNumber - 1}"
                        class="btn btn-default">Previous</a></td>
                </c:otherwise>
            </c:choose>
        
        </c:if>
            
            <c:forEach begin="0" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${i.equals(pageNumber)}">
                        <td><b>&nbsp ${i} &nbsp</b></td>
                    </c:when>
                    <c:otherwise>
                        <td><a>&nbsp ${i} &nbsp</a></td>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
            
        <c:if test="${pageNumber != totalPages}">
            <c:choose>
                <c:when test="${active}">
                    <td><a href="/games/activeGames?page=${pageNumber + 1}"
                        class="btn btn-default">Next</a></td>
                </c:when>
                <c:otherwise>
                    <td><a href="/games/finishGames?page=${pageNumber + 1}"
                        class="btn btn-default">Next</a></td>
                </c:otherwise>
            </c:choose>
        </c:if>
        
        </tr>
    </table>
</minesweeper:layout>
