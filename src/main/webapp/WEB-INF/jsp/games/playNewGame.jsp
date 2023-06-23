<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Play game">
    <style>
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
    </style>
    <h1>Mines:<c:out value="${board.minesNumber}"></c:out></h1>
    <div id="board"></div>
</petclinic:layout>
<script>
    var root=document.querySelector(":root");
    var rows="${board.rows}";
    var columns="${board.columns}";
    var width = (columns)*50+20;
    var height = (rows)*50+20;
    root.style.setProperty("--width", parseInt(width)+"px");
    root.style.setProperty("--height", parseInt(height)+"px");

    var board=[];

    window.onload = function(){
        startGame();
    }
    function startGame(){
        for(let r=0;r<rows;r++){
            let row=[];
            for(let c=0;c<columns;c++){
                let tile =document.createElement("div");
                tile.id=r.toString()+"-"+c.toString();
                document.getElementById("board").append(tile);
                row.push(tile);
            }
            board.push(row);
        }
        console.log(board)
    }
</script>