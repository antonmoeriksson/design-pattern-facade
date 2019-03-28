package se.cygni.anton.rps.data.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.cygni.anton.rps.data.api.PlayerFacade;
import util.Move;

import java.util.HashMap;
import java.util.List;


public class Player implements PlayerFacade {
    private HashMap<String, PlayerFacade> players = new HashMap<String, PlayerFacade>();

    private String name;
    @JsonIgnore(true)
    private Move move;
    private boolean hasMadeMove = false;

    public Player() {}

    public Player(String name) {
        this.name = name;
        this.move = Move.UNKNOWN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public String getName(PlayerFacade playerFacade) {
        return ((Player) playerFacade).name;
    }

    public Move[] getMoves() {
        return Move.values();
    }

    public PlayerFacade createPlayer(String name) {
        players.put(name, new Player(name));
        return players.get(name);
    }

    public PlayerFacade createPlayer() {
        return new Player();
    }

    public PlayerFacade getPlayer(String name) {
        return players.get(name);
    }

    public boolean hasMadeMove(String name) {
        return this.hasMadeMove;
    }

    public void setHasMadeMove(boolean hasMadeMove) {
        this.hasMadeMove = hasMadeMove;
    }
}
