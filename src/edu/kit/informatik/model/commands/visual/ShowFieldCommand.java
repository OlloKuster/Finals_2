package edu.kit.informatik.model.commands.visual;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowFieldCommand implements Command {
    private static final String REGEX_NON_BURNABLE = "[A-D]|L";
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 2) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }
        int row;
        int column;

        try {
            row = Integer.parseInt(arguments.get(0));
            column = Integer.parseInt(arguments.get(1));
        } catch (NumberFormatException nfe) {
            throw new GameException(Errors.INVALID_ARGUMENT);
        }

        String output = "";

        GameState gameState = session.getGameState();
        Board board = gameState.getBoard();
        String cell = board.getCell(row, column);
        if (cell == null) {
            throw new GameException(Errors.EMPTY_CELL);
        }

        Pattern pattern = Pattern.compile(REGEX_NON_BURNABLE);
        Matcher matcher = pattern.matcher(cell);
        if (matcher.matches()) {
            output = "d" + "," + output;
        }
        else {
            output = output + cell + ",";
        }

        List<FireFighter> fireFighterOnBoard = gameState.getFigures();

        for (FireFighter fireFighter : fireFighterOnBoard) {
            if (fireFighter.getHorPosition() == row && fireFighter.getVertPosition() == column) {
                output = output + fireFighter.getName() + ",";
            }
        }
        return output.substring(0, output.length() - 1); // remove the comma
    }
}
