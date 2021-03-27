package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

public class MoveCommand implements Command {
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
        if (this.moveRules(currentState, fireFighter, targetRow, targetColumn)) {
            fireFighter.setHorPosition(targetRow);
            fireFighter.setVertPosition(targetColumn);
        }
        else {
            throw new GameException(Errors.CANNOT_MOVE);
        }
        return null;
    }

    private boolean moveRules(GameState currentState, FireFighter fireFighter, int row, int column) {
        return true;
    }
}
