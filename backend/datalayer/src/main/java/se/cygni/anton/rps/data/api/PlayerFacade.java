package se.cygni.anton.rps.data.api;

import util.Move;

public interface PlayerFacade {
    PlayerFacade createPlayer(String name);
    PlayerFacade getPlayer(String name);

    String getName();

    Move getMove();
    Move[] getMoves();

    void setMove(Move move);

    boolean hasMadeMove(String name);
    void setHasMadeMove(boolean hasMadeMove);
}
