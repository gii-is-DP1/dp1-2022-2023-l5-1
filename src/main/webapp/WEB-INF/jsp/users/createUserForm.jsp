<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>


<style>
    .text {
        text-align: right;
        margin-bottom: 0;
        padding-top: 7px;
        position: relative;
        min-height: 1px;
        padding-left: 15px;
        padding-right: 15px;
        float: left;
    }
    .checkbox {
    width: 100%;
    height: 25px;
    align-items: left;
}
</style>

<minesweeper:layout pageName="users">
    <h2>
        <c:if test="${user['new']}">New </c:if> User
    </h2>
    <form:form modelAttribute="user" class="form-horizontal" id="add-user-form">
        <div class="form-group has-feedback">
            <minesweeper:inputField label="Username" name="username"/>
            <minesweeper:inputField label="Name" name="name"/>
            <minesweeper:inputField label="Password" name="password"/>
            <input type="hidden" name="version" value="${user.version}"/>
            <spring:bind path="hardcoregamer">
                <div >
                    <label class="col-sm-2 control-label">Hard-Core</label>
                    <div class="controls">
                        <form:checkbox path="hardcoregamer" class="checkbox"/>
                        <span class="help-inline">${status.errorMessage}</span>
                    </div>
                </div>
            </spring:bind>
            <h3>User profile information</h3>
            <xs>The only mandatory information is the location</xs>
            <minesweeper:inputField label="Biography" name="biography"/>
            <minesweeper:inputField label="Date of birth" name="birthDate" placeholder="dd/MM/yyyy"/>
            <minesweeper:inputField label="Location" name="location"/>
            <div class="control-group1">
                <minesweeper:selectField name="genre" label="Favourite genre" names="${genres}" size="3" itemValue="id" itemLabel="name"/>
            </div>
            <div class="control-group">
                <minesweeper:selectField name="platform" label="Favourite platform" names="${platforms}" size="3" itemValue="id" itemLabel="name"/>
            </div>
            <div class="control-group">
                 <minesweeper:selectField name="saga" label="Favourite saga" names="${sagas}" size="3" itemValue="id" itemLabel="name"/>
             </div>
            <minesweeper:inputField label="Profile picture URL" name="profilePicture"/>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${user['new']}">
                        <button class="btn btn-default" type="submit">Add new user</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update User</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</minesweeper:layout>

<script>
    var newUser = "${user['new']}";

    function makeReadOnly(id) {
        document.getElementById(id).readOnly = true;
    }

    window.onload = function() {
        if(newUser == "false") {
            makeReadOnly('username');
        }
    }
</script>