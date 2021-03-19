package edu.kit.informatik.control.command.parser;

import edu.kit.informatik.model.data.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IPD parser adjusted to fit a more lightweight need.
 * @author Oliver Kuster
 * @version 1.0
 */
public class Parser { private static final String REGEX_SINGLE_PARAMETER = "[^\\n\\r]";
    private static final String REGEX_SINGLE_COMMAND = "(\\S+)";
    private static final String REGEX_COMMAND = REGEX_SINGLE_COMMAND + "(?:\\s(" + REGEX_SINGLE_PARAMETER + "*))?";
    private static final String REGEX_ARGUMENT_SEPARATOR = " ";
    private static final int REGEX_GROUP_COMMAND_PARAMETER = 2;
    private static final int REGEX_GROUP_PARAMETER_PARAMETER = 2;

    /**
     * Empty constructor. Nothing required here since the Command interface does the brunt of the work.
     */
    public Parser() {
    }

    /**
     * Parses the input into a Pair of command + arguments.
     * @param input Input from the terminal.
     * @return A pair of command + arguments.
     */
    public Pair<String, List<String>> parse(final String input) {
        final Pattern commandPattern = Pattern.compile(REGEX_COMMAND);
        final Matcher commandMatcher = commandPattern.matcher(input);

        if (!commandMatcher.matches()) {
            return null;
        }

        final String commandName = commandMatcher.group(1);

        if (commandMatcher.groupCount() < 2 || commandMatcher.group(REGEX_GROUP_COMMAND_PARAMETER) == null) {
            return new Pair<>(commandName, List.of());
        }
        else {
            final String parameterName = commandMatcher.group(REGEX_GROUP_PARAMETER_PARAMETER);
            final List<String> parameterList = getParameters(parameterName);
            return new Pair<>(commandName, parameterList);
        }
    }

    /**
     * Extracts the parameters from the input.
     * @param input String input from the terminal.
     * @return The list of parameters of that input.
     */
    public List<String> getParameters(String input) {
        return new LinkedList<>(Arrays.asList(input.split(REGEX_ARGUMENT_SEPARATOR)));
    }

}
