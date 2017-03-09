package game.entities.playerProperties.spells.abstractions;

import game.entities.playerProperties.Stats;

import static constants.Constants.INITIAL_SPELL_LEVEL;

public abstract class Spell {

    private int spellLevel;
    private double manaRequired;
    private Stats playerStats;

    public Spell(Stats playerStats){
        this.setPlayerStats(playerStats);
        this.spellLevel = INITIAL_SPELL_LEVEL;
    }

    public abstract void cast();
    public abstract void tick();

    public void levelUp() {
        this.spellLevel++;
        System.out.println(this.getClass().getSimpleName() + " leveled up!");
    }

    public double getManaRequired() {
        return manaRequired;
    }

    protected void setManaRequired(double manaRequired) {
        this.manaRequired = manaRequired;
    }

    protected Stats getPlayerStats() {
        return this.playerStats;
    }

    private void setPlayerStats(Stats playerStats) {
        this.playerStats = playerStats;
    }
}
