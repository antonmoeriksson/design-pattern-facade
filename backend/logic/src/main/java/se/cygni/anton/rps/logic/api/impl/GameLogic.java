package se.cygni.anton.rps.logic.api.impl;

import se.cygni.anton.rps.data.api.GameFacade;
import se.cygni.anton.rps.data.api.PlayerFacade;
import se.cygni.anton.rps.data.impl.Player;
import se.cygni.anton.rps.data.impl.RockPaperScissors;
import se.cygni.anton.rps.logic.api.facade.GameLogicFacade;
import se.cygni.anton.rps.logic.api.facade.PlayerLogicFacade;
import util.Move;
import util.State;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GameLogic implements GameLogicFacade {
    private GameFacade gameFacade;
    private PlayerLogicFacade playerLogicFacade = new PlayerLogic();


    public GameLogic(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public GameFacade createGame(String playerName) {
        GameFacade game = gameFacade.create(playerLogicFacade.createPlayer(playerName));
        gameFacade.addGame(game.getGameId(game).toString(), game);
        return game;
    }

    public String getGameState() {
        return null;
    }

    public GameFacade addPlayerToGame(String gameID, String playerName) {
        PlayerFacade player = playerLogicFacade.createPlayer(playerName);
        GameFacade game = getGame(gameID);
        return gameFacade.addPlayerToGame(game, player);
    }

    public boolean isPlayerInGame(GameFacade gameFacade, PlayerFacade playerFacade) {
        Player player1 = ((RockPaperScissors)gameFacade).getPlayer1();
        Player player2 = ((RockPaperScissors)gameFacade).getPlayer2();

        return player1.getName().equals(((Player) playerFacade).getName()) ||
                player2.getName().equals(((Player) playerFacade).getName());
    }

    public void updateGameResult(GameFacade gameFacade) {
        updateResult(gameFacade);
    }

    public UUID getGameId(GameFacade gameFacade) {
        return gameFacade.getGameId(gameFacade);
    }

    public GameFacade getGame(String id) {
        return ((RockPaperScissors)gameFacade).getGame(id);
    }

    public void performMove(String gameId, String playerName, String move) {
        PlayerFacade player = playerLogicFacade.getPlayer(playerName);
        playerLogicFacade.execMove(player, move);
        updateResult(getGame(gameId));
    }

    public void updateResult(GameFacade gameFacade) {
        if (hasBothPlayerMadeMove(gameFacade)) {
            int winner = getWinner(gameFacade);
            ((RockPaperScissors)gameFacade).setState(State.values()[winner]);
        }
    }

    private int getWinner(GameFacade gameFacade) {
        Player player1 = ((RockPaperScissors)gameFacade).getPlayer1();
        Player player2 = ((RockPaperScissors)gameFacade).getPlayer2();
        return ((State.values().length - 1) + (player1.getMove().ordinal() - player2.getMove().ordinal())) % (State.values().length - 1);
    }

    private boolean hasBothPlayerMadeMove(GameFacade gameFacade) {
        Player player1 = ((RockPaperScissors)gameFacade).getPlayer1();
        Player player2 = ((RockPaperScissors)gameFacade).getPlayer2();
        return player1.getMove() != Move.UNKNOWN && player2.getMove() != Move.UNKNOWN;

    }

}
