package game.entities.playerProperties.spells.abstractions;


import game.entities.playerProperties.Stats;

public abstract class BuffingSpell extends StatsDependantSpell {

    private boolean isActive;
    private int activeTime;
    private int timeCounter;

    public BuffingSpell(Stats playerStats) {
        super(playerStats);
    }

    protected abstract void debuff();

    protected void setActive(boolean active) {
        isActive = active;
    }

    protected boolean isActive() {
        return isActive;
    }

    protected void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }

    protected int getActiveTime() {
        return activeTime;
    }

    protected void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }

    protected int getTimeCounter() {
        return timeCounter;
    }
}
