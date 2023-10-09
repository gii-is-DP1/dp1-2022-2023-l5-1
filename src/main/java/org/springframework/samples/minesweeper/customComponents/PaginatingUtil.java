package org.springframework.samples.minesweeper.customComponents;

import java.util.Map;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class PaginatingUtil{


    public void prepareModelForPagination(Map<String, Object> model, Pageable p, List ls, Integer totalElements){
		model.put("pageNumber", p.getPageNumber());
		model.put("pageSize", p.getPageSize());
		model.put("hasPrevious", p.hasPrevious());
        Double totalPages=0.0;
		Integer itemsPerPage = p.getPageSize();
        //se añade este if ya que en el caso de active games es posible que no haya ninguna partida en curso,
        // poniendo totalPages a -1, y al hacer forEach en la vista salta whitelabel error
        if(totalElements!=0.0){
            totalPages = Math.ceil(totalElements/(itemsPerPage));
        }
		model.put("totalPages", totalPages);
        model.put("itemsListed", ls);      
    }
    
}
