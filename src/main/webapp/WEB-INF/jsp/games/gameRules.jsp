<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Game Rules">
    <head>
        <title>Minesweeper</title>
      </head>
      <body>
        <h1>Minesweeper</h1>
      
        <h2>Difficulty Levels</h2>
        <ul>
          <li>Beginner level: 8 X 8 squares and 10 mines.</li>
          <li>Intermediate level: 16 X 16 squares and 40 mines.</li>
          <li>Advanced level: 16 X 30 squares and 90 mines.</li>
          <li>Custom level: You can choose all the parameters.</li>
        </ul>
      
        <h2>Game Rules</h2>
        <ul>
          <li>Each square can be in one of three states: unopened, opened, or marked with a flag.</li>
          <li>When opening a square, one of three things can happen:
            <ul>
              <li>If the square contains a mine, the player loses the game.</li>
              <li>If the square does not contain a mine and has a number, the number indicates how many mines are in the surrounding squares.</li>
              <li>If the square does not contain a mine and does not have a number, all neighboring squares are automatically opened.</li>
            </ul>
          </li>
          <li>The player can mark squares with a flag if they believe it contains a mine.</li>
          <li>The game ends when all squares without mines have been opened.</li>
        </ul>
      
        <h2>Strategy</h2>
        <ul>
          <li>At the start of the game, a common strategy is to click randomly until finding a large opening with many numbers. This strategy can help reveal information about the location of the mines.</li>
          <li>If all the mines surrounding a number have been correctly marked, the player can click on that number with both mouse buttons simultaneously (chording) to automatically open the remaining squares. However, if some squares have been incorrectly marked, chording can cause the mines to explode.</li>
          <li>Some variations of the game, such as Minesweeper X, Crossmines, and Minehunt, expand on the basic game concepts and add new design elements.</li>
          <li>The difficulty of the game can be increased by adding more mines or starting with a larger board. For example, the expert level of Minesweeper has a 30 X 16 board with 90 mines.</li>
          <li>The time taken to complete the game can be recorded as a measure of performance, but there is no official scoring in Minesweeper.</li>
        </ul>
      </body>
</petclinic:layout>