package edu.kit.informatik.control.messages;

/**
 * All errors collected and kinda sorted.
 * @author Oliver Kuster
 * @version 1.0
 */
public class Errors {
    /** Error if a running game is trying to get initialised **/
    public static final String GAME_STILL_RUNNING = "there is already a game running";
    /** Error if the input status is not a valid cell state. **/
    public static final String INVALID_CELL_STATE = "'%s' is not a valid state.";
    /** Error if the input width is not big enough for the board. **/
    public static final String NOT_WIDE_ENOUGH = "'%d' is too small of a width, width needs to be at least '%d'"
            + " or greater";
    /** Error if the input height is not big enough for the board. **/
    public static final String NOT_HIGH_ENOUGH = "'%d' is too small of a height, height needs to be at least '%d'"
            + " or greater";
    /** Error if the input asks for a cell out of scope. **/
    public static final String OUT_OF_SCOPE = "specified cell is out of scope.";
    /** Error if the input is not a number on the dice. **/
    public static final String NOT_A_DICE_ROLL = "not the result of a D6-Dice.";
    /** Error if a fire tries to spread when it's not supposed to. **/
    public static final String WAIT_UNTIL_ALL = "wait until all players finished their moves.";
    /** Error if the board is initialised without a regular burning field. **/
    public static final String NO_NORMAL_BURN = "at least one burning tile is required.";
    /** Error if the board is initialised without a strong burning field. **/
    public static final String NO_STRONG_BURN = "at least one strong burning tile is required.";
    /** Error if the fire fighter already exists. **/
    public static final String FIRE_FIGHTER_ALREADY_EXISTS = "fire fighter %s already exists.";
    /** Error if fire fighter cannot be found. **/
    public static final String FIRE_FIGHTER_NOT_FOUND = "fire fighter not found.";
    /** Error if the object is placed wrong. **/
    public static final String PLACED_WRONG = "%s is placed wrong.";
    /** Error if the number of lakes is wrong. **/
    public static final String WRONG_NUMBER_LAKES = "wrong number of lakes.";
    /** Error if the fire stations are not placed proper. **/
    public static final String NO_STATION = "fire station %s is supposed to be at %dx%d.";
    /** Error if the lakes are not placed proper. **/
    public static final String NO_LAKE = "a lake is supposed to be at %dx%d.";
    /** Error if the firefighters are not placed proper. **/
    public static final String FIREFIGHTER_MISPLACED = "fire fighter %s is supposed to be at %dx%d.";
    /** Error if a board with an even width is initialised. **/
    public static final String EVEN_WIDTH = "even width is not allowed.";
    /** Error if a board with an even height is initialised. **/
    public static final String EVEN_HEIGHT = "even height is not allowed.";
    /** Error if the firefighter has no action points left. **/
    public static final String NO_ACTION_POINTS = "no action points left.";
    /** Error if the firefighter has no water points left. **/
    public static final String NO_WATER_POINTS = "no water points left.";
    /** Error if the number of arguments is wrong. **/
    public static final String INVALID_NUMBER_ARGUMENT = "invalid number of arguments.";
    /** Error if the argument is wrong. **/
    public static final String INVALID_ARGUMENT = "invalid argument.";
    /** Error if the command is invalid. **/
    public static final String UNKNOWN_COMMAND = "command not found.";
    /** Error if the command is invalid after the game is over. **/
    public static final String UNKNOWN_COMMAND_END = "command not found, game is already over.";
    /** Error if the figure can't move and it's a general case. **/
    public static final String CANNOT_MOVE = "cannot move there.";
    /** Error if the figure can't move because it would stand still. **/
    public static final String STANDING_STILL = "fire fighter would stand still.";
    /** Error if the figure is accessed by the wrong owner. **/
    public static final String NOT_OWNER = "%s cannot be used by %s, wrong owner.";
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
    /** Error if the figure can't extinguish the field. **/
    public static final String CANNOT_EXTINGUISH_FIELD = "cannot extinguish field %dx%d, because it is a %s.";
    /** Error if the figure already extinguished this turn. **/
    public static final String ALREADY_EXTINGUISHED = "this firefighter already used extinguish this turn.";
    /** Error if the figure can't refill and it's a general case. **/
    public static final String CANNOT_REFILL = "cannot refill.";
    /** Error if the can't buy a fire engine general case. **/
    public static final String CANNOT_BUY = "cannot buy.";
    /** Error if the can't buy a fire engine. **/
    public static final String NOT_ENOUGH_REP = "not enough reputation points.";
    /** Error if the engine can't be placed. **/
    public static final String CANNOT_PLACE = "cannot place fire engine here.";
    /** Error if the cell does not exist. **/
    public static final String EMPTY_CELL = "cell is not on the board";
}
