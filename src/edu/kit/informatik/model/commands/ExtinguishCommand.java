package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

public class ExtinguishCommand implements Command {
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
                board.setCell(row, column, "w");
            case "+":
                board.setCell(row, column, "w");
            case "*":
                board.setCell(row, column, "+");
            default:
                throw new GameException(Errors.INVALID_CELL_STATE);
        }
    }

    private boolean rules(GameState currentState, FireFighter fireFighter, int row, int column) {
        return true;
    }
}
