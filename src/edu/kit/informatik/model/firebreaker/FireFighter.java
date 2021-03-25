package edu.kit.informatik.model.firebreaker;

public class FireFighter {
    private final String name;
    private int horPosition; // abscissa
    private int vertPosition; // ordinate
    private int actionPoints;

    public FireFighter(final String name, int horPosition, int vertPosition) {
        this.name = name;
        this.horPosition = horPosition;
        this.vertPosition = vertPosition;
        this.actionPoints = 0;
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
}
