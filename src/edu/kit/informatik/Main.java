package edu.kit.informatik;

import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Main class.
 * @author Oliver Kuster
 * @version 1.0
 */
public class Main {
    /**
     * Main method, standard implementation.
     * @param args standard input.
     * @throws GameException from methods
     */
    public static void main(String[] args) throws GameException {
        String[] inputArgs = args[0].split(",");
        List<String> input = new LinkedList<>(Arrays.asList(inputArgs));
        final Session session = new Session(input);
        session.start();
    }
}
