import edu.kit.informatik.model.firebreaker.Board;
import edu.kit.informatik.model.firebreaker.GameException;

public class Test {
    public static void main(String[] args) throws GameException {
        Board board = new Board(4, 4);
        board.setCell(2, 3, "ö");
        System.out.println(board.getCell(2, 3));
    }
}
