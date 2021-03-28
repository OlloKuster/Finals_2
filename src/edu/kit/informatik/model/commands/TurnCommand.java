package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

public class TurnCommand implements Command {
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        GameState gameState = session.getGameState();
        gameState.nextPlayer();
        Player newCurrPlayer = gameState.getCurrentPlayer();
        return newCurrPlayer.getName();
    }
}
