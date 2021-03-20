package edu.kit.informatik.model.firebreaker;

/**
 * Exceptions for the game.
 * @author Oliver Kuster, structure mostly taken from Lucas Alber.
 * @version 1.0
 */
public class GameException extends Exception{
    /**
     * Constructs the error message.
     * @param message The error message.
     */
    public GameException(final String message) {
        super(message);
    }
}
