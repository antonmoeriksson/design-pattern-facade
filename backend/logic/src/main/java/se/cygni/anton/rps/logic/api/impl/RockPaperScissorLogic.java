package se.cygni.anton.rps.logic.api.impl;

import se.cygni.anton.rps.data.api.RockPaperScissorFacade;
import se.cygni.anton.rps.data.api.PlayerFacade;
import se.cygni.anton.rps.data.impl.Player;
import se.cygni.anton.rps.data.impl.RockPaperScissors;
import se.cygni.anton.rps.logic.api.facade.RockPaperScissorLogicFacade;
import se.cygni.anton.rps.logic.api.facade.PlayerLogicFacade;
import util.Move;
import util.State;

import java.util.UUID;

public class RockPaperScissorLogic implements RockPaperScissorLogicFacade {
    private final RockPaperScissorFacade rockPaperScissorFacade;
    private final PlayerLogicFacade playerLogicFacade = new PlayerLogic();


    public RockPaperScissorLogic(RockPaperScissorFacade rockPaperScissorFacade) {
        this.rockPaperScissorFacade = rockPaperScissorFacade;
    }

    public RockPaperScissorFacade createGame(String playerName) {
        RockPaperScissorFacade game = rockPaperScissorFacade.create(playerLogicFacade.createPlayer(playerName));
        rockPaperScissorFacade.addGame(game.getGameId(game).toString(), game);
        return game;
    }


    public RockPaperScissorFacade addPlayerToGame(String gameId, String playerName) {
        PlayerFacade player = playerLogicFacade.createPlayer(playerName);
        RockPaperScissorFacade game = getGame(gameId);
        return rockPaperScissorFacade.addPlayerToGame(game, player);
    }

    public boolean isPlayerInGame(RockPaperScissorFacade rockPaperScissorFacade, PlayerFacade playerFacade) {
        PlayerFacade player1 = rockPaperScissorFacade.getPlayer1();
        PlayerFacade player2 = rockPaperScissorFacade.getPlayer2();

        return player1.getName().equals(playerFacade.getName()) ||
                player2.getName().equals( playerFacade.getName());
    }

    public void updateGameResult(RockPaperScissorFacade rockPaperScissorFacade) {
        updateResult(rockPaperScissorFacade);
    }

    public UUID getGameId(RockPaperScissorFacade rockPaperScissorFacade) {
        return rockPaperScissorFacade.getGameId(rockPaperScissorFacade);
    }

    public RockPaperScissorFacade getGame(String gameId) {
        return rockPaperScissorFacade.getGame(gameId);
    }

    public void performMove(String gameId, String playerName, String move) {
        if (isPlayerInGame(getGame(gameId), playerLogicFacade.getPlayer(playerName))) {
            PlayerFacade player = playerLogicFacade.getPlayer(playerName);
            playerLogicFacade.execMove(player, move);
            updateResult(getGame(gameId));
        }
    }

    private void updateResult(RockPaperScissorFacade rockPaperScissorFacade) {
        if (hasBothPlayerMadeMove(rockPaperScissorFacade)) {
            int winner = getWinner(rockPaperScissorFacade);
            rockPaperScissorFacade.setState(State.values()[winner]);
        }
    }

    private int getWinner(RockPaperScissorFacade rockPaperScissorFacade) {
        PlayerFacade player1 = rockPaperScissorFacade.getPlayer1();
        PlayerFacade player2 = rockPaperScissorFacade.getPlayer2();
        // (n + p1 - p2) % n
        return ((State.values().length - 1) + (player1.getMove().ordinal() - player2.getMove().ordinal())) % (State.values().length - 1);
    }

    private boolean hasBothPlayerMadeMove(RockPaperScissorFacade rockPaperScissorFacade) {
        PlayerFacade player1 = rockPaperScissorFacade.getPlayer1();
        PlayerFacade player2 = rockPaperScissorFacade.getPlayer2();
        return player1.getMove() != Move.UNKNOWN && player2.getMove() != Move.UNKNOWN;
    }
}
