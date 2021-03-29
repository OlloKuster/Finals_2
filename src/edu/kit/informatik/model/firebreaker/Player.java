package edu.kit.informatik.model.firebreaker;

import edu.kit.informatik.control.messages.Errors;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Class of a player.
 * @author Oliver Kuster
 * @version 1.0
 */
public class Player {
    private final String name;
    private int reputationPoints;
    private List<FireFighter> ownedFireFighters;
    private boolean madeAction;

    /**
     * Constructor of a player.
     * @param name Identifier for the player.
     */
    public Player(final String name) {
        this.name = name;
        this.reputationPoints = 0;
        this.ownedFireFighters = new LinkedList<>();
        this.madeAction = false;
    }

    /**
     * Getter of the name.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the reputation points.
     * @return The reputation points.
     */
    public int getReputationPoints() {
        return reputationPoints;
    }

    /**
     * Setter for the reputation points.
     * @param reputationPoints The desired reputation points.
     */
    public void setReputationPoints(int reputationPoints) {
        this.reputationPoints = reputationPoints;
    }

    /**
     * Getter for the owned fire fighters.
     * @return The owned fire fighters.
     */
    public List<FireFighter> getOwnedFireFighters() {
        return ownedFireFighters;
    }

    /**
     * Adds a single fire fighter, instead of a full setter.
     * @param fireFighter The fire fighter to be added.
     * @throws GameException When the fire fighter is already owned.
     */
    public void addFireFighter(FireFighter fireFighter) throws GameException {
        String nameFireFighter = fireFighter.getName();
        if (this.ownedFireFighters.contains(fireFighter)) {
            throw new GameException(String.format(Errors.FIRE_FIGHTER_ALREADY_EXISTS, nameFireFighter));
        }
        this.ownedFireFighters.add(fireFighter);
    }

    /**
     * Removes a single fire fighter.
     * @param name Name of the fire fighter, so lists are more accessible.
     * @throws GameException If the fire fighter cannot be found.
     */
    public void removeFireFighter(String name) throws GameException {
        for (int i = 0; i < this.ownedFireFighters.size(); i++) {
            String fireFighterName = this.ownedFireFighters.get(i).getName();
            if (fireFighterName.equals(name)) {
                this.ownedFireFighters.remove(i);
                return;
            }
        }
        throw new GameException(Errors.FIRE_FIGHTER_NOT_FOUND);
    }

    /**
     * Getter for the action flag.
     * @return Whether or not the player has made an action yet.
     */
    public boolean isMadeAction() {
        return madeAction;
    }

    /**
     * Setter for the action flag.
     * @param madeAction The flag status.
     */
    public void setMadeAction(boolean madeAction) {
        this.madeAction = madeAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
