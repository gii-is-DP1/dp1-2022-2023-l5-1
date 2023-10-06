package org.springframework.samples.minesweeper.customComponents;

import java.util.Map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.samples.minesweeper.game.GameService;
import org.springframework.samples.minesweeper.genre.GenreService;
import org.springframework.samples.minesweeper.platform.PlatformService;
import org.springframework.samples.minesweeper.saga.SagaService;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Component;


@Component
public class PaginatingUtil{

    private UserService us;
    private GenreService genreS;
    private PlatformService ps;
    private SagaService ss;
    private GameService gameS;

	@Autowired
	public void PaginatingUtil(UserService us, GenreService genreS, PlatformService ps, SagaService ss, GameService gameS) {
		this.us = us;
        this.genreS =genreS;
        this.ps = ps;
        this.ss = ss;
        this.gameS = gameS;
	}

    public void prepareModelForPagination(Map<String, Object> model, Pageable p, String type, Boolean status){
        Integer page=0;
        Pair pair = abstractListAllOrdered(type, page, p, status);
		List ls = (List) pair.getFirst();
		model.put("pageNumber", p.getPageNumber());
		model.put("pageSize", p.getPageSize());
		model.put("hasPrevious", p.hasPrevious());
        Double totalPages=0.0;
		Integer itemsPerPage = p.getPageSize();
        //se añade este if ya que en el caso de active games es posible que no haya ninguna partida en curso,
        // poniendo totalPages a -1, y al hacer forEach en la vista salta whitelabel error
        if((Double)pair.getSecond()!=0.0){
            totalPages = Math.ceil((Double)pair.getSecond()/(itemsPerPage)) - 1.0;
        }
		model.put("totalPages", totalPages);
        model.put("itemsListed", ls);      
    }

    private  Pair<List,Double> abstractListAllOrdered(String type, Integer page, Pageable p, Boolean status){
        List ls=new ArrayList<>();
        Integer totalElements=0;
        if(type.equals("user")){
            ls = us.getAllUsersOrdered(page, p);
            totalElements = us.findUsers().size();

        }else if(type.equals("genres")){
            ls=genreS.getAllGenresOrdered(page, p);
            totalElements = genreS.findGenres().size();

        }else if(type.equals("platforms")){
            ls=ps.getAllPlatformOrdered(page, p);
            totalElements = ps.findPlatforms().size();

        }else if(type.equals("sagas")){
            ls=ss.getAllSagasOrdered(page, p);
            totalElements = ss.findSagas().size();

        }else if(type.equals("game")){
            if(status){
                ls=gameS.getActiveGamesOrdered(page, p);
                totalElements = gameS.getActiveGames().size();
            }else{
                ls=gameS.getFinishGamesOrdered(page, p);
                totalElements = gameS.getFinishGames().size();
            }
        }
        return Pair.of(ls, totalElements.doubleValue());
    }
    
}
