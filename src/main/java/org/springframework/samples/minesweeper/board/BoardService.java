package org.springframework.samples.minesweeper.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.minesweeper.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository ur;

    public Board boardInit(DifficultyLevel dif, String username){
        Board br= new Board();
        switch(dif){
            case BEGGINER:
                br.setColumns(8);
                br.setRows(8);
                br.setMinesNumber(10);
                br.setFlagsNumber(10);
                break;
            case INTERMEDIATE:
                br.setColumns(16);
                br.setRows(16);
                br.setMinesNumber(40);
                br.setFlagsNumber(40);
                break;
            case ADVANCED:
                br.setColumns(30);
                br.setRows(16);
                br.setMinesNumber(90);
                br.setFlagsNumber(90);
                break;
            default:
                break;
        }
        br.setUser(ur.findByUsername(username));
        br.setLevel(dif);
        return br;
    }

    public Board boardInit(Integer rows, Integer columns, Integer mines, String username){
        Board br= new Board();
        br.setColumns(columns);
        br.setRows(rows);
        br.setMinesNumber(mines);
        br.setFlagsNumber(mines);
        br.setUser(ur.findByUsername(username));
        br.setLevel(DifficultyLevel.CUSTOM);
        return br;
    }

    public void save(Board board) {
        boardRepository.save(board);
    }

    public Board getBoard(Integer boardId) {
        Board board = this.boardRepository.findBoardById(boardId);
        return board;
    }
}