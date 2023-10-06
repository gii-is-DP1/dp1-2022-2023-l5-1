package org.springframework.samples.minesweeper.platform;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlatformRepository extends  CrudRepository<Platform, String>{

    @Query("SELECT platform FROM Platform platform WHERE platform.id = :platformId")
    Platform findPlatformById(@Param("platformId") Integer platformId);

    @Query("SELECT p FROM Platform p ORDER BY p.id DESC")
    List<Platform> getAllPlatformOrdered(Pageable p);
    
}
