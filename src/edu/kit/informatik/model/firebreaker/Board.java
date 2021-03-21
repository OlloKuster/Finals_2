package edu.kit.informatik.model.firebreaker;

import edu.kit.informatik.control.command.parser.Parser;
import edu.kit.informatik.control.messages.Errors;

import javax.swing.*;


/**
 * The class representing a board.
 * @author Oliver Kuster
 * @version 1.0
 */
public class Board {
    // Static variables of the board and its states.
    private static final int MIN_SIZE = 5;
    // Order: Lake (4 total, same symbol), 4 fire stations (A-D), forest.
    private static final  String[] CELL_STATES = {"L", "A", "B", "C", "D", "F"};
    // Denotes which token(s) can be changed.
    // Adjustments can be made with implementing new methods if new mechanics are required.
    private static final String FOREST_TOKEN = "F";

    private final int boardWidth;
    private final int boardHeight;
    private final String[][] board;

    /**
     * Constructor of the board.
     * @param boardWidth Initialises the width of the board, >=5.
     * @param boardHeight Initialises the height of the board, >=5.
     * @throws GameException If the input width or height are too small.
     */
    public Board(final int boardWidth, final int boardHeight) throws GameException {
        if (boardWidth < MIN_SIZE) {
            throw new GameException(String.format(Errors.NOT_WIDE_ENOUGH, boardWidth, MIN_SIZE));
        }
        if (boardHeight < MIN_SIZE) {
            throw new GameException(String.format(Errors.NOT_HIGH_ENOUGH, boardHeight, MIN_SIZE));
        }
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.board = new String[boardWidth][boardHeight];
    }

    /**
     * Getter for the board width.
     * @return The width of the board.
     */
    public int getBoardWidth() {
        return boardWidth;
    }

    /**
     * Getter for the board height.
     * @return The height of the board.
     */
    public int getBoardHeight() {
        return boardHeight;
    }

    /**
     * Getter for the board.
     * @return The entire board as a matrix.
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * Getter for a specific cell.
     * @param abscissa X-Coordinate, blame Reussner.
     * @param ordinate Y-Coordinate, blame Reussner.
     * @return The status of the cell {@link Board#CELL_STATES}
     */
    public String getCell(int abscissa, int ordinate) throws GameException {
        if (abscissa > this.boardWidth || ordinate > this.boardHeight) {
            throw new GameException(Errors.OUT_OF_SCOPE);
        }
        return this.board[abscissa][ordinate];
    }

    /**
     * Sets the cell state of the board.
     * @param abscissa X-Coordinate which is to be set.
     * @param ordinate Y-Coordinate which is to be set.
     * @param status State which the cell should be put in.
     * @throws GameException If the input status is not valid.
     */
    public void setCell(int abscissa, int ordinate, String status) throws GameException {
        if (!validState(abscissa, ordinate, status)) {
            throw new GameException(String.format(Errors.INVALID_CELL_STATE, status));
        }
        this.board[abscissa][ordinate] = status;
    }

    private boolean validState(int abscissa, int ordinate, String status) throws GameException {
        // empty cells have yet to be placed
        if (this.getCell(abscissa, ordinate) == null) {
            return true;
        }
        else {
            for (String cellStatus : CELL_STATES) {
                if (cellStatus.equals(status)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void burn(int abscissa, int ordinate) {

    }
}
