package edu.kit.informatik.model.commands.state;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

import static edu.kit.informatik.model.commands.state.Initialise.*;

/**
 * Initialises a new game state
 * @author Oliver Kuster
 * @version 1.0
 */
public class InitialiseCommand implements Command {

    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() <= 2) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }

        try {
            Integer.parseInt(arguments.get(0));
            Integer.parseInt(arguments.get(1));
        } catch (NumberFormatException nfe) {
            throw new GameException(Errors.INVALID_ARGUMENT);
        }

        Board board = initialiseBoard(arguments);
        List<Player> players = initialisePlayers();
        List<FireFighter> fireFighters = initialiseFireFighters(arguments, players);

        GameState gameState = new GameState(players, board, fireFighters);

        if (session.getGameState() != null) {
            throw new GameException(Errors.GAME_STILL_RUNNING);
        }
        else {
            session.setGameState(gameState);
        }
        return null;
    }
}
