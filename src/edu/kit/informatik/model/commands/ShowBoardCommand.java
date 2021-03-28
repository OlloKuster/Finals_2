package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowBoardCommand implements Command {
    private static final String REGEX_BURNING = "([*+])";
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 0) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }

        GameState gameState = session.getGameState();
        Board board = gameState.getBoard();
        Pattern burnPattern = Pattern.compile(REGEX_BURNING);
        String output = "";
        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                String cell = board.getCell(i, j);
                Matcher burnMatcher = burnPattern.matcher(cell);
                if (!burnMatcher.matches()) {
                    if (j == board.getBoardWidth() - 1) {
                        output = output + "X";
                    }
                    else {
                        output = output + "X" + ",";
                    }
                }
                else {
                    if (j == board.getBoardWidth() - 1) {
                        output = output + cell;
                    }
                    else {
                        output = output + cell + ",";
                    }
                }
                if (j == board.getBoardWidth() - 1) {
                    output = output + "\n";
                }
            }
        }
        return output;
    }
}
