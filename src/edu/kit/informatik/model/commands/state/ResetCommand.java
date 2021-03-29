package edu.kit.informatik.model.commands.state;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.control.util.Util;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import static edu.kit.informatik.model.commands.state.Initialise.*;

import java.util.List;

/**
 * Resets the game.
 * @author Oliver Kuster
 * @version 1.0
 */
public class ResetCommand implements Command {
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 0) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }

        List<String> initialSetup = session.getInitialInput();

        Board board = initialiseBoard(initialSetup);
        List<Player> players = initialisePlayers();
        List<FireFighter> fireFighters = initialiseFireFighters(initialSetup, players);
        for (Player player : players) {
            Util.resetPlayerFlag(player);
            Util.resetFigures(player);
        }
        Util.resetBurnFlag(session.getGameState());

        GameState gameState = new GameState(players, board, fireFighters);
        session.reset(gameState);
        return "OK";
    }
}
