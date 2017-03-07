package game.entities.playerProperties.spells.abstractions;


import game.entities.playerProperties.Stats;

public abstract class StatsDependantSpell extends Spell {

    private Stats playerStats;

    public StatsDependantSpell(Stats playerStats) {
        this.setPlayerStats(playerStats);
    }

    protected Stats getPlayerStats() {
        return this.playerStats;
    }

    private void setPlayerStats(Stats playerStats) {
        this.playerStats = playerStats;
    }
}
