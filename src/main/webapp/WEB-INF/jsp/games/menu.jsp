<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>

<minesweeper:layout pageName="Game menu">
    <center>
    <h2>Game menu:</h2>
    <h3>Select difficulty</h3>
        <form method="get" action="/games/playNewGame" class="form-horizontal" id="select-game-form">
                
            <div class="form-horizontal has-feedback">
                <input type="radio" id="beginner" name="difficulty" value="Beginner" checked /> <label for="beginner">Beginner</label><br>
                <input type="radio" id="intermediate" name="difficulty" value="Intermediate" /> <label for="intermediate">Intermediate</label><br>
                <input type="radio" id="advanced" name="difficulty" value="Advanced" /> <label for="advanced">Advanced</label><br>
                <input type="radio" id="custom" name="difficulty" value="Custom"/> <label for="custom">Custom</label><br> <br>

                <div id="form" style="display: none">
                    <form:form modelAttribute="board" class="form-horizontal">
                        <div class="form-horizontal has-feedback">
                            <label>Columns: </label>
                            <form:input path="columns" value="8" type="number" min="8" max="30" size="5" name="columns" />
                            &nbsp;&nbsp; 
                            <label>Rows: </label>
                            <form:input path="rows" value="8" type="number" min="8" max="16" size="5" name="rows" />
                            &nbsp;&nbsp; 
                            <label>Mines: </label>
                            <form:input path="minesNumber" value="10" type="number" min="10" max="99" size="5" name="mines" />
                            <from:hidden path="id"/>
                            <form:hidden path= "user"/>
                        </div>
                    </form:form>
                </div>
                <button class="btn btn-default" type="submit">Create game</button>
            </div>
        </form>
    </center>
</minesweeper:layout>

<script>
    const custom = document.querySelector("input[value='Custom']");
    var form = document.getElementById('form');
    custom.onclick = function () {
		form.style.display = "block";
	};
</script>