package org.springframework.samples.petclinic.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class GameService {

    
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    private String laPartida = "La partida {id: '";
    private String name = "', Name: '";

    @Transactional
    public Iterable<Game> findAll() {
        return gameRepository.findAll();
    }

    @Transactional
    public int gameCount() {
        return (int) gameRepository.count();
    }

    @Transactional
    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    @Transactional
    public Game findGameById(int id) throws DataAccessException, NotFoundException {
        Optional<Game> game = gameRepository.findById(id);
        if (!game.isEmpty()) {
            return game.get();
        } else {
            throw new NotFoundException("Game {id:" + id + "} no encontrada");
        }
    }

    @Transactional
    public List<Game> findOnGoingGames() throws DataAccessException {
        return gameRepository.findOnGoingGames();
    }

    @Transactional
    public List<Game> findLobbies() {
        return gameRepository.findLobbies();
    }

    @Transactional
    public void deleteGame(Game game) throws DeleteGameException {
        if (!game.isStarted()) {
            gameRepository.delete(game);
        } else {
            throw new DeleteGameException(laPartida + game.getId() + name + game.getName() + "'} no puede ser borrada");
        }
    }

    @Transactional
    public void startGame(Game game) throws NewGameException {
        if (game.hasEnoughPlayers() && !game.isStarted()) {
            game.setTurn(1);
            game.setStartTime(LocalDateTime.now());

            List<Integer> turnList = initialTurnList(game);
            mapGameRepository.putTurnList(game.getId(), turnList);

            cardService.newDeck(game);
            gameCardService.showCards(game);

            saveGame(game);
        } else {
            if (!game.hasEnoughPlayers()) {
                throw new NewGameException(laPartida + game.getId() + name + game.getName()
                        + "'} no puede ser iniciada porque no hay suficientes jugadores");
            } else {
                throw new NewGameException(laPartida + game.getId() + name + game.getName() + "'} ya ha sido iniciada");
            }

        }
    }

    @Transactional
    public void createNewGame(Game game) throws NewGameException {
        User creator = game.getCreator();
        if (creator.hasActiveGameAsCreator() || creator.hasActivePlayer()) {
            throw new NewGameException("El usuario {id: '" + creator.getId() + "', Username: '" + creator.getUsername()
                    + "'} ya tiene otro juego activo");
        } else {
            saveGame(game);
        }
    }

    @Transactional
    public void turnRoll(Roll roll, Integer gameId) {
        if (roll.getRollAmount() == null || roll.getRollAmount() == 0) {
            roll.rollDiceInitial();

        } else if (roll.getRollAmount() < roll.getMaxThrows() && roll.getKeep().length < roll.getValues().size()) {
            List<DiceValues> valoresConservados = Arrays.asList(roll.getKeep());
            roll.rollDiceNext(valoresConservados);
        } else if (roll.getRollAmount() < roll.getMaxThrows()) {
            List<DiceValues> valoresConservados = Arrays.asList(roll.getKeep());
            roll.rollDiceNext(valoresConservados);
            roll.setRollAmount(roll.getMaxThrows());
        }
        mapGameRepository.putRoll(gameId, roll);
    }

    @Transactional
    public void nuevoTurno(Game game) throws NotFoundException {
        Integer gameId = game.getId();
        mapGameRepository.putRoll(gameId, new Roll());

        game.setTurn(game.getTurn() + 1);

        nextPositionTurn(gameId);

        useCardsStartTurn(actualTurn(gameId));
        saveGame(game);
        playerService.startTurn(actualTurnPlayer(gameId));
    }

    @Transactional
    public void nextPositionTurn(Integer gameId) throws DataAccessException, NotFoundException {
        List<Integer> turnList = mapGameRepository.getTurnList(gameId);

        Boolean finished = Boolean.FALSE;

        while (!finished) {
            Player player = playerService.findPlayerById(turnList.get(1));
            if (!player.isDead()) {
                turnList.add(turnList.remove(0));
                mapGameRepository.putTurnList(gameId, turnList);
                finished = Boolean.TRUE;
            } else {
                turnList.remove(1);
            }
        }

    }

    @Transactional
    public Integer actualTurnPlayerId(Integer gameId) throws NotFoundException {
        return actualTurnPlayer(gameId).getId();
    }

    @Transactional
    public Player actualTurnPlayer(Integer gameId) throws NotFoundException {
        Player player = actualTurn(gameId);
        return player;
    }

    @Transactional
    public List<Game> findAllFinished() {
        Iterable<Game> resultSinFiltrar = findAll();
        List<Game> resultadoFiltrado = new ArrayList<>();
        for (Game game : resultSinFiltrar) {
            if (game.isFinished()) {
                resultadoFiltrado.add(game);
            }
        }
        return resultadoFiltrado;
    }

    @Transactional
    public List<Game> findAllNotFinished() {
        Iterable<Game> resultSinFiltrar = findAll();
        List<Game> resultadoFiltrado = new ArrayList<Game>();
        for (Game game : resultSinFiltrar) {
            if (!game.isFinished()) {
                resultadoFiltrado.add(game);
            }
        }
        return resultadoFiltrado;
    }
    @Transactional
    public void endGame(Game game) {
        if (!game.playersWithMaxVictoryPoints().isEmpty()) {
            game.setWinner(game.playersWithMaxVictoryPoints().get(0).getUser().getUsername());
            game.setEndTime(LocalDateTime.now());
        } else if (game.playersAlive().size() == 1) {
            game.setEndTime(LocalDateTime.now());
            game.setWinner(game.playersAlive().get(0).getUser().getUsername());
        }

        // Check achievements for every user when the game is finished
        if (game.isFinished()) {
            game.getPlayers().stream()
                    .forEach(p -> achievementService.checkAchievements(p.getUser()));
        }
        saveGame(game);
    }

    @Transactional
    public List<Integer> initialTurnList(Game game) {
        List<Integer> listaTurnos = new ArrayList<>();
        List<Player> jugadores = game.getPlayers();
        for (Player player : jugadores) {
            listaTurnos.add(player.getId());
        }
        Collections.shuffle(listaTurnos);
        return listaTurnos;
    }
    @Transactional
    public Player actualTurn(Integer gameId) throws NotFoundException {

        List<Integer> turnList = mapGameRepository.getTurnList(gameId);
        return playerService.findPlayerById(turnList.get(0));
    }

    @Transactional
    public Boolean isPlayerTurn(Integer gameId) throws NotFoundException {
        User user = userService.authenticatedUser();
        Boolean result = Boolean.FALSE;
        if (user != null) {
            result = actualTurn(gameId).getUser().getId().equals(user.getId());
        }
        return result;
    }

    @Transactional
    public Boolean isPlayerInGame(Game game) {
        User user = userService.authenticatedUser();
        Boolean result = Boolean.FALSE;
        if (user != null) {
            for (Player player : game.getPlayers()) {
                result = result || player.getUser().getId().equals(user.getId());
            }
        }
        return result;
    }

    @Transactional
    public List<Player> playersOrder(List<Integer> turnList) {
        return turnList.stream().map(id -> {
            try {
                return playerService.findPlayerById(id);
            } catch (DataAccessException | NotFoundException e) {
                log.warn(e.toString());
            }
            return null;
        }).collect(Collectors.toList());
    }



    
}
