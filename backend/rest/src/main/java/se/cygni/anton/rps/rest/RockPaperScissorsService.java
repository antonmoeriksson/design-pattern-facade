package se.cygni.anton.rps.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.cygni.anton.rps.data.api.RockPaperScissorFacade;
import se.cygni.anton.rps.logic.api.facade.RockPaperScissorLogicFacade;
import se.cygni.anton.rps.logic.api.impl.RockPaperScissorLogic;
import se.cygni.anton.rps.data.impl.*;

@RestController
@RequestMapping("api")
public class RockPaperScissorsService {
    private static final String jsonErrorMessage = "{\"ErrorMessage\": \"%s\"}";
    private final Gson gson = new Gson();

    private final RockPaperScissorLogicFacade rockPaperScissorLogicFacade = new RockPaperScissorLogic(new RockPaperScissors());

    @RequestMapping(value = "/game", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity createGame(@RequestBody String name) {
        name = unMarshalJson(name, "name");
        String gameJson;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            gameJson = gson.toJson(rockPaperScissorLogicFacade.createGame(name));

        } catch (Exception e) {
            httpStatus = HttpStatus.EXPECTATION_FAILED;
            return new ResponseEntity(createErrorMessage("Error in create game"), httpStatus);

        }
        return new ResponseEntity(gameJson, httpStatus);
    }


    @RequestMapping(value = "/game/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity getGame(@PathVariable String id) {
        HttpStatus httpStatus = HttpStatus.OK;
        RockPaperScissorFacade game;
        try {
            game = rockPaperScissorLogicFacade.getGame(id);
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity(createErrorMessage("Error in getGame, possibly bad ID."), httpStatus);

        }
        return new ResponseEntity(game, httpStatus);
    }


    @RequestMapping(value = "/game/{id}/move", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity execMove(@PathVariable String id, @RequestBody String jsonBody) {
        try {
            String name = unMarshalJson(jsonBody, "name");
            String move = unMarshalJson(jsonBody, "move");
            rockPaperScissorLogicFacade.performMove(id, name, move);
            return new ResponseEntity(rockPaperScissorLogicFacade.getGame(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(createErrorMessage("Invalid input args"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/game/{id}/join", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity joinGame(@PathVariable String id, @RequestBody String name) {
        name = unMarshalJson(name, "name");
        try {
            return new ResponseEntity(rockPaperScissorLogicFacade.addPlayerToGame(id, name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(createErrorMessage("Unable to join game."), HttpStatus.BAD_REQUEST);
        }
    }

    private String unMarshalJson(String input, String paramName) {
        JsonObject jsonData = new Gson().fromJson(input, JsonObject.class);
        return jsonData.get(paramName).getAsString();
    }

    private String createErrorMessage(String errorMessage) {
        return String.format(jsonErrorMessage, errorMessage);
    }
}
