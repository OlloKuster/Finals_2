package edu.kit.informatik.model.firebreaker;

public class Player {
    private final String name;
    private int reputationPoints;

    public Player(final String name) {
        this.name = name;
        this.reputationPoints = 0;
    }


    public String getName() {
        return name;
    }

    public int getReputationPoints() {
        return reputationPoints;
    }

    public void setReputationPoints(int reputationPoints) {
        this.reputationPoints = reputationPoints;
    }
}
