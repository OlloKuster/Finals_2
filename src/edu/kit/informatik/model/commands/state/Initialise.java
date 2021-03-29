package edu.kit.informatik.model.commands.state;

import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class to initialise a game state.
 * @author Oliver Kuster
 * @version 1.0
 */
public abstract class Initialise {
    /** Dimensions of the game, just a formality for easier adjustments */
    static final int DIMENSIONS = 2;
    /** Players and their names */
    static final String[] PLAYERS = {"A", "B", "C", "D"};
    private static final String REGEX_FIREFIGHTER_NAME = "[A-D][0-9]+";

    private Initialise() {

    }


    /**
     * Initialises a board.
     * @param arguments The input arguments.
     * @return The initialised board given by the arguments.
     * @throws GameException If the input is wrong.
     */
    static Board initialiseBoard(List<String> arguments) throws GameException {
        int pos = DIMENSIONS;
        int width = Integer.parseInt(arguments.get(0));
        int height = Integer.parseInt(arguments.get(1));

        if (width % 2 == 0) {
            throw new GameException(Errors.EVEN_WIDTH);
        }
        if (height % 2 == 0) {
            throw new GameException(Errors.EVEN_HEIGHT);
        }
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

    /**
     * Initialises the players.
     * @return The list of players in the game.
     */
    static List<Player> initialisePlayers() {
        List<Player> playerList = new LinkedList<>();
        for (String playerName : PLAYERS) {
            Player player = new Player(playerName);
            playerList.add(player);
        }
        return playerList;
    }

    /**
     * Initialises the fire fighters. Has to be done AFTER the players, else you cannot assign the
     * fire fighters to the players.
     * @param arguments Input arguments, extract where the fire fighters are placed
     * @param players The players in the game
     * @return A list of the fire fighters in game.
     * @throws GameException If the players and fire fighters don't match
     */
    static List<FireFighter> initialiseFireFighters(List<String> arguments, List<Player> players) throws GameException {
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
                    String ownerName = cell.substring(0, cell.length() - 1);
                    for (Player currPlayer : players) {
                        if (currPlayer.getName().equals(ownerName)) {
                            currPlayer.addFireFighter(fireFighter);
                        }
                    }
                    fireFighterList.add(fireFighter);
                }
                pos++;
            }
        }

        return fireFighterList;
    }
}
