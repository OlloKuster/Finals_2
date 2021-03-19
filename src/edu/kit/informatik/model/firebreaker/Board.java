package edu.kit.informatik.model.firebreaker;

public class Board {
    private final static int MIN_SIZE = 5;

    private final int boardWidth;
    private final int boardHeight;
    private final String[][] board;

    public Board(final int boardWidth, final int boardHeight) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.board = new String[boardWidth][boardHeight];
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public String[][] getBoard() {
        return board;
    }

    public String getCell(int abscissa, int ordinate) {
        return this.board[abscissa][ordinate];
    }

    public void setCell(int abscissa, int ordinate, String status) {
        this.board[abscissa][ordinate] = status;
    }
}
