package edu.kit.informatik.view.game;

import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.Player;

import java.util.List;

public class GameState {
    private final List<Player> players;
    private final Board board;
    private final List<FireFighter> figures;
    private Player currentPlayer;
    private List<String> initialSetup;
    private boolean win;

    public GameState(List<Player> players, Board board, List<FireFighter> figures) {
        this.players = players;
        this.board = board;
        this.figures = figures;
        this.currentPlayer = players.get(0);
        this.initialSetup = null;
        this.win = false;
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
    public void won() {
        this.win = true;
    }

    public void setInitialSetup(List<String> initialSetup) {
        this.initialSetup = initialSetup;
    }

    public List<String> getInitialSetup() {
        return initialSetup;
    }
}
