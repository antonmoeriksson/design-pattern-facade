package se.cygni.anton.rps.logic.api.facade;

import se.cygni.anton.rps.data.api.GameFacade;
import se.cygni.anton.rps.data.api.PlayerFacade;

import java.util.List;
import java.util.UUID;

public interface GameLogicFacade {
    GameFacade createGame(String playerName);
    String getGameState();
    GameFacade addPlayerToGame(String gameID, String playerName);
    boolean isPlayerInGame(GameFacade gameFacade, PlayerFacade playerFacade);
    void updateGameResult(GameFacade gameFacade);
    UUID getGameId(GameFacade gameFacade);
    GameFacade getGame(String id);
    void performMove(String id, String player, String move);


}
