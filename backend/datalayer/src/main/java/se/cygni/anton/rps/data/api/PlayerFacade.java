package se.cygni.anton.rps.data.api;

import util.Move;

public interface PlayerFacade {
    PlayerFacade createPlayer(String name);
    PlayerFacade createPlayer();

    String getName(PlayerFacade playerFacade);
    Move[] getMoves();
    PlayerFacade getPlayer(String name);
    boolean hasMadeMove(String name);
    void setHasMadeMove(boolean hasMadeMove);



}
