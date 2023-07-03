<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>
<minesweeper:layout pageName="User profile">
    <h1>${user.username}'s profile:</h1>
    <h3>Biography:</h3>
    <m>${user.biography}</m>
    <h3>Date of birth:</h3>
    <m>${user.birthDate}</m>
    <h3>Location:</h3>
    <m>${user.location}</m>
    <h3>Favourite genre:</h3>
    <m>${user.genre.name}</m>
    <h3>Favourite saga:</h3>
    <m>${user.saga.name}</m>
    <h3>Favourite platform:</h3>
    <m>${user.platform.name}</m>
    <h3>Profile picture:</h3>
    <img src="${user.profilePicture}" alt="Image">
</minesweeper:layout>