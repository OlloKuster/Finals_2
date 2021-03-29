package edu.kit.informatik.model.firebreaker;

import edu.kit.informatik.control.messages.Errors;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Player {
    private final String name;
    private int reputationPoints;
    private List<FireFighter> ownedFireFighters;
    private boolean madeAction;

    public Player(final String name) {
        this.name = name;
        this.reputationPoints = 0;
        this.ownedFireFighters = new LinkedList<>();
        this.madeAction = false;
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

    public List<FireFighter> getOwnedFireFighters() {
        return ownedFireFighters;
    }

    public void setOwnedFireFighters(List<FireFighter> ownedFireFighters) {
        this.ownedFireFighters = ownedFireFighters;
    }

    public void addFireFighter(FireFighter fireFighter) throws GameException {
        String nameFireFighter = fireFighter.getName();
        if (this.ownedFireFighters.contains(fireFighter)) {
            throw new GameException(String.format(Errors.FIRE_FIGHTER_ALREADY_EXISTS, nameFireFighter));
        }
        this.ownedFireFighters.add(fireFighter);
    }

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

    public boolean isMadeAction() {
        return madeAction;
    }

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
