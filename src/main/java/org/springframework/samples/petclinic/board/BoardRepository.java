package org.springframework.samples.petclinic.board;



import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface BoardRepository extends CrudRepository<Board,Integer>{
    
}
