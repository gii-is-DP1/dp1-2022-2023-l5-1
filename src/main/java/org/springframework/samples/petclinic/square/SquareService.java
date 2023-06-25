package org.springframework.samples.petclinic.square;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.samples.petclinic.board.Board;

@Service
public class SquareService {

    public List<String> initializeSquares(Board board){
        List<String> squares =new ArrayList<String>();
        Integer mines = board.getMinesNumber();
        Integer rows = board.getRows();
        Integer columns= board.getColumns();
    
        for(int i =0; i<rows; i++){
            for(int j=0; j<columns;j++){
                squares.add(i+"-"+j);
            }
        }
        Integer i= 1;
        //Colocación de minas
        List<String> mineList = new ArrayList<>();
        while(i<=mines){
            double random = Math.round(Math.random()*(squares.size()-1));
            int index = (int) random;
            String sq = squares.get(index);
            if(!mineList.contains(sq)){
                mineList.add(sq);
                i++;
            }
            
        }
        return mineList;
    }
}