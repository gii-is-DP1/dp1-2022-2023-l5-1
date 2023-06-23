package org.springframework.samples.petclinic.square;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


import org.springframework.samples.petclinic.board.Board;

@Service
public class SquareService {

    public List<Square> initializeSquares(Board board){
        List<Square> squares =new ArrayList<Square>();
        Integer mines = board.getMinesNumber();
        Integer rows = board.getYNumber();
        Integer columns= board.getXNumber();
    
        for(int i =0; i<rows; i++){
            for(int j=0; j<columns;j++){
                Square sq = new Square();
                sq.setBoard(board);
                sq.setColumn(j);
                sq.setRow(i);
                sq.setIsCovered(true);
                sq.setIsMine(false);
                squares.add(sq);
            }
        }
        Integer i= 1;
        //Colocación de minas
        while(i<mines){
            double random = Math.floor(Math.random()*(squares.size()-1));
            int index = (int) random;
            Square sq = squares.get(index);
            if(sq.getIsMine()==false){
                sq.setIsMine(true);
                i++;
            }
            
        }
        return squares;
    }
}
