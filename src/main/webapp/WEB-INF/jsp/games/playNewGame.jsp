<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Play game">
    <style>
        #board {
            width: var(--width);
            height: var(--height);
            border: 5px solid darkgray;
            background-color: lightgray;

            margin: 0auto;
            display: flex;
            flex-wrap: wrap;
        }

        #board div{
            width: 18px;
            height: 18px;
            border: 1px solid whitesmoke;

            font-size: 30px;
            display: flex;
            justify-content: center;
            align-items: center;

        }
    </style>

    <h1>Mines:<c:out value="${board.minesNumber}"></c:out>></h1>
    <div id="board"></div>
</petclinic:layout>
<script>
    var boardStyle = document.body.style;
    var rows=<c:out value="$board.yNumber"/>;
    var columns=<c:out value="$board.xNumber"/>;
    var width = columns*20;
    var height = rows*20;
    boardStyle.setProperty("--width", width.toString+"px");
    boardStyle.setProperty("--height", height.toString+"px");


    window.onload = function(){
        startGame();
    }
    function startGame(){
        for(let r=0;r<rows;r++){
            let row=[];
            for(let c=0;c<columns;c++){
                let tile =document.createElement("div");
                document.getElementById("board").append("tile");
                row.push(tile);
            }
        }
    }
</script>