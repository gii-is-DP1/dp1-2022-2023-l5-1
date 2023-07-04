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
            <c:forEach items="${users}" var="user">
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
</minesweeper:layout>
