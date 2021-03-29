package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;

import java.util.List;

/**
 * Command to quit.
 * @author Oliver Kuster
 * @version 1.0
 */
public class QuitCommand implements Command {
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 0) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }
        session.quit();
        return null;
    }
}
