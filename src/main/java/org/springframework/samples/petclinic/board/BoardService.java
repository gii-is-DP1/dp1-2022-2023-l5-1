package org.springframework.samples.petclinic.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private UserRepository ur;

    public Board boardInit(DifficultyLevel dif, String username){
        Board br= new Board();
        switch(dif){
            case BEGGINER:
                br.setXNumber(8);
                br.setYNumber(8);
                br.setMinesNumber(10);
                br.setFlagsNumber(10);
            case INTERMEDIATE:
                br.setXNumber(16);
                br.setYNumber(16);
                br.setMinesNumber(40);
                br.setFlagsNumber(40);
            case ADVANCED:
                br.setXNumber(30);
                br.setYNumber(16);
                br.setMinesNumber(90);
                br.setFlagsNumber(90);
        }
        br.setUser(ur.findByUsername(username));
        br.setLevel(dif);
        return br;
    }

    public Board boardInit(Integer rows, Integer columns, Integer mines, String username){
        Board br= new Board();
        br.setXNumber(columns);
        br.setYNumber(rows);
        br.setMinesNumber(mines);
        br.setFlagsNumber(mines);
        br.setUser(ur.findByUsername(username));
        br.setLevel(DifficultyLevel.CUSTOM);
        return br;
    }
}
