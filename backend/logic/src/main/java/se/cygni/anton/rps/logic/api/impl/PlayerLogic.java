package se.cygni.anton.rps.logic.api.impl;

import com.google.gson.Gson;
import se.cygni.anton.rps.data.api.PlayerFacade;
import se.cygni.anton.rps.data.impl.Player;
import se.cygni.anton.rps.logic.api.facade.PlayerLogicFacade;
import util.Move;

import java.util.HashMap;

public class PlayerLogic implements PlayerLogicFacade {

    private final PlayerFacade playerFacade = new Player();

    public PlayerFacade createPlayer(String name) {
        return playerFacade.createPlayer(name);
    }

    public boolean execMove(PlayerFacade playerFacade, String move) {
        for (Move m : playerFacade.getMoves()) {
            if (m.toString().equals(move)) {
                ((Player)playerFacade).setMove(m);
                return true;
            }
        }
        return false;
    }

    public PlayerFacade getPlayer(String name) {
        return playerFacade.getPlayer(name);
    }
}
