package edu.kit.informatik.control.command;

import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.commands.*;
import edu.kit.informatik.model.commands.gameplay.*;
import edu.kit.informatik.model.commands.state.ResetCommand;
import edu.kit.informatik.model.commands.visual.ShowBoardCommand;
import edu.kit.informatik.model.commands.visual.ShowFieldCommand;
import edu.kit.informatik.model.commands.visual.ShowPlayerCommand;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

/**
 * Command interface to parse the inputs.
 * Some commands implement game rules, but those will be incorporated as private methods instead of a new interface
 * and are not enforced here.
 * @author Oliver Kuster
 * @version 1.0
 */
public interface Command {

    /**
     * Parses the correct command.
     * Differentiate before and after a win/loss
     * @param string The command which will be called.
     * @param gameState The current game state which will be parsed.
     * @return A new command.
     * @throws GameException When the command is not known.
     */
    static Command fromString(final String string, GameState gameState) throws GameException {
        if (gameState.isWin() || gameState.isLoss()) {
            switch (string) {
                case "show-board":
                    return new ShowBoardCommand();
                case "show-field":
                    return new ShowFieldCommand();
                case "quit":
                    return new QuitCommand();
                default:
                    throw new GameException(Errors.UNKNOWN_COMMAND_END);
            }
        }
        else {
            switch(string) {
                case "move":
                    return new MoveCommand();
                case "extinguish":
                    return new ExtinguishCommand();
                case "refill":
                    return new RefillCommand();
                case "buy-fire-engine":
                    return new BuyFireEngineCommand();
                case "fire-to-roll":
                    return new FireToRollCommand();
                case "turn":
                    return new TurnCommand();
                case "reset":
                    return new ResetCommand();
                case "show-board":
                    return new ShowBoardCommand();
                case "show-field":
                    return new ShowFieldCommand();
                case "show-player":
                    return new ShowPlayerCommand();
                case "quit":
                    return new QuitCommand();
                default:
                    throw new GameException(Errors.UNKNOWN_COMMAND);
            }
        }
    }

    /**
     * Executes the command.
     * @param session The current session of the game.
     * @param arguments The arguments passed.
     * @return The message string after a successful execution.
     * @throws GameException If anything goes wrong
     */
    String execute(Session session, List<String> arguments) throws GameException;
}
