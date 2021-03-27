package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

public class RefillCommand implements Command {
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 1) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }
        String name = arguments.get(0);
        GameState currentState = session.getGameState();
        FireFighter fireFighter = currentState.getFigureByName(name);

        if (rules(currentState, fireFighter)) {
            int waterPoints = fireFighter.getWaterPoints();
            fireFighter.setWaterPoints(--waterPoints);
            int actionPoints = fireFighter.getActionPoints();
            return String.format("%d", actionPoints);
        }
        else {
            throw new GameException(Errors.CANNOT_REFILL);
        }

    }

    private boolean rules(GameState currentState, FireFighter fireFighter) {
        return true;
    }
}
