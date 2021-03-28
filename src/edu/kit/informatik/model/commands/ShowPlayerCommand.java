package edu.kit.informatik.model.commands;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowPlayerCommand implements Command {
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 0) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }
        GameState gameState = session.getGameState();
        Player currentPlayer = gameState.getCurrentPlayer();
        String playerName = currentPlayer.getName();
        List<FireFighter> fireFighterList = gameState.getFigures();
        int reputationPoints = currentPlayer.getReputationPoints();

        String output = playerName + reputationPoints;

        final String regexFirefighterName = playerName + "[0-9]+";
        final Pattern namePattern = Pattern.compile(regexFirefighterName);
        for (FireFighter fireFighter : fireFighterList) {
            final Matcher nameMatcher = namePattern.matcher(fireFighter.getName());
            if (nameMatcher.matches()) {
                String fireFighterName = fireFighter.getName();
                int waterPoints = fireFighter.getWaterPoints();
                int actionPoints = fireFighter.getActionPoints();
                int row = fireFighter.getHorPosition();
                int column = fireFighter.getVertPosition();
                output = "\n" + output + "," + fireFighterName + "," + waterPoints + ","
                        + actionPoints + "," + row + "," + column;
            }
        }
        return output;
    }
}
