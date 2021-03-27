package edu.kit.informatik.control.command;

import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.commands.*;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;

import java.util.List;

public interface Command {

    static Command fromString(final String string) throws GameException {
        switch(string) {
            case "move":
                return new MoveCommand();
            case "extinguish ":
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

    String execute(Session session, List<String> arguments) throws GameException;
}
