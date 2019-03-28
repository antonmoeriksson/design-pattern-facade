package se.cygni.anton.rps.logic.api.facade;

import se.cygni.anton.rps.data.api.RockPaperScissorFacade;
import se.cygni.anton.rps.data.api.PlayerFacade;

import java.util.UUID;

public interface RockPaperScissorLogicFacade {
    RockPaperScissorFacade createGame(String playerName);
    RockPaperScissorFacade addPlayerToGame(String gameId, String playerName);
    RockPaperScissorFacade getGame(String gameId);

    void performMove(String gameId, String player, String move);
    void updateGameResult(RockPaperScissorFacade rockPaperScissorFacade);
}
