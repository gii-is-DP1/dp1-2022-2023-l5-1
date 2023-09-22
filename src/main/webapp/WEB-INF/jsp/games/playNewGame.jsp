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

    // Get some data variables from the game context like rows, id,column...
    var rows="${board.rows}";
    var id="${game.getId()}";
    var columns="${board.columns}";
    var minesLocation = "${mines}";
    var minesCount = "${mines.size()}";
    var difficulty = "${difficulty}";

    //Variable state
    var success = false;
    var width = (columns)*50+20;
    var height = (rows)*50+20;

    //Set custom properties
    root.style.setProperty("--width", parseInt(width)+"px");
    root.style.setProperty("--height", parseInt(height)+"px");
    
    //Varibles related to the game state
    var tilesClicked = 0;
    var gameOver = false;
    var flagEnabled = false;
    var flagCount = 0;
    var error = "${error}";
    var board=[];
    var hardcore = "${hardcore}";

    //Update the html content
    document.getElementById("flags").innerHTML = minesCount;

    window.onload = async function(){
        // Check if an error occurred, if it occurs show the next alert.
        if(error == "true") {
            alert("Mines have been reduced to 90% of the maximum allowed.");
        }

        // If it is not a hardcore game, finish the casual game.
        if(hardcore == "false") {
            finishCasualGame();
        }

        startGame();

        // Add restart button and a finish button
        document.getElementById("restart-button").addEventListener("click", restartGame);
        document.getElementById("finish-button").addEventListener("click", finishGame);
    }

    function endGame() {
        var url = '/games/endGame?id='+ id + '&success=' + success +'&audit_id=${audit.id}';
        location.replace(url);
    }

    // If the game duration > recommended the game finish
    async function finishCasualGame() { 
        var delay = 60000;
        if(difficulty == "INTERMEDIATE") {
            delay = 180000;
        } else if(difficulty == "ADVANCED" || difficulty == "CUSTOM") {
            delay = 300000;
        }
        await sleep(delay);
        revealMines();
        alert("You have spent too much time in the game, the game will finish now.");
        endGame();
    }


    function startGame() {
        document.getElementById("flag-button").addEventListener("click", setFlag);
        let r = 0;
        while (r < rows) {
            let row = [];
            let c = 0;
            while (c < columns) {
                let tile = document.createElement("div");
                tile.id = r.toString() + "-" + c.toString();
                tile.addEventListener("click", clickTile);
                document.getElementById("board").append(tile);
                row.push(tile);
                c++;
            }
            board.push(row);
            r++;
        }

        console.log(board);
        return;
    }

    //TO SET FLAG
    function setFlag() {
        const flagButton = document.getElementById("flag-button");
        flagEnabled = !flagEnabled;
        flagButton.style.backgroundColor = flagEnabled ? "darkgray" : "lightgray";
    }

    //Sleep time
    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }


    //When clic the title
    async function clickTile() {

        let tile = this;

        // Check if the game is over, tile is already clicked, or flags are not enabled and there is text on the tile
        if (gameOver || tile.classList.contains("tile-clicked") || (tile.innerText != "" && !flagEnabled)) {
            return;
        }

        // Handle flag placement or removal when flags are enabled
        if (flagEnabled) {
            if (tile.innerText == "") {
                tile.innerText = "x"; 
                flagCount++;
            } else if (tile.innerText == "x") {
                tile.innerText = ""; // Remove the flag
                flagCount--;
            }
            //Update the flag counter
            document.getElementById("flags").innerHTML = minesCount - flagCount;
            return;
        }

        // Check if the tile contains a mine
        if (minesLocation.includes(tile.id)) {
            revealMines();
            gameOver = true;
            await sleep(500);
            alert("Game Over\nYou lose");
            endGame();
            return;
        }

        // Split the tile's ID to get its row and column coordinates
        let coords = tile.id.split("-");
        let r = parseInt(coords[0]);
        let c = parseInt(coords[1]);

        checkMine(r, c);
    }


    //Reveal mine if player click on a tile that contains mine
    function revealMines() {
        board.forEach((row, r) => {
            row.forEach((tile, c) => {
                if (minesLocation.includes(tile.id) && (tile.innerText == "x" || success)) {
                    tile.style.backgroundColor = "green";
                } else if (minesLocation.includes(tile.id) && tile.innerText != "x") {
                    tile.innerText = "x";
                    tile.style.backgroundColor = "red";
                }
            });
        });
        return;
    }

    async function checkMine(r, c) {
        if (isOutOfBounds(r, c) || isTileClicked(r, c)) {
            return;
        }

        board[r][c].classList.add("tile-clicked");
        tilesClicked += 1;

        const minesFound = countMinesAround(r, c);

        if (minesFound > 0) {
            revealTileNumber(r, c, minesFound);
        } else {
            revealAdjacentTiles(r, c);
        }

        if (tilesClicked === rows * columns - minesCount) {
            gameOver = true;
            success = true;
            revealMines();
            await sleep(500);
            alert("Game Over\n You win");
            endGame();
        }
    }

    function isOutOfBounds(r, c) {
        return r < 0 || r >= rows || c < 0 || c >= columns;
    }

    function isTileClicked(r, c) {
        return board[r][c].classList.contains("tile-clicked");
    }

    function countMinesAround(r, c) {
        let minesFound = 0;
        const offsets = [-1, 0, 1];

        for (const dr of offsets) {
            for (const dc of offsets) {
                if (dr === 0 && dc === 0) continue;
                minesFound += checkTile(r + dr, c + dc);
            }
        }

        return minesFound;
    }

    function revealTileNumber(r, c, number) {
        board[r][c].innerText = number;
        board[r][c].classList.add("x" + number.toString());
    }

    function revealAdjacentTiles(r, c) {
        const offsets = [-1, 0, 1];

        for (const dr of offsets) {
            for (const dc of offsets) {
                if (dr === 0 && dc === 0) continue;
                checkMine(r + dr, c + dc);
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