package edu.kit.informatik.view.game;

import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.control.util.Util;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateSetup {
    private boolean validSetup;

    public ValidateSetup() {
        this.validSetup = false;
    }

    public void validateSetup(Session session, List<String> arguments) throws GameException {
        List<String> boardArgs = new LinkedList<>();
        for (int i = 2; i < arguments.size(); i++) {
            boardArgs.add(arguments.get(i));
        }
        GameState gameState = session.getGameState();
        Board board = gameState.getBoard();
        List<FireFighter> fireFighterList = gameState.getFigures();

        if (validFireStations(board, boardArgs) && validLakes(board, boardArgs)
                && validFirefighters(board, fireFighterList, boardArgs) && validForest(boardArgs)) {
            this.validSetup = true;
        }
    }

    private boolean validFireStations(Board board, List<String> arguments) throws GameException {
        final String fireStationName = "[A-D]";
        List<String> queue = new LinkedList<>();
        for (int i = 0; i < arguments.size(); i++) {
            queue.add(arguments.get(i));
        }
        int reqHeight = board.getBoardHeight() - 1;
        int reqWidth = board.getBoardWidth() - 1;

        if (!board.getCell(0, 0).equals("A")) {
            throw new GameException(String.format(Errors.NO_STATION, "A", 0, 0));
        }
        else {
            Util.pop(queue, "A");
        }
        if (!board.getCell(reqHeight, reqWidth).equals("B")) {
            throw new GameException(String.format(Errors.NO_STATION, "B", reqWidth, reqHeight));
        }
        else {
            Util.pop(queue, "B");
        }
        if (!board.getCell(reqHeight, 0).equals("C")) {
            throw new GameException(String.format(Errors.NO_STATION, "C", 0, reqHeight));
        }
        else {
            Util.pop(queue, "C");
        }
        if (!board.getCell(0, reqWidth).equals("D")) {
            throw new GameException(String.format(Errors.NO_STATION, "D", reqWidth, 0));
        }
        else {
            Util.pop(queue, "D");
        }

        Pattern namePattern = Pattern.compile(fireStationName);
        for (String s : queue) {
            Matcher nameMatcher = namePattern.matcher(s);
            if (nameMatcher.matches()) {
                throw new GameException(String.format(Errors.PLACED_WRONG, s));
            }
        }
        return true;
    }

    private boolean validLakes(Board board, List<String> arguments) throws GameException {
        final String lakeName = "L";
        List<String> queue = new LinkedList<>();
        int boardHeight = board.getBoardHeight() - 1;
        int boardWidth = board.getBoardWidth() - 1;
        int reqHeight = boardHeight / 2;
        int reqWidth = boardWidth / 2;

        for (int i = 0; i < arguments.size(); i++) {
            queue.add(arguments.get(i));
        }

        if (!board.getCell(reqWidth, 0).equals(lakeName)) {
            throw new GameException(String.format(Errors.NO_LAKE, reqWidth, 0));
        }
        if (!board.getCell(boardWidth, reqHeight).equals(lakeName)) {
            throw new GameException(String.format(Errors.NO_LAKE, boardWidth, reqHeight));
        }
        if (!board.getCell(reqWidth, boardHeight).equals(lakeName)) {
            throw new GameException(String.format(Errors.NO_LAKE, reqWidth, boardHeight));
        }
        if (!board.getCell(0, reqHeight).equals(lakeName)) {
            throw new GameException(String.format(Errors.NO_LAKE, 0, reqHeight));
        }

        int lakeCounter = 0;
        for (String s : queue) {
            if (s.equals(lakeName)) {
                lakeCounter++;
            }
        }

        if (lakeCounter != 4) {
            throw new GameException(Errors.WRONG_NUMBER_LAKES);
        }
        return true;
    }

    private boolean validFirefighters(Board board, List<FireFighter> fireFighterList, List<String> arguments)
            throws GameException {
        final String fireFighterName = "[A-D][0-9]+";
        int reqHeight = board.getBoardHeight() - 2;
        int reqWidth = board.getBoardWidth() - 2;
        List<String> queue = new LinkedList<>();
        for (int i = 0; i < arguments.size(); i++) {
            queue.add(arguments.get(i));
        }
        for (FireFighter fireFighter : fireFighterList) {
            String name = fireFighter.getName();
            int curRow = fireFighter.getHorPosition();
            int curColumn = fireFighter.getVertPosition();
            if (name.equals("A0") && curRow != 1 && curColumn != 1) {
                throw new GameException(String.format(Errors.FIREFIGHTER_MISPLACED, "A0", 0, 0));
            }
            if (name.equals("B0") && curRow != reqWidth && curColumn != reqHeight) {
                throw new GameException(String.format(Errors.FIREFIGHTER_MISPLACED, "B0", reqWidth, reqHeight));
            }
            if (name.equals("C0") && curRow != reqHeight && curColumn != 1) {
                throw new GameException(String.format(Errors.FIREFIGHTER_MISPLACED, "C0", 1, reqHeight));
            }
            if (name.equals("D0") && curRow != 1 && curColumn != reqWidth) {
                throw new GameException(String.format(Errors.FIREFIGHTER_MISPLACED, "C0", 1, reqHeight));
            }
            Util.pop(queue, name);
        }

        Pattern namePattern = Pattern.compile(fireFighterName);
        for (String s : queue) {
            Matcher nameMatcher = namePattern.matcher(s);
            if (nameMatcher.matches()) {
                throw new GameException(String.format(Errors.PLACED_WRONG, s));
            }
        }
        return true;

    }

    private boolean validForest(List<String> argument) throws GameException {
        final String validCells = "[A-D][0-9]*|L|\\+|\\*|d|w";
        int normalBurnCounter = 0;
        int strongBurnCounter = 0;
        Pattern namePattern = Pattern.compile(validCells);
        for (String cell : argument) {
            Matcher nameMatcher = namePattern.matcher(cell);
            if (!nameMatcher.matches()) {
                throw new GameException(Errors.INVALID_CELL_STATE);
            }
            if (cell.equals("+")) {
                normalBurnCounter++;
            }
            if (cell.equals("*")) {
                strongBurnCounter++;
            }
        }
        if (normalBurnCounter == 0) {
            throw new GameException(Errors.NO_NORMAL_BURN);
        }
        if (strongBurnCounter == 0) {
            throw new GameException(Errors.NO_STRONG_BURN);
        }
        return true;
    }

    public boolean isValidSetup() {
        return validSetup;
    }

    public void setValidSetup(boolean validSetup) {
        this.validSetup = validSetup;
    }
}
