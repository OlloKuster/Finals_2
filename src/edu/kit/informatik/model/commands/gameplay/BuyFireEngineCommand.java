package edu.kit.informatik.model.commands.gameplay;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command to buy a fire engine aka fire fighter.
 * @author Oliver Kuster
 * @version 1.0
 */
public class BuyFireEngineCommand implements Command {
    private static final int REQ_REP_POINTS = 5;
    private static final String REGEX_CAN_BE_PLACED = "[dw]";

    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 2) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }
        GameState gameState = session.getGameState();
        Player player = gameState.getCurrentPlayer();
        int row = Integer.parseInt(arguments.get(0));
        int column = Integer.parseInt(arguments.get(1));

        if (rules(gameState, row, column)) {
            int repPoints = player.getReputationPoints();
            repPoints -= REQ_REP_POINTS;
            player.setReputationPoints(repPoints);
            player.setMadeAction(true);
            String playerName = player.getName();
            int number = player.getOwnedFireFighters().size();
            String name = playerName + number;
            FireFighter fireFighter = new FireFighter(name, row, column);
            player.addFireFighter(fireFighter);
            List<FireFighter> fireFighterList = gameState.getFigures();
            fireFighterList.add(fireFighter);
            return (String.format("%d", repPoints));
        }
        else {
            throw new GameException(Errors.CANNOT_BUY);
        }
    }

    private boolean rules(GameState gameState, int row, int column) throws GameException {
        Player currentPlayer = gameState.getCurrentPlayer();
        Board board = gameState.getBoard();
        String status = board.getCell(row, column);
        int reputationPoints = currentPlayer.getReputationPoints();
        if (reputationPoints < REQ_REP_POINTS) {
            throw new GameException(Errors.NOT_ENOUGH_REP);
        }
        final Pattern placePattern = Pattern.compile(REGEX_CAN_BE_PLACED);
        final Matcher placeMatcher = placePattern.matcher(status);
        if (!placeMatcher.matches()) {
            throw new GameException(Errors.CANNOT_PLACE);
        }
        return true;
    }
}
