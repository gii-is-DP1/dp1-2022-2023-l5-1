package org.springframework.samples.minesweeper.board;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@org.springframework.stereotype.Repository
public interface BoardRepository extends CrudRepository<Board,Integer>{

    @Query("SELECT board FROM Board board WHERE board.id = :boardId")
    public Board findBoardById(@Param("boardId") Integer boardId);
    
}
