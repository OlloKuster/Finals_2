package edu.kit.informatik.control.command;

import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;

import java.util.List;

public interface Command {

    String execute(Session session, List<String> arguments) throws GameException;
}
