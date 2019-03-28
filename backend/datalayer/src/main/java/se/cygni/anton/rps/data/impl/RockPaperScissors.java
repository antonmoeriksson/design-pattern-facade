package se.cygni.anton.rps.data.impl;

import se.cygni.anton.rps.data.api.GameFacade;
import se.cygni.anton.rps.data.api.PlayerFacade;
import util.Move;
import util.State;

import java.util.HashMap;
import java.util.UUID;

public class RockPaperScissors implements GameFacade {
    private Player player1;
    private Player player2;
    private State state;
    private HashMap<UUID, GameFacade> games = new HashMap<UUID, GameFacade>();


    private UUID id;

    public RockPaperScissors(Player player1) {
        this.player1 = player1;
        this.player2 = null;
        this.state = State.ONGOING;
        this.id = UUID.randomUUID();
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public RockPaperScissors() {
    }

    public RockPaperScissors create(PlayerFacade player) {
        return new RockPaperScissors((Player) player);
    }

    public GameFacade create() {
        return new RockPaperScissors();
    }


    public UUID getGameId(GameFacade gameFacade) {
        return ((RockPaperScissors) gameFacade).getId();
    }

    public GameFacade addPlayerToGame(GameFacade game, PlayerFacade player) {
        ((RockPaperScissors) game).setPlayer2((Player) player);
        return game;
    }

    public GameFacade addGame(String gameId, GameFacade gameFacade) {
        games.put(UUID.fromString(gameId), gameFacade);
        return games.get(gameId);
    }

    public GameFacade getGame(String id) {
        UUID uuid = UUID.fromString(id);
        if (games.containsKey(uuid)) {
            return games.get(UUID.fromString(id));
        } else {
            return create();
        }
    }

}
