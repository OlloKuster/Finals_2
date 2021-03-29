package edu.kit.informatik.model.commands.gameplay;

import edu.kit.informatik.control.command.Command;
import edu.kit.informatik.control.messages.Errors;
import edu.kit.informatik.model.data.Pair;
import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.FireFighter;
import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.model.firebreaker.Player;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.game.GameState;

import java.util.LinkedList;
import java.util.List;

/**
 * Command to spread the fire.
 * @author Oliver Kuster
 * @version 1.0
 */
public class FireToRollCommand implements Command {

    @Override
    public String execute(Session session, List<String> arguments) throws GameException {
        if (arguments.size() != 1) {
            throw new GameException(Errors.INVALID_NUMBER_ARGUMENT);
        }
        List<Pair<Integer, Integer>> toBeBurned = new LinkedList<>();
        GameState gameState = session.getGameState();
        Board board = gameState.getBoard();
        List<Player> players = gameState.getPlayers();
        Player currentPlayer = gameState.getCurrentPlayer();
        if (currentPlayer != players.get(0) || currentPlayer.isMadeAction() || gameState.isBurnt()) {
            throw new GameException(Errors.WAIT_UNTIL_ALL);
        }
        int diceRoll = Integer.parseInt(arguments.get(0));
        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                if (board.getCell(j, i).equals("*") || board.getCell(j, i).equals("+")) {
                    Pair<Integer, Integer> field = new Pair<>(j, i);
                    toBeBurned.add(field);
                }
            }
        }

        for (Pair<Integer, Integer> burningField : toBeBurned) {
            int row = burningField.getFirst();
            int column = burningField.getSecond();
            diceResult(gameState, row, column, diceRoll);
        }
        if (checkLoss(gameState)) {
            return "lose";
        }
        gameState.setBurnt(true);
        return "OK";
    }

    private void diceResult(GameState gameState, int row, int column, int diceRoll) throws GameException {
        Board board = gameState.getBoard();
        String cell = board.getCell(row, column);
        switch (diceRoll) {
            case 1:
                burn(gameState, row , column);
                if (cell.equals("*")) {
                    burn(gameState, row - 1, column);
                    burn(gameState, row + 1, column);
                    burn(gameState, row, column - 1);
                    burn(gameState, row, column + 1);
                }
                return;
            case 2:
                burn(gameState, row , column);
                if (cell.equals("*")) {
                    burn(gameState, row - 1, column);
                }
                return;
            case 3:
                burn(gameState, row , column);
                if (cell.equals("*")) {
                    burn(gameState, row, column + 1);
                }
                return;
            case 4:
                burn(gameState, row , column);
                if (cell.equals("*")) {
                    burn(gameState, row + 1, column);
                }
                return;
            case 5:
                burn(gameState, row , column);
                if (cell.equals("*")) {
                    burn(gameState, row, column - 1);
                }
                return;
            case 6:
                return;
            default:
                throw new GameException(Errors.NOT_A_DICE_ROLL);
        }
    }

    private void burn(GameState gameState, int row, int column) throws GameException {
        Board board = gameState.getBoard();
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();
        if (row > boardWidth - 1 || row < 0 || column > boardHeight - 1 || column < 0) {
            return;
        }

        String targetCell = board.getCell(row, column);


        switch (targetCell) {
            case "w":
                board.setCell(row, column, "d");
                return;
            case "d":
                board.setCell(row, column, "+");
                return;
            case "+":
            case "*":
                killFireFighter(gameState, row, column);
                board.setCell(row, column, "*");
                return;
            default:
                return; // to break the switch.
        }
    }

    private void killFireFighter(GameState gameState, int row, int column) throws GameException {
        List<FireFighter> fireFighterList = gameState.getFigures();
        List<Player> players = gameState.getPlayers();
        for (FireFighter fireFighter : fireFighterList) {
            if (fireFighter.getHorPosition() == row && fireFighter.getVertPosition() == column) {
                for (Player player : players) {
                    String fireFighterName = fireFighter.getName();
                    String ownerName = fireFighterName.substring(0, fireFighterName.length() - 1);
                    if (player.getName().equals(ownerName)) {
                        player.removeFireFighter(fireFighterName);
                        if (player.getOwnedFireFighters().size() == 0) {
                            players.remove(player);
                            break;
                        }
                    }
                }
                fireFighterList.remove(fireFighter);
                break;
            }
        }
    }


    private boolean checkLoss(GameState gameState) {
        return gameState.lost();
    }
}
