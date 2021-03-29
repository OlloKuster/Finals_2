package edu.kit.informatik.model.commands.gameplay;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.control.util.Util;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RefillCommand implements Command {
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 1) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }
        String name = arguments.get(0);
        GameState currentState = session.getGameState();
        FireFighter fireFighter = currentState.getFigureByName(name);

        if (rules(currentState, fireFighter)) {
            fireFighter.setWaterPoints(3);
            int actionPoints = fireFighter.getActionPoints();
            fireFighter.setActionPoints(--actionPoints);
            Player player = currentState.getCurrentPlayer();
            player.setMadeAction(true);
            return String.format("%d", actionPoints);
        }
        else {
            throw new GameException(Errors.CANNOT_REFILL);
        }

    }

    private boolean rules(GameState currentState, FireFighter fireFighter) throws GameException {
        final String lakeName = "[AL]";
        int actionPoints = fireFighter.getActionPoints();
        Player currentPlayer = currentState.getCurrentPlayer();
        String playerName = currentPlayer.getName();
        String fireFighterName = fireFighter.getName();
        if (actionPoints == 0) {
            throw new GameException(Errors.NO_ACTION_POINTS);
        }
        int curRow = fireFighter.getHorPosition();
        int curColumn = fireFighter.getVertPosition();
        Board board = currentState.getBoard();
        Pattern refillPattern = Pattern.compile(lakeName);
        final Matcher refillMatcherUp = refillPattern.matcher(board.getCell(curRow - 1, curColumn));
        final Matcher refillMatcherUpRight = refillPattern.matcher(board.getCell(curRow - 1, curColumn + 1));
        final Matcher refillMatcherRight = refillPattern.matcher(board.getCell(curRow, curColumn + 1));
        final Matcher refillMatcherDownRight = refillPattern.matcher(board.getCell(curRow + 1, curColumn + 1));
        final Matcher refillMatcherDown = refillPattern.matcher(board.getCell(curRow + 1, curColumn));
        final Matcher refillMatcherDownLeft = refillPattern.matcher(board.getCell(curRow + 1, curColumn - 1));
        final Matcher refillMatcherLeft = refillPattern.matcher(board.getCell(curRow - 1, curColumn));
        final Matcher refillMatcherUpLeft = refillPattern.matcher(board.getCell(curRow - 1, curColumn - 1));

        // not returning the statement directly, since the rules may be adjusted and another rule may be added
        // which would require me to rewrite the entire thing in a "normal" project
        if (!refillMatcherUp.matches() && !refillMatcherUpRight.matches()
                && !refillMatcherRight.matches() && !refillMatcherDownRight.matches()
                && !refillMatcherDown.matches() && !refillMatcherDownLeft.matches()
                && !refillMatcherLeft.matches() && !refillMatcherUpLeft.matches()) {
            throw new GameException(Errors.CANNOT_REFILL);
        }

        return Util.checkOwnership(playerName, fireFighterName);

    }
}
