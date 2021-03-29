package edu.kit.informatik.view;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.model.commands.state.InitialiseCommand;
import edu.kit.informatik.model.data.Pair;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.game.GameState;
import edu.kit.informatik.view.game.ValidateSetup;

import java.util.List;

/**
 * A session for the game.
 * @author Oliver Kuster
 * @version 1.0
 */
public class Session {
    private final List<String> initialInput;
    private GameState gameState;
    private boolean quit;

    /**
     * Constructor to ensure an empty game state at the start.
     * @param initialInput Initial input to construct the board.
     */
    public Session(List<String> initialInput) {
        this.initialInput = initialInput;
        this.gameState = null;
    }

    /**
     * Starts a new game.
     */
    public void start() throws GameException {

        Command initialise = new InitialiseCommand();
        initialise.execute(this, this.initialInput);
        ValidateSetup validator = new ValidateSetup();
        try {
            validator.validateSetup(this, initialInput);
        }
        catch (final GameException e) {
            Terminal.printError(e.getMessage());
            quit();
        }

        while (!this.quit) {
            final String input = Terminal.readLine();
            final String output;
            try {
                output = this.process(input);
            } catch (final GameException e) {

                Terminal.printError(e.getMessage());
                continue;
            }

            if (output != null) {
                Terminal.printLine(output);
            }
        }

        this.quit = false;
    }

    private String process(final String input) throws GameException {
        Parser parser = new Parser();
        Pair<String, List<String>> parsedInput = parser.parse(input);

        final Command command = Command.fromString(parsedInput.getFirst(), this.gameState);
        final List<String> arguments = parsedInput.getSecond();

        return command.execute(this, arguments);
    }

    /**
     * Method to quit the console.
     */
    public void quit() {
        this.quit = true;
    }

    /**
     * Returns the current game state.
     * @return The game state.
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Sets the current game state.
     * @param gameState The game state.
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * The getter for the initial input.
     * @return The initial input.
     */
    public List<String> getInitialInput() {
        return initialInput;
    }

    /**
     * Method to reset the session.
     * @param gameState The game state which will be pulled.
     */
    public void reset(GameState gameState) {
        this.gameState = gameState;
    }
}
