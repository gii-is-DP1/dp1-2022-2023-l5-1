<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<% application.setAttribute("succes", "Fail"); %>

<petclinic:layout pageName="Play game">
    <style>
        body{
            font-family: Arial, Helvetica, sans-serif;
            font-weight: bold;
            text-align: center;
        }
    </style>

    <h1>Game Over</h1>
    <h2>You win</h2>
    <a href="/games">
        <button>Restart Game</button>
    </a>
    <a href="/">
        <button>Exit</button>
    </a>
</petclinic:layout>