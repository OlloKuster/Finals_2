package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;

import java.util.List;

public class FireToRollCommand implements Command {

    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        return null;
    }
}
