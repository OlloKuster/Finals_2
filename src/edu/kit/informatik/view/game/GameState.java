package edu.kit.informatik.view.game;

import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;

import java.util.List;

/**
 * Game state class, which is used to pass information to the commands mostly.
 * @author Oliver Kuster
 * @version 1.0
 */
public class GameState {
    private final List<Player> players;
    private final Board board;
    private final List<FireFighter> figures;
    private Player currentPlayer;
    private boolean burnt;
    private boolean win;
    private boolean loss;

    /**
     * Constructor of the game state.
     * @param players The current players.
     * @param board The current board and its status.
     * @param figures The current figures in play.
     */
    public GameState(List<Player> players, Board board, List<FireFighter> figures) {
        this.players = players;
        this.board = board;
        this.figures = figures;
        this.currentPlayer = players.get(0);
        this.burnt = false;
        this.win = false;
        this.loss = false;
    }

    /**
     * Getter for the players.
     * @return The players.
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Getter for the currently active player.
     * @return The currently active player.
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Getter for the board.
     * @return The board.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Getter for the figures.
     * @return The figures.
     */
    public List<FireFighter> getFigures() {
        return this.figures;
    }

    /**
     * Getter for a player by name.
     * @param name The player which has to be searched.
     * @return The specified player.
     */
    public Player getPlayerByName(String name) {
        for (Player player : this.players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Getter for a figure by name.
     * @param name The figure which has to be searched.
     * @return The specified figure.
     */
    public FireFighter getFigureByName(String name) {
        for (FireFighter figure : this.figures) {
            if (figure.getName().equals(name)) {
                return figure;
            }
        }
        return null;
    }

    /**
     * Pulls up the next player in the list as the active player.
     */
    public void nextPlayer() {
        int curIndex = this.players.indexOf(this.currentPlayer);
        int numberPlayers = this.players.size();
        curIndex = (curIndex + 1) % numberPlayers;
        this.currentPlayer = this.players.get(curIndex);
    }

    /**
     * Checks whether or not the game is in a won state.
     * @return Whether or not the game is won.
     * @throws GameException From used methods.
     */
    public boolean won() throws GameException {
        int burningCells = 0;
        for (int i = 0; i < this.board.getBoardHeight(); i++) {
            for (int j = 0; j < this.board.getBoardWidth(); j++) {
                if (this.board.getCell(i, j).equals("*") || this.board.getCell(i, j).equals("+")) {
                    burningCells++;
                }
            }
        }
        if (burningCells == 0) {
            this.win = true;
            return true;
        }
        return false;
    }

    /**
     * Checks whether or not the game is in a lost state.
     * @return Whether or not the game is lost.
     */
    public boolean lost() {
        if (this.figures.size() == 0) {
            this.loss = true;
            return true;
        }
        return false;
    }

    /**
     * Gets the win flag.
     * @return If the game is won or not.
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Sets the win flag.
     * @param win Whether or not the game is won.
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * Gets the loss flag.
     * @return If the game is lost or not.
     */
    public boolean isLoss() {
        return loss;
    }

    /**
     * Sets the loss flag.
     * @param loss Whether or not the game is lost or not
     */
    public void setLoss(boolean loss) {
        this.loss = loss;
    }

    /**
     * Gets the burnt flag, if there was a spread of fire this turn already or not.
     * @return The burnt flag
     */
    public boolean isBurnt() {
        return burnt;
    }

    /**
     * Sets the burnt flag, if there was a spread of fire this turn already or not.
     * @param burnt The burnt flag
     */
    public void setBurnt(boolean burnt) {
        this.burnt = burnt;
    }
}
