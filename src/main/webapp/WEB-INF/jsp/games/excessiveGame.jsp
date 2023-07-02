<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>
<% application.setAttribute("succes", "Fail"); %>

<minesweeper:layout pageName="Play game">
    <style>
        body{
            font-family: Arial, Helvetica, sans-serif;
            font-weight: bold;
            text-align: center;
        }
    </style>

    <h1>You have played too much today</h1>
    <h2>It's time to take a break from Mine Sweeper</h2>
    <a href="/">
        <button>Exit</button>
    </a>
</minesweeper:layout>