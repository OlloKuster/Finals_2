package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Initialises a new game state
 * @author Oliver Kuster
 * @version 1.0
 */
public class InitialiseCommand implements Command {
    /** Dimensions of the game, just a formality for easier adjustments */
    static final int DIMENSIONS = 2;
    /** Players and their names */
    static final String[] PLAYERS = {"A", "B", "C", "D"};
    private static final String REGEX_FIREFIGHTER_NAME = "[A-D][0-9]+";

    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() <= 2) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }

        try {
            Integer.parseInt(arguments.get(0));
            Integer.parseInt(arguments.get(1));
        } catch (NumberFormatException nfe) {
            throw new GameException(Errors.INVALID_ARGUMENT);
        }

        Board board = initialiseBoard(arguments);
        List<Player> players = initialisePlayers();
        List<FireFighter> fireFighters = initialiseFireFighters(arguments);

        GameState gameState = new GameState(players, board, fireFighters);
        gameState.setInitialSetup(arguments);

        if (session.getGameState() != null) {
            throw new GameException(Errors.GAME_STILL_RUNNING);
        }
        else {
            session.setGameState(gameState);
        }
        return null;
    }

    private Board initialiseBoard(List<String> arguments) throws GameException {
        int pos = DIMENSIONS;
        int width = Integer.parseInt(arguments.get(0));
        int height = Integer.parseInt(arguments.get(1));
        Pattern namePattern = Pattern.compile(REGEX_FIREFIGHTER_NAME);

        Board board = new Board(width, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String cell = arguments.get(pos);
                Matcher nameMatcher = namePattern.matcher(cell);
                if (nameMatcher.matches()) {
                    board.setCell(i, j, "d");
                }
                else {
                    board.setCell(i, j, arguments.get(pos));
                }
                pos++;
            }
        }
        return board;
    }

    private List<Player> initialisePlayers() {
        List<Player> playerList = new LinkedList<>();
        for (String playerName : PLAYERS) {
            Player player = new Player(playerName);
            playerList.add(player);
        }
        return playerList;
    }

    private List<FireFighter> initialiseFireFighters(List<String> arguments) {
        int pos = DIMENSIONS;
        int width = Integer.parseInt(arguments.get(0));
        int height = Integer.parseInt(arguments.get(1));
        Pattern namePattern = Pattern.compile(REGEX_FIREFIGHTER_NAME);
        List<FireFighter> fireFighterList = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String cell = arguments.get(pos);
                Matcher nameMatcher = namePattern.matcher(cell);
                if (nameMatcher.matches()) {
                    FireFighter fireFighter = new FireFighter(cell, i, j);
                    fireFighterList.add(fireFighter);
                }
                pos++;
            }
        }

        return fireFighterList;
    }
}
