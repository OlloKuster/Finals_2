package edu.kit.informatik.model.commands.gameplay;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

/**
 * Command to buy a fire engine aka fire fighter.
 * @author Oliver Kuster
 * @version 1.0
 */
public class BuyFireEngineCommand implements Command {
    private static final int REQ_REP_POINTS = 5;

    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 1) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }
        String name = arguments.get(0);
        GameState gameState = session.getGameState();
        Player player = gameState.getPlayerByName(name);

        if (rules(gameState)) {
            int repPoints = player.getReputationPoints();
            repPoints -= REQ_REP_POINTS;
            player.setReputationPoints(repPoints);
            player.setMadeAction(true);
            return (String.format("%d", repPoints));
        }
        else {
            throw new GameException(Errors.CANNOT_BUY);
        }
    }

    private boolean rules(GameState gameState) throws GameException {
        Player currentPlayer = gameState.getCurrentPlayer();
        int reputationPoints = currentPlayer.getReputationPoints();
        if (reputationPoints < REQ_REP_POINTS) {
            throw new GameException(Errors.NOT_ENOUGH_REP);
        }
        return true;
    }
}