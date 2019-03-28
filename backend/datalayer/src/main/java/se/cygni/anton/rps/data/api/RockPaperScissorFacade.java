package se.cygni.anton.rps.data.api;

import util.Move;
import util.State;

import java.util.UUID;

public interface RockPaperScissorFacade {

    RockPaperScissorFacade create(PlayerFacade player);
    RockPaperScissorFacade create();
    RockPaperScissorFacade addGame(String gameId, RockPaperScissorFacade game);
    RockPaperScissorFacade getGame(String id);

    PlayerFacade getPlayer1();
    PlayerFacade getPlayer2();

    void setPlayer2(PlayerFacade player2);

    void setState(State state);

    UUID getId();

    State getState();

    UUID getGameId(RockPaperScissorFacade rockPaperScissorFacade);
    RockPaperScissorFacade addPlayerToGame(RockPaperScissorFacade game, PlayerFacade player);



}
