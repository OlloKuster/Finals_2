package edu.kit.informatik.control.messages;

public class Errors {
    /** Error if a running game is trying to get initialised **/
    public static final String GAME_STILL_RUNNING = "there is already a game running";
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
    /** Error if the firefighter has no action points left. **/
    public static final String NO_ACTION_POINTS = "no action points left.";
    /** Error if the firefighter has no water points left. **/
    public static final String NO_WATER_POINTS = "no water points left.";
    /** Error if the input asks for a cell out of scope. **/
    public static final String NOT_FOREST_CELL = "cell is not a forest cell and cannot burn.";
    /** Error if a change to an empty cell is made. **/
    public static final String EMPTY_CELL_CHANGE = "cell at '%d' x '%d' is not set yet.";
    /** Error if the number of arguments is wrong. **/
    public static final String INVALID_NUMBER_ARGUMENT = "invalid number of arguments.";
    /** Error if the argument is wrong. **/
    public static final String INVALID_ARGUMENT = "invalid argument.";
    /** Error if the command is invalid. **/
    public static final String UNKNOWN_COMMAND = "command not found.";
    /** Error if the figure can't move and it's a general case. **/
    public static final String CANNOT_MOVE = "cannot move there.";
    /** Error if the figure can't move and it's a general case. **/
    public static final String NOT_OWNER = "%s cannot be moved by %s, wrong owner.";
    /** Error if the figure cannot reach the target field because of distance. **/
    public static final String OUT_OF_RANGE = "the field %dx%d is too far away from %s.";
    /** Error if the figure can't end there. **/
    public static final String CANNOT_ENTER = "the field %dx%d cannot be entered because it is marked as %s.";
    /** Error if there is no valid path to the target. **/
    public static final String CANNOT_REACH = "the field %dx%d cannot be reached.";
    /** Error if the target field is not on the board. **/
    public static final String NOT_ON_BOARD = "the field %dx%d is not on the board.";
    /** Error if the figure can't extinguish the field and it's a general case. **/
    public static final String CANNOT_EXTINGUISH = "cannot extinguish there.";
    /** Error if the figure can't extinguish the field and it's a general case. **/
    public static final String CANNOT_EXTINGUISH_FIELD = "cannot extinguish field %dx%d.";
    /** Error if the figure can't refill and it's a general case. **/
    public static final String CANNOT_REFILL = "cannot refill there.";
    /** Error if the can't buy a fire engine. **/
    public static final String CANNOT_BUY = "cannot buy.";
    /** Error if the can't buy a fire engine. **/
    public static final String NOT_ENOUGH_REP = "not enough reputation points.";
    /** Error if the cell does not exist. **/
    public static final String EMPTY_CELL = "cell is not on the board";
}
