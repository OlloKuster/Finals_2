package edu.kit.informatik.view.game;

import edu.kit.informatik.model.data.Pair;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.Player;

import java.util.List;

public class GameState {
    private final List<Player> players;
    private final Board board;
    private final List<FireFighter> figures;

    public GameState(List<Player> players, Board board, List<FireFighter> figures) {
        this.players = players;
        this.board = board;
        this.figures = figures;
    }

    public List<Player> getPlayers() {
        return this.players;
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
}
