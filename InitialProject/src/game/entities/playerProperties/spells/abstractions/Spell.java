package game.entities.playerProperties.spells.abstractions;

import game.entities.playerProperties.Stats;

import static constants.Constants.INITIAL_SPELL_LEVEL;

public abstract class Spell {

    private int spellLevel;
    private double manaRequired;

    public Spell(){
        this.spellLevel = INITIAL_SPELL_LEVEL;
    }

    public abstract void cast();
    public abstract void tick();

    public void levelUp() {
        this.spellLevel++;
    }

    public double getManaRequired() {
        return manaRequired;
    }

    protected void setManaRequired(double manaRequired) {
        this.manaRequired = manaRequired;
    }
}
