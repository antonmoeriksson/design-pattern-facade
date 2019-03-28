package se.cygni.anton.rps.data.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.cygni.anton.rps.data.api.RockPaperScissorFacade;
import se.cygni.anton.rps.data.api.PlayerFacade;
import util.State;

import java.util.HashMap;
import java.util.UUID;

public class RockPaperScissors implements RockPaperScissorFacade {
    private Player player1;
    private Player player2;
    private State state;

    @JsonIgnore(true)
    private HashMap<UUID, RockPaperScissorFacade> games = new HashMap<UUID, RockPaperScissorFacade>();

    private UUID id;

    private RockPaperScissors(Player player1) {
        this.player1 = player1;
        this.player2 = null;
        this.state = State.ONGOING;
        this.id = UUID.randomUUID();
    }

    public Player getPlayer1() {
        return player1;
    }

    public UUID getId() {
        return id;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerFacade player2) {
        this.player2 = (Player) player2;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public RockPaperScissors() {}

    public RockPaperScissors create(PlayerFacade player) {
        return new RockPaperScissors((Player) player);
    }

    public RockPaperScissorFacade create() {
        return new RockPaperScissors();
    }

    public UUID getGameId(RockPaperScissorFacade rockPaperScissorFacade) {
        return rockPaperScissorFacade.getId();
    }

    public RockPaperScissorFacade addPlayerToGame(RockPaperScissorFacade game, PlayerFacade player) {
        game.setPlayer2(player);
        return game;
    }

    public RockPaperScissorFacade addGame(String gameId, RockPaperScissorFacade rockPaperScissorFacade) {
        games.put(UUID.fromString(gameId), rockPaperScissorFacade);
        return games.get(gameId);
    }

    public RockPaperScissorFacade getGame(String id) {
        UUID uuid = UUID.fromString(id);
        if (games.containsKey(uuid)) {
            return games.get(UUID.fromString(id));
        } else {
            return create();
        }
    }
}
