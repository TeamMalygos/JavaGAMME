package game.entities.playerProperties.spells.implementations;


import game.entities.playerProperties.Stats;
import game.entities.playerProperties.spells.abstractions.StatsDependantSpell;

import static constants.Constants.*;

public class Heal extends StatsDependantSpell {

    private int healAmount;

    public Heal(Stats playerStats) {
        super(playerStats);
        this.setHealAmount(HEAL_SPELL_INITIAL_HEAL_AMOUNT);
        this.setManaRequired(HEAL_SPELL_INITIAL_MANA_REQUIRED);
    }

    @Override
    public void cast() {
        if (super.getPlayerStats().getCurrentMana() >= super.getManaRequired()) {
            super.getPlayerStats().gainHealth(this.getHealAmount());
            super.getPlayerStats().setCurrentMana(super.getPlayerStats().getCurrentMana() - super.getManaRequired());
            System.out.println("Heal spell cast! " + this.getHealAmount() + " health gained!");
        } else {
            System.out.println(INSUFFICIENT_MANA);
        }
    }

    @Override
    public void tick() {}

    @Override
    public void levelUp() {
        super.levelUp();
        this.healAmount += HEAL_SPELL_LEVEL_UP_AMOUNT_INCREASE;
    }

    private int getHealAmount() {
        return this.healAmount;
    }

    private void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }
}
