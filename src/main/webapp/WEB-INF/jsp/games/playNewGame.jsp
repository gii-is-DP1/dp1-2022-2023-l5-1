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
        .tile-clicked {
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

    <h1>Mines:<c:out value="${board.minesNumber}"></c:out></h1>
    <div id="board"></div>
    <button id="flag-button"><span class="glyphicon glyphicon-flag" aria-hidden="true"></span></button>
    <button id="restart-button">Restart Game</button>
    <button id="finish-button">Finish Game</button>   
</petclinic:layout>

<script>
    var root=document.querySelector(":root");
    var rows="${board.rows}";
    var id="${game.getId()}";
    var columns="${board.columns}";
    var minesLocation = "${mines}";
    var minesCount = "${mines.size()}"
    var success = false;
    var width = (columns)*50+20;
    var height = (rows)*50+20;
    root.style.setProperty("--width", parseInt(width)+"px");
    root.style.setProperty("--height", parseInt(height)+"px");
    var tilesClicked = 0;
    var gameOver = false;
    var flagEnabled = false;
    var flagCount = 0;
    var board=[];

    window.onload = function(){
        startGame();
        document.getElementById("restart-button").addEventListener("click", restartGame);
        document.getElementById("finish-button").addEventListener("click", finishGame);
    }

    function endGame() {
        var url = '/games/endGame?id='+ id + '&success=' + success;
        location.replace(url);
    }

    function startGame() {
        document.getElementById("flag-button").addEventListener("click", setFlag);

        //populate our board
        for (let r = 0; r < rows; r++) {
            let row = [];
            for (let c = 0; c < columns; c++) {
                //<div id="0-0"></div>
                let tile = document.createElement("div");
                tile.id = r.toString() + "-" + c.toString();
                tile.addEventListener("click", clickTile);
                document.getElementById("board").append(tile);
                row.push(tile);
            }
            board.push(row);
        }

        console.log(board);
        return;
    }

    function setFlag() {
        if (flagEnabled) {
            flagEnabled = false;
            document.getElementById("flag-button").style.backgroundColor = "lightgray";
        }
        else {
            flagEnabled = true;
            document.getElementById("flag-button").style.backgroundColor = "darkgray";
        }
        return;
    }

    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    async function clickTile() {
        let tile = this;
        if (gameOver || this.classList.contains("tile-clicked") || (tile.innerText != "" && !flagEnabled)) {
            return;
        }
        if (flagEnabled) {
            if (tile.innerText == "") {
                tile.innerText = "x";
                flagCount++;
            }
            else if (tile.innerText == "x") {
                tile.innerText = "";
                flagCount--
            }
            return;
        }

        if (minesLocation.includes(tile.id)) {
            // alert("GAME OVER");
            revealMines();
            gameOver = true;
            await sleep(500);
            alert("Game Over\n You lose");
            endGame();
            return;
        }


        let coords = tile.id.split("-"); // "0-0" -> ["0", "0"]
        let r = parseInt(coords[0]);
        let c = parseInt(coords[1]);
        checkMine(r, c);

    }

    function revealMines() {
        for (let r= 0; r < rows; r++) {
            for (let c = 0; c < columns; c++) {
                let tile = board[r][c];
                if (minesLocation.includes(tile.id) && (tile.innerText == "x" || success)) {
                    tile.style.backgroundColor = "green";
                } else if (minesLocation.includes(tile.id) && tile.innerText != "x") {
                    tile.innerText = "x";
                    tile.style.backgroundColor = "red";
                }
            }
        }
        return;
    }

    async function checkMine(r, c) {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            return;
        }
        if (board[r][c].classList.contains("tile-clicked")) {
            return;
        }

        board[r][c].classList.add("tile-clicked");
        tilesClicked += 1;

        let minesFound = 0;

        //top 3
        minesFound += checkTile(r-1, c-1);      //top left
        minesFound += checkTile(r-1, c);        //top 
        minesFound += checkTile(r-1, c+1);      //top right

        //left and right
        minesFound += checkTile(r, c-1);        //left
        minesFound += checkTile(r, c+1);        //right

        //bottom 3
        minesFound += checkTile(r+1, c-1);      //bottom left
        minesFound += checkTile(r+1, c);        //bottom 
        minesFound += checkTile(r+1, c+1);      //bottom right

        if (minesFound > 0) {
            board[r][c].innerText = minesFound;
            board[r][c].classList.add("x" + minesFound.toString());
        }
        else {
            //top 3
            checkMine(r-1, c-1);    //top left
            checkMine(r-1, c);      //top
            checkMine(r-1, c+1);    //top right

            //left and right
            checkMine(r, c-1);      //left
            checkMine(r, c+1);      //right

            //bottom 3
            checkMine(r+1, c-1);    //bottom left
            checkMine(r+1, c);      //bottom
            checkMine(r+1, c+1);    //bottom right
        }

        if (tilesClicked == rows * columns - minesCount) {
            gameOver = true;
            success = true;
            revealMines();
            await sleep(500);
            alert("Game Over\n You win");
            endGame();
        }
    }

    function restartGame() {
    location.reload(); // Recargar la página para reiniciar el juego
    }

    function finishGame() {
    // Avisar al usuario de que al terminar antes de tiempo no se guardará el progreso en la partida
    var confirmed = confirm("Are you sure you want to finish the game prematurely? Your progress in the current game will be lost!");
    // Redireccionar a la página de inicio
    if (confirmed) {
        window.location.replace("/");
        }
    }  

    function checkTile(r, c) {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            return 0;
        }
        if (minesLocation.includes(r.toString() + "-" + c.toString())) {
            return 1;
        }
        return 0;
    }
    
</script>