package edu.kit.informatik.control.messages;

public class Errors {
    /** Error if the input status is not a valid cell state. **/
    public static final String INVALID_CELL_STATE = "'%s' is not a valid state";
    /** Error if the input width is not big enough for the board. **/
    public static final String NOT_WIDE_ENOUGH = "'%d' is too small of a width, width needs to be at least '%d'"
            + " or greater";
    /** Error if the input height is not big enough for the board. **/
    public static final String NOT_HIGH_ENOUGH = "'%d' is too small of a height, height needs to be at least '%d'"
            + " or greater";
    /** Error if the input asks for a cell out of scope. **/
    public static final String OUT_OF_SCOPE = "specified cell is out of scope.";
}
