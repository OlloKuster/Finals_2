package edu.kit.informatik.model.firebreaker.rulebook;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

public interface Rulebook {
    boolean checkRule(GameState gameState, List<String> arguments);
}
