package edu.kit.informatik.model.firebreaker;

import java.util.Objects;

public class FireFighter {
    private final String name;
    private int horPosition; // abscissa
    private int vertPosition; // ordinate
    private int actionPoints;
    private int waterPoints;
    private boolean canExtinguish;

    public FireFighter(final String name, int horPosition, int vertPosition) {
        this.name = name;
        this.horPosition = horPosition;
        this.vertPosition = vertPosition;
        this.actionPoints = 3;
        this.waterPoints = 3;
        this.canExtinguish = true;
    }

    public String getName() {
        return name;
    }

    public int getHorPosition() {
        return horPosition;
    }

    public void setHorPosition(int horPosition) {
        this.horPosition = horPosition;
    }

    public int getVertPosition() {
        return vertPosition;
    }

    public void setVertPosition(int vertPosition) {
        this.vertPosition = vertPosition;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public int getWaterPoints() {
        return waterPoints;
    }

    public void setWaterPoints(int waterPoints) {
        this.waterPoints = waterPoints;
    }

    public void setCanExtinguish(boolean canExtinguish) {
        this.canExtinguish = canExtinguish;
    }

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
