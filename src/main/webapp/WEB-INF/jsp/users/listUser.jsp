<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>

<minesweeper:layout pageName="users">
    <h2 style="margin-left: 50%;">User profiles</h2>
    <table id="usersTable" style="background-color: rgba(94, 176, 253, 0.562);" class="table">
        <thead>
            <tr>
                <th>Username</th>
                <th>User Profiles</th>
                <c:if test="${admin}">
                    <th>Update User</th>
                    <th>Delete User</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${itemsListed}" var="user">
                <tr>
                    <td>
                        <c:out value="${user.username}"/>
                        </td>
                    <td>
                        <a href="users/profile/${user.username}" class="btn btn-primary" role="button">Profile</a>
                    </td>
                    <c:if test="${admin}">
                        <td>
                            <a href="users/update/${user.username}" class="btn btn-primary" role="button">Edit</a>
                        </td>
                        <td>
                            <a href="users/delete/${user.username}"  class="btn btn-danger" role="button">Delete</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table border="1" cellpadding="5" cellspacing="5">

        <tr>
        <c:if test="${hasPrevious}">
        <td><a href="/users?page=${pageNumber - 1}"
                class="btn btn-default">Previous</a></td>
        
        </c:if>
            
            <c:forEach begin="0" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${i.equals(pageNumber)}">
                        <td><b href="/users?page=${i}">&nbsp ${i} &nbsp</b></td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="/users?page=${i}">&nbsp ${i} &nbsp</a></td>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
            
        <c:if test="${pageNumber != totalPages}">
        <td><a href="/users?page=${pageNumber + 1}" 
                class="btn btn-default">Next</a></td>
        </c:if>
        
        </tr>
    </table>
</minesweeper:layout>
