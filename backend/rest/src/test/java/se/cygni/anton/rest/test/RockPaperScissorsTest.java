package se.cygni.anton.rest.test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.cygni.anton.rps.data.api.GameFacade;
import se.cygni.anton.rps.data.impl.RockPaperScissors;
import se.cygni.anton.rps.rest.RockPaperScissorsService;

public class RockPaperScissorsTest {

    private RockPaperScissorsService rockPaperScissorsService = new RockPaperScissorsService();

    @Test
    public void createGameTest() {
        ResponseEntity responseEntity = rockPaperScissorsService.createGame("{\"name\": \"ANTON\"}");
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testJoinValidGame() {
        ResponseEntity tmp = rockPaperScissorsService.createGame(marshalString("name", "ANTON"));
        String gameId = unMarshalJson(tmp.getBody().toString(), "id");
        ResponseEntity responseEntity = rockPaperScissorsService.joinGame(gameId, marshalString("name", "ANTON") );
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testJoinInvalidGame() {
        ResponseEntity responseEntity = rockPaperScissorsService.joinGame("BAD ID", marshalString("name", "BAD-NAME"));
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testValidMove() {
        ResponseEntity tmp = rockPaperScissorsService.createGame(marshalString("name", "ANTON"));
        String gameId = unMarshalJson(tmp.getBody().toString(), "id");
        tmp = rockPaperScissorsService.joinGame(gameId, marshalString("name", "erik"));
        ResponseEntity responseEntity = rockPaperScissorsService.execMove(gameId,
                "{\"name\": \"ANTON\", \"move\": \"rock\"}");
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testInvalidMove() {
        ResponseEntity tmp = rockPaperScissorsService.createGame(marshalString("name", "ANTON"));
        String gameId = unMarshalJson(tmp.getBody().toString(), "id");
        tmp = rockPaperScissorsService.joinGame(gameId, marshalString("name", "erik"));
        ResponseEntity responseEntity = rockPaperScissorsService.execMove(gameId,
                "{\"name\": \"ANTON\", \"move\": \"lizzard\"}");
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    private String unMarshalJson(String input, String paramName) {
        JsonObject jsonData = new Gson().fromJson(input, JsonObject.class);
        return jsonData.get(paramName).getAsString();
    }
    private String marshalString(String key, String value) {
        return String.format("{\"%s\": \"%s\"}", key, value);
    }
}

