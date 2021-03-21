package edu.kit.informatik.model.firebreaker;

public class FireFighter {
    private int horPosition; // abscissa
    private int vertPosition; // ordinate
    private int actionPoints;

    public FireFighter(int horPosition, int vertPosition) {
        this.horPosition = horPosition;
        this.vertPosition = vertPosition;
        this.actionPoints = 0;
    }
}
