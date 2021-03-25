package edu.kit.informatik.model.firebreaker.rulebook;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.view.game.GameState;

public interface Rulebook {
    boolean checkRule(GameState gameState, Command command );
}
