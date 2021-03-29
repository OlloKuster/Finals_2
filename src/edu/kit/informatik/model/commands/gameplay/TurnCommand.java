package edu.kit.informatik.model.commands.gameplay;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

import static edu.kit.informatik.control.util.Util.*;

/**
 * Command to end the turn of a single player.
 * @author Oliver Kuster
 * @version 1.0
 */
public class TurnCommand implements Command {
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        GameState gameState = session.getGameState();
        gameState.nextPlayer();
        Player newCurrPlayer = gameState.getCurrentPlayer();
        resetFigures(newCurrPlayer);
        resetPlayerFlag(newCurrPlayer);
        resetBurnFlag(gameState);
        return newCurrPlayer.getName();
    }


}
