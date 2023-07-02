package org.springframework.samples.minesweeper.genre;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends  CrudRepository<Genre, String>{

    @Query("SELECT genre FROM Genre genre WHERE genre.id = :genreId")
    Genre findGenreById(@Param("genreId") Integer genreId);
    
}
