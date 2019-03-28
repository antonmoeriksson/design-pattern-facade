package se.cygni.anton.rps.data.api;

import se.cygni.anton.rps.data.impl.Player;
import util.State;

import java.util.List;
import java.util.UUID;

public interface GameFacade {

    GameFacade create(PlayerFacade player);
    GameFacade create();

    GameFacade addGame(String gameId, GameFacade game);
    GameFacade getGame(String id);

    //void updateResult(GameFacade gameFacade);
    UUID getGameId(GameFacade gameFacade);
    GameFacade addPlayerToGame(GameFacade game, PlayerFacade player);
    //boolean isPlayerInGame(GameFacade gameFacade, PlayerFacade playerFacade);



}
