<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <h2 style="margin-left: 50%;">User List</h2>

    <table id="usersTable" style="background-color: rgba(94, 176, 253, 0.562);" class="table">
        <thead>
        <tr>
            <th>Username</th>
            <th>Rank</th>
            <th>Update User</th>
            <th>Delete User</th>
            <th>Delete User Stats</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    <c:out value="${user.username}"/>
                </td>
                <td>
                    RANGO
                </td>
                <td>
                    <a href="users/update/${user.username}" class="btn btn-primary" role="button">Edit</a>
                </td>
                <td>
                    <a href="users/delete/${user.username}"  class="btn btn-danger" role="button">Delete</a>
                </td>
                <td>
                    BOTON
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
