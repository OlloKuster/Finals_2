package edu.kit.informatik.control.util;

import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.game.GameState;

import java.util.Iterator;
import java.util.List;

/**
 * Util class.
 * @author Oliver Kuster
 * @version 1.0
 */
public final class Util {

    /**
     * Private constructor.
     */
    private Util() {

    }

    /**
     * Not allowed to use Math.abs(), so there it is for ints.
     * @param a The input number.
     * @return The absolute value of a.
     */
    public static int intAbs(int a) {
        if (a < 0) {
            return -a;
        }
        else {
            return a;
        }
    }

    /**
     * Checking the ownership of a fire fighter, gets called a few times and I didn't want to make
     * an entire new class for it.
     * @param playerName The name of the owner.
     * @param fireFighterName The name of the fire fighter.
     * @return Whether or not the fire fighter belongs to the owner
     * @throws GameException If the fire fighter does not belong to the owner
     */
    public static boolean checkOwnership(String playerName, String fireFighterName) throws GameException {
        if (fireFighterName.startsWith(playerName)) {
            return true;
        }
        else {
            throw new GameException(String.format(Errors.NOT_OWNER, fireFighterName, playerName));
        }
    }

    /**
     * Pops the specified element out of the queue, not returning the element itself since this
     * is not necessary here.
     * @param queue The queue.
     * @param item The to be removed item from the queue.
     */
    public static void pop(List<String> queue, String item) {
        for (Iterator<String> iterator = queue.iterator(); iterator.hasNext();) {
            String s = iterator.next();
            if (item.equals(s)) {
                iterator.remove();
            }
        }
    }

    /**
     * Resets the flags of the figures of a player.
     * @param player The player whose figures are reset.
     */
    public static void resetFigures(Player player)  {
        List<FireFighter> fireFighterList = player.getOwnedFireFighters();
        for (FireFighter fireFighter : fireFighterList) {
            fireFighter.setActionPoints(3);
        }
    }

    /**
     * Resets the burn flag, so that the fire is allowed to spread again.
     * @param gameState Current game state
     */
    public static void resetBurnFlag(GameState gameState) {
        Player currentPlayer = gameState.getCurrentPlayer();
        Player startingPlayer = gameState.getPlayers().get(0);
        if (currentPlayer == startingPlayer) {
            gameState.setBurnt(false);
        }
    }

    /**
     * Resets the player flags.
     * @param player The target player.
     */
    public static void resetPlayerFlag(Player player) {
        player.setMadeAction(false);
    }
}
