package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
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

public class ExtinguishCommand implements Command {
    private static final String REGEX_CANNOT_EXTINGUISH = "(w|[A-Z])";
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
            Board board = currentState.getBoard();
            this.extinguish(board, targetRow, targetColumn);
            String cellStatus = board.getCell(targetRow, targetColumn);

            int actionPoints = fireFighter.getActionPoints();
            fireFighter.setActionPoints(--actionPoints);

            int waterPoints = fireFighter.getWaterPoints();
            fireFighter.setWaterPoints(--waterPoints);

            fireFighter.setCanExtinguish(false);

            if (cellStatus.equals("d")) {
                Player currentPlayer = currentState.getCurrentPlayer();
                int repPoints = currentPlayer.getReputationPoints();
                currentPlayer.setReputationPoints(++repPoints);
            }
            return (String.format("%s,%d", cellStatus, actionPoints));
        }
        else {
            throw new GameException(Errors.CANNOT_EXTINGUISH);
        }
    }

    private void extinguish(Board board, int row, int column) throws GameException {
        String status = board.getCell(row, column);
        switch(status) {
            case "d":
            case "+":
                board.setCell(row, column, "w");
                return;
            case "*":
                board.setCell(row, column, "+");
                return;
            default:
                throw new GameException(String.format(Errors.INVALID_CELL_STATE, status));
        }
    }

    private boolean rules(GameState currentState, FireFighter fireFighter, int row, int column) throws GameException {
        int actionPoints = fireFighter.getActionPoints();
        int waterPoints = fireFighter.getWaterPoints();
        if (!fireFighter.isCanExtinguish()) {
            throw new GameException(Errors.ALREADY_EXTINGUISHED);
        }
        if (actionPoints == 0) {
            throw new GameException(Errors.NO_ACTION_POINTS);
        }
        if (waterPoints == 0) {
            throw new GameException(Errors.NO_WATER_POINTS);
        }
        Board board = currentState.getBoard();
        String tarStatus = board.getCell(row, column);

        int curRow = fireFighter.getHorPosition();
        int curColumn = fireFighter.getVertPosition();
        int dist = Util.intAbs(row - curRow) + Util.intAbs(column - curColumn);
        if (dist > 1) {
            throw new GameException(String.format(Errors.CANNOT_REACH, row, column));
        }

        final Pattern extPattern = Pattern.compile(REGEX_CANNOT_EXTINGUISH);
        final Matcher extMatcher = extPattern.matcher(tarStatus);

        if (extMatcher.matches()) {
            throw new GameException(String.format(Errors.CANNOT_EXTINGUISH_FIELD, row, column, tarStatus));
        }

        return true;
    }
}
