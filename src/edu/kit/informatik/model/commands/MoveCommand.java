package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.control.messages.Messages;
import edu.kit.informatik.control.util.Util;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveCommand implements Command {
    static final String REGEX_CANNOT_ENTER = "(\\*|\\+|w|[A-Z])";
    static final String REGEX_CANNOT_PASS = "(\\*|w|[A-Z])";

    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 3) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }

        String name = arguments.get(0);
        int targetRow = Integer.parseInt(arguments.get(1));
        int targetColumn = Integer.parseInt(arguments.get(2));

        GameState currentState = session.getGameState();
        FireFighter fireFighter = currentState.getFigureByName(name);
        if (this.rules(currentState, fireFighter, targetRow, targetColumn)) {
            fireFighter.setHorPosition(targetRow);
            fireFighter.setVertPosition(targetColumn);
            int actionPoints = fireFighter.getActionPoints();
            fireFighter.setActionPoints(--actionPoints);
            return ("OK");
        }
        else {
            throw new GameException(Errors.CANNOT_MOVE);
        }
    }

    private boolean rules(GameState currentState, FireFighter fireFighter, int row, int column) throws GameException {
        Player currentPlayer = currentState.getCurrentPlayer();
        String playerName = currentPlayer.getName();
        String fireFighterName = fireFighter.getName();
        Board board = currentState.getBoard();

        return this.checkOwnership(playerName, fireFighterName) && this.checkMove(board, fireFighter, row, column);

    }

    private boolean checkOwnership(String playerName, String fireFighterName) throws GameException {
        if (fireFighterName.startsWith(playerName)) {
            return true;
        }
        else {
            throw new GameException(String.format(Errors.NOT_OWNER, fireFighterName, playerName));
        }
    }

    private boolean checkMove(Board board, FireFighter fireFighter, int row, int column) throws GameException {
        final Pattern enterPattern = Pattern.compile(REGEX_CANNOT_ENTER);
        final Pattern passPattern = Pattern.compile(REGEX_CANNOT_PASS);
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();
        int actionPoints = fireFighter.getActionPoints();

        if (actionPoints == 0) {
            throw new GameException(Errors.NO_ACTION_POINTS);
        }

        if (row < -1 || row >= boardWidth || column < -1 || column >= boardHeight) {
            throw new GameException(String.format(Errors.NOT_ON_BOARD, row, column));
        }
        String tarCell = board.getCell(row, column);
        final Matcher enterMatcher = enterPattern.matcher(tarCell);
        if (enterMatcher.matches()) {
            throw new GameException(String.format(Errors.CANNOT_ENTER, row, column, tarCell));
        }

        int curRow = fireFighter.getHorPosition();
        int curColumn = fireFighter.getVertPosition();

        int dist = Util.intAbs(row - curRow) + Util.intAbs(column - curColumn);

        if (dist > 2) {
            throw new GameException(String.format(Errors.OUT_OF_RANGE, row, column, fireFighter.getName()));
        }
        if (Util.intAbs(row - curRow) == Util.intAbs(column -  curColumn)) {
            String horTarField = board.getCell(row, curColumn);
            String vertTarField = board.getCell(curRow, column);
            final Matcher passMatcherHor = passPattern.matcher(horTarField);
            final Matcher passMatcherVert = passPattern.matcher(vertTarField);
            if (passMatcherHor.matches() && passMatcherVert.matches()) {
                throw new GameException(String.format(Errors.CANNOT_REACH, row, column));
            }
        }

        return true;
    }
}
