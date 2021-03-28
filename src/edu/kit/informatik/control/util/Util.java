package edu.kit.informatik.control.util;

import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.firebreaker.GameException;

public class Util {

    public static int intAbs(int a) {
        if (a < 0) {
            return -a;
        }
        else {
            return a;
        }
    }

    public static boolean checkOwnership(String playerName, String fireFighterName) throws GameException {
        if (fireFighterName.startsWith(playerName)) {
            return true;
        }
        else {
            throw new GameException(String.format(Errors.NOT_OWNER, fireFighterName, playerName));
        }
    }
}
