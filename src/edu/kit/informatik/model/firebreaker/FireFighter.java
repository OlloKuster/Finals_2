package edu.kit.informatik.model.firebreaker;

import java.util.Objects;

/**
 * The fire fighter class.
 * @author Oliver Kuster
 * @version 1.0
 */
public class FireFighter {
    private final String name;
    private int horPosition; // abscissa
    private int vertPosition; // ordinate
    private int actionPoints;
    private int waterPoints;
    private boolean canExtinguish;

    /**
     * Constructor of a fire fighter with name and starting position.
     * @param name Name of the figure.
     * @param horPosition Starting position hor.
     * @param vertPosition Starting position vert.
     */
    public FireFighter(final String name, int horPosition, int vertPosition) {
        this.name = name;
        this.horPosition = horPosition;
        this.vertPosition = vertPosition;
        this.actionPoints = 3;
        this.waterPoints = 3;
        this.canExtinguish = true;
    }

    /**
     * Getter for name.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the hor. position.
     * @return The hor. position
     */
    public int getHorPosition() {
        return horPosition;
    }

    /**
     * Setter for the hor. pos.
     * @param horPosition The desired hor. pos.
     */
    public void setHorPosition(int horPosition) {
        this.horPosition = horPosition;
    }

    /**
     * Getter for the vert. position.
     * @return The vert. position.
     */
    public int getVertPosition() {
        return vertPosition;
    }

    /**
     * Setter for the vert. pos.
     * @param vertPosition The desired vert. pos.
     */
    public void setVertPosition(int vertPosition) {
        this.vertPosition = vertPosition;
    }

    /**
     * Getter for the action points.
     * @return The action points.
     */
    public int getActionPoints() {
        return actionPoints;
    }

    /**
     * Setter for the action points
     * @param actionPoints The action points.
     */
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    /**
     * Getter for the water points.
     * @return Available water points.
     */
    public int getWaterPoints() {
        return waterPoints;
    }

    /**
     * Setter for the water points
     * @param waterPoints The wanted water points
     */
    public void setWaterPoints(int waterPoints) {
        this.waterPoints = waterPoints;
    }

    /**
     * Sets the flag for being able to extinguish.
     * @param canExtinguish Whether or not the unit can extinguish.
     */
    public void setCanExtinguish(boolean canExtinguish) {
        this.canExtinguish = canExtinguish;
    }

    /**
     * Getter for the extinguish flag.
     * @return Whether or not the unit can extinguish.
     */
    public boolean isCanExtinguish() {
        return canExtinguish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireFighter that = (FireFighter) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
