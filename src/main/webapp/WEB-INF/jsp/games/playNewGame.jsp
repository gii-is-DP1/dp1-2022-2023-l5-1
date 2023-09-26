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
        :root{
            --width: 800px;
            --height: 800px;
        }
        #board {
            width: var(--width);
            height: var(--height);
            border: 10px solid darkgray;
            background-color: lightgray;

            margin: 0 auto;
            display: flex;
            flex-wrap: wrap;
        }

        #board div{
            box-sizing: border-box;
            width: 50px;
            height: 50px;
            border: 1px solid gray;

            font-size: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        #flag-button {
            width: 100px;
            height: 50px;
            font-size: 20px;
            background-color: lightgray;
            border: none;
        }
        .square-clicked {
            background-color: darkgrey;
        }

        .x1 {
            color: blue;
        }

        .x2 {
            color: green;
        }

        .x3 {
            color: red;
        }

        .x4 {
            color: navy;
        }

        .x5 {
            color: brown;
        }

        .x6 {
            color: teal;
        }

        .x7 {
            color: black;
        }

        .x8 {
            color: gray;
        }
    </style>

    <h1>Mines: <c:out value="${board.minesNumber}"></c:out></h1>
    <h1> Flags: <div id="flags" style="display: contents;"></div></h1>
    <div id="board"></div>
    <button id="flag-button"><span class="glyphicon glyphicon-flag" aria-hidden="true"></span></button>
    <button id="restart-button">Restart Game</button>
    <button id="finish-button">Finish Game</button>   
</minesweeper:layout>

<script>
    // Select the root element of the document
    var root=document.querySelector(":root");

    // Get some data variables from the game context like totalrows, id,column...
    var totalrows="${board.rows}";
    var id="${game.getId()}";
    var totalcolumns="${board.columns}";
    var minesPosition = "${mines}";
    var numberOfMines = "${mines.size()}";
    var difficulty = "${difficulty}";

    //Variable state
    var success = false;
    var width = (totalcolumns)*50+20;
    var height = (totalrows)*50+20;

    //Set custom properties
    root.style.setProperty("--width", parseInt(width)+"px");
    root.style.setProperty("--height", parseInt(height)+"px");
    
    //Varibles related to the game state
    var squareClicked = 0;
    var gameOver = false;
    var flaggingAllowed = false;
    var numberOfFlag = 0;
    var error = "${error}";
    var board=[];
    var hardcore = "${hardcore}";

    //Update the html content
    document.getElementById("flags").innerHTML = numberOfMines;

    window.onload = async function(){
        // Check if an error occurred, if it occurs show the next alert.
        if(error == "true") {
            alert("Mines have been reduced to 90% of the maximum allowed.");
        }

        // If it is not a hardcore game, finish the casual game.
        if(hardcore == "false") {
            checkGameDurationAndFinish();
        }

        initializeGameBoard();

        // Add restart button and a finish button
        document.getElementById("restart-button").addEventListener("click", restartGame);
        document.getElementById("finish-button").addEventListener("click", finishGame);
    }

    function endGame() {
        var link = '/games/endGame?id='+ id + '&success=' + success +'&audit_id=${audit.id}';
        location.replace(link);
    }

    // If the game duration > recommended the game finish
    async function finishCasualGame() { 
        var delay1 = 60000;
        if(difficulty == "INTERMEDIATE") {
            delay = 180000;
        } else if(difficulty == "ADVANCED" || difficulty == "CUSTOM") {
            delay = 300000;
        }
        await delay(delay1);
        showMines();
        alert("You have spent too much time in the game, the game will finish now.");
        endGame();
    }


    function initializeGameBoard() {
        document.getElementById("flag-button").addEventListener("click", putFlag);
        let rows = 0;
        while (rows < totalrows) {
            let row = [];
            let column = 0;
            while (column < totalcolumns) {
                let square = document.createElement("div");
                square.id = rows.toString() + "-" + column.toString();
                square.addEventListener("click", clickSquare);
                document.getElementById("board").append(square);
                row.push(square);
                column++;
            }
            board.push(row);
            rows++;
        }

        console.log(board);
        return;
    }

    //TO SET FLAG
    function putFlag() {
        const flagButton = document.getElementById("flag-button");
        flaggingAllowed = !flaggingAllowed;
        flagButton.style.backgroundColor = flaggingAllowed ? "darkgray" : "lightgray";
    }

    //Sleep time
    function delay(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }


    //When clic the title
    async function clickSquare() {

        let square = this;

        // Check if the game is over, square is already clicked, or flags are not enabled and there is text on the square
        if (gameOver || square.classList.contains("square-clicked") || (square.innerText != "" && !flaggingAllowed)) {
            return;
        }

        // Handle flag placement or removal when flags are enabled
        if (flaggingAllowed) {
            if (square.innerText == "") {
                square.innerText = "x"; 
                numberOfFlag++;
            } else if (square.innerText == "x") {
                square.innerText = ""; // Remove the flag
                numberOfFlag--;
            }
            //Update the flag counter
            document.getElementById("flags").innerHTML = numberOfMines - numberOfFlag;
            return;
        }

        // Check if the square contains a mine
        if (minesPosition.includes(square.id)) {
            showMines();
            gameOver = true;
            await delay(500);
            alert("Game Over\nYou lose");
            endGame();
            return;
        }

        // Split the square's ID to get its row and column coordinates
        let coords = square.id.split("-");
        let rows = parseInt(coords[0]);
        let column = parseInt(coords[1]);

        revealMine(rows, column);
    }


    //Reveal mine if player click on a square that contains mine
    function showMines() {
        board.forEach((row, rows) => {
            row.forEach((square, column) => {
                if (minesPosition.includes(square.id) && (square.innerText == "x" || success)) {
                    square.style.backgroundColor = "green";
                } else if (minesPosition.includes(square.id) && square.innerText != "x") {
                    square.innerText = "x";
                    square.style.backgroundColor = "red";
                }
            });
        });
        return;
    }

    async function revealMine(rows, column) {
        if (isOutOfBounds(rows, column) || isSquareClicked(rows, column)) {
            return;
        }

        board[rows][column].classList.add("square-clicked");
        squareClicked += 1;

        const minesFound = calculateAdjacentMines(rows, column);

        if (minesFound > 0) {
            revealSquareNumber(rows, column, minesFound);
        } else {
            revealAdjacentSquare(rows, column);
        }

        if (squareClicked === totalrows * totalcolumns - numberOfMines) {
            gameOver = true;
            success = true;
            showMines();
            await delay(500);
            alert("Game Over\n You win");
            endGame();
        }
    }

    function isOutOfBounds(rows, column) {
        return rows < 0 || rows >= totalrows || column < 0 || column >= totalcolumns;
    }

    function isSquareClicked(rows, column) {
        return board[rows][column].classList.contains("square-clicked");
    }

    function calculateAdjacentMines(rows, column) {
        let minesFound = 0;
        const offsets = [-1, 0, 1];

        for (const dr of offsets) {
            for (const dc of offsets) {
                if (dr === 0 && dc === 0) continue;
                minesFound += checkSquare(rows + dr, column + dc);
            }
        }

        return minesFound;
    }

    function revealSquareNumber(rows, column, number) {
        board[rows][column].innerText = number;
        board[rows][column].classList.add("x" + number.toString());
    }

    function revealAdjacentSquare(rows, column) {
        const offsets = [-1, 0, 1];

        for (const dr of offsets) {
            for (const dc of offsets) {
                if (dr === 0 && dc === 0) continue;
                revealMine(rows + dr, column + dc);
            }
        }
    }


    function restartGame() {
    location.replace("/games"); //Return to restart game
    }

    function finishGame() {
    //warn to the player that the player want to leave without save the progress
    var confirmed = confirm("Are you sure you want to finish the game prematurely? Your progress in the current game will be lost!");
    // Redirect to the homepage
    if (confirmed) {
        window.location.replace("/");
        }
    }  

    function checkSquare(rows, column) {
        if (rows < 0 || rows >= totalrows || column < 0 || column >= totalcolumns) {
            return 0;
        }
        if (minesPosition.includes(rows.toString() + "-" + column.toString())) {
            return 1;
        }
        return 0;
    }
    
</script>