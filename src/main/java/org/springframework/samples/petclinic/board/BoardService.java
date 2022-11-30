package org.springframework.samples.petclinic.board;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.deck.Deck;
import org.springframework.samples.petclinic.deck.DeckService;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.stereotype.Service;



@Service
public class BoardService {
   
    @Autowired
    private BoardRepository boardRepo;
    @Autowired
    private DeckService deckService;
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;
 

    private static final String REDIRECT_TO_BOARDS = "redirect:/boards/";

    @Transactional
    public String initBoard(Game g){        
  
        Board board = g.getBoard();
        //List<Player> players = g.getPlayers();       
        initCardPlayers(g);
        //distribute(board,players); //circulo de jugadores
        g.setBoard(board);
        gameService.save(g);     
        
        return REDIRECT_TO_BOARDS+ g.getCode();
    }

  
    @Transactional
    public void distribute(Board b, List<Player> players){        
        createFields();
        distributeFields();
        boardRepo.save(b);
    }
    private void distributeFields() {
    }

    private void createFields() {
	}

	@Transactional
    public void initCardPlayers(Game game){           
        List<Player> players = game.getPlayers();       
        Deck d = game.getDeck();
        for(Player p: players){
            List<Card> hand = new ArrayList<>();      
            List<Card> cards = d.getCards().stream().limit(7).collect(Collectors.toList());
            d.getCards().removeAll(cards);
            deckService.save(d);
            hand.addAll(cards);
            p.setCards(hand);

            playerService.save(p);
        }
    }
  
    
    @Transactional
    public void save(Board b){
        boardRepo.save(b);
    }
    
}
