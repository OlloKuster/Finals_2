package edu.kit.informatik;

import edu.kit.informatik.model.firebreaker.GameException;
import edu.kit.informatik.view.Session;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws GameException {
        final Session session = new Session();
        String[] inputArgs = args[0].split(",");
        List<String> input = new LinkedList<>(Arrays.asList(inputArgs));
        session.start(input);
    }
}
