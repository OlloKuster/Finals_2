package edu.kit.informatik.view.game;

import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;

import java.util.List;

public class GameState {
    private final List<Player> players;
    private final Board board;
    private final List<FireFighter> figures;
    private Player currentPlayer;
    private boolean burnt;
    private boolean win;
    private boolean loss;

    public GameState(List<Player> players, Board board, List<FireFighter> figures) {
        this.players = players;
        this.board = board;
        this.figures = figures;
        this.currentPlayer = players.get(0);
        this.burnt = false;
        this.win = false;
        this.loss = false;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer () {
        return this.currentPlayer;
    }

    public Board getBoard() {
        return this.board;
    }

    public List<FireFighter> getFigures() {
        return this.figures;
    }

    public Player getPlayerByName(String name) {
        for (Player player : this.players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public FireFighter getFigureByName(String name) {
        for (FireFighter figure : this.figures) {
            if (figure.getName().equals(name)) {
                return figure;
            }
        }
        return null;
    }

    public void nextPlayer() {
        int curIndex = this.players.indexOf(this.currentPlayer);
        int numberPlayers = this.players.size();
        curIndex = (curIndex + 1) % numberPlayers;
        this.currentPlayer = this.players.get(curIndex);
    }

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

    public boolean lost() throws GameException {
        if (this.figures.size() == 0) {
            this.loss = true;
            return true;
        }
        return false;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isLoss() {
        return loss;
    }

    public void setLoss(boolean loss) {
        this.loss = loss;
    }

    public boolean isBurnt() {
        return burnt;
    }

    public void setBurnt(boolean burnt) {
        this.burnt = burnt;
    }
}
