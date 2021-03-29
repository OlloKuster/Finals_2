package edu.kit.informatik.model.commands.gameplay;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

public class TurnCommand implements Command {
    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        GameState gameState = session.getGameState();
        gameState.nextPlayer();
        Player newCurrPlayer = gameState.getCurrentPlayer();
        resetFigures(newCurrPlayer);
        resetPlayerFlag(newCurrPlayer);
        resetBurnFlag(gameState);
        return newCurrPlayer.getName();
    }

    private void resetFigures(Player player)  {
        List<FireFighter> fireFighterList = player.getOwnedFireFighters();
        for (FireFighter fireFighter : fireFighterList) {
            fireFighter.setActionPoints(3);
        }
    }

    private void resetBurnFlag(GameState gameState) {
        Player currentPlayer = gameState.getCurrentPlayer();
        Player startingPlayer = gameState.getPlayers().get(0);
        if (currentPlayer == startingPlayer) {
            gameState.setBurnt(false);
        }
    }
    private void resetPlayerFlag(Player player) {
        player.setMadeAction(false);
    }
}
