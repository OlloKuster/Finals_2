package edu.kit.informatik.view;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.model.commands.InitialiseCommand;
import edu.kit.informatik.model.data.Pair;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.game.GameState;

import java.util.List;

/**
 * A session for the game.
 * @author Oliver Kuster
 * @version 1.0
 */
public class Session {
    private GameState gameState;
    private boolean quit;

    /**
     * Constructor to ensure an empty game state at the start.
     */
    public Session() {
        this.gameState = null;
    }

    /**
     * Starts a new game.
     * @param initialInput Initial input to construct the board.
     */
    public void start(List<String> initialInput) throws GameException {
        Command initialise = new InitialiseCommand();
        initialise.execute(this, initialInput);
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

        final Command command = Command.fromString(parsedInput.getFirst());
        final List<String> arguments = parsedInput.getSecond();

        return command.execute(this, arguments);
    }

    public void quit() {
        this.quit = true;
    }


    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
