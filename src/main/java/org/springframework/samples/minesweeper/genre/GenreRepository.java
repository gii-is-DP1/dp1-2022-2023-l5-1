package org.springframework.samples.minesweeper.genre;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends  CrudRepository<Genre, String>{

    @Query("SELECT genre FROM Genre genre WHERE genre.id = :genreId")
    Genre findGenreById(@Param("genreId") Integer genreId);

    @Query("SELECT g FROM Genre g ORDER BY g.id DESC")
    List<Genre> getAllGenresOrdered(Pageable p);
    
}
