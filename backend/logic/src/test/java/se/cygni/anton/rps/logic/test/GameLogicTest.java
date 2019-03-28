package se.cygni.anton.rps.logic.test;

import org.junit.Assert;
import org.junit.Test;
import se.cygni.anton.rps.data.api.PlayerFacade;
import se.cygni.anton.rps.data.api.RockPaperScissorFacade;
import se.cygni.anton.rps.data.impl.Player;
import se.cygni.anton.rps.data.impl.RockPaperScissors;
import se.cygni.anton.rps.logic.api.facade.PlayerLogicFacade;
import se.cygni.anton.rps.logic.api.facade.RockPaperScissorLogicFacade;
import se.cygni.anton.rps.logic.api.impl.PlayerLogic;
import se.cygni.anton.rps.logic.api.impl.RockPaperScissorLogic;
import util.Move;
import util.State;

public class GameLogicTest {

    RockPaperScissorLogicFacade gameLogic = new RockPaperScissorLogic(new RockPaperScissors());
    PlayerFacade player = new Player("Erik");
    @Test
    public void testRockRock() {
        Assert.assertEquals(generateGame(Move.ROCK, Move.ROCK).getState(), State.DRAW);
    }

    @Test
    public void testScissorScissor() {
        Assert.assertEquals(generateGame(Move.SCISSOR, Move.SCISSOR).getState(), State.DRAW);
    }

    @Test
    public void testPaperPaper() {
        Assert.assertEquals(generateGame(Move.PAPER, Move.PAPER).getState(), State.DRAW);
    }

    @Test
    public void testRockPaper() {
        Assert.assertEquals(generateGame(Move.ROCK, Move.PAPER).getState(), State.PLAYER_TWO_WIN);
    }

    @Test
    public void testPaperRock() {
        Assert.assertEquals(generateGame(Move.PAPER, Move.ROCK).getState(), State.PLAYER_ONE_WIN);
    }
    @Test
    public void testRockScissor() {
        Assert.assertEquals(generateGame(Move.ROCK, Move.SCISSOR).getState(), State.PLAYER_ONE_WIN);
    }
    @Test
    public void testScissorRock() {
        Assert.assertEquals(generateGame(Move.SCISSOR, Move.ROCK).getState(), State.PLAYER_TWO_WIN);
    }
    @Test
    public void testPaperScissor() {
        Assert.assertEquals(generateGame(Move.PAPER, Move.SCISSOR).getState(), State.PLAYER_TWO_WIN);
    }
    @Test
    public void testScissorPaper() {
        Assert.assertEquals(generateGame(Move.SCISSOR, Move.PAPER).getState(), State.PLAYER_ONE_WIN);
    }

    private RockPaperScissorFacade generateGame(Move player1Move, Move player2Move) {
        RockPaperScissorFacade game = gameLogic.createGame("ester");
        player.setMove(player2Move);
        game.getPlayer1().setMove(player1Move);
        game.addPlayerToGame(game, player);
        gameLogic.updateGameResult(game);
        return game;
    }

    private String makeJsonGameobject() {
        return "";
    }

}
