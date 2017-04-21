package game.entities.playerProperties.spells.implementations;


import game.entities.playerProperties.Stats;
import game.entities.playerProperties.spells.abstractions.BuffingSpell;

import static constants.Constants.*;

public class HardcoreAlgorithm extends BuffingSpell {

    private double damageIncrease;

    public HardcoreAlgorithm(Stats playerStats) {
        super(playerStats);
        this.damageIncrease = HARDCORE_ALGORITHM_SPELL_INITIAL_DAMAGE_INCREASE;
        super.setActiveTime(HARDCORE_ALGORITHM_SPELL_INITIAL_ACTIVE_TIME);
        super.setManaRequired(HARDCORE_ALGORITHM_SPELL_INITIAL_MANA_REQUIRED);
    }

    @Override
    public void cast() {
        if (super.getPlayerStats().getCurrentMana() >= super.getManaRequired()) {
            if (!super.isActive()) {
                super.getPlayerStats().setDamage(getPlayerStats().getDamage() + this.damageIncrease);
                super.setActive(true);
            }
            super.setTimeCounter(super.getActiveTime());
            System.out.println("Just learned a badass algorithm! Time to write some seriously damaging code!");
            System.out.println(this.damageIncrease + " damage increased!");
        } else {
            System.out.println(INSUFFICIENT_MANA);
        }
    }

    @Override
    public void tick() {
        if (super.isActive()) {
            super.setTimeCounter(super.getTimeCounter() - 1);

            if (super.getTimeCounter() <= 0) {
                this.debuff();
            }
        }
    }

    @Override
    protected void debuff() {
        super.getPlayerStats().setDamage(getPlayerStats().getDamage() - this.getDamageIncrease());
        super.setActive(false);
        System.out.println("Oh..too much Rakia last night...what was that algorithm again?");
    }
    @Override
    public void levelUp() {
        super.levelUp();
        this.damageIncrease += HARDCORE_ALGORITHM_SPELL_LEVEL_UP_AMOUNT_INCREASE;
        if (isActive()) {
            super.getPlayerStats().setDamage(getPlayerStats().getDamage() + HARDCORE_ALGORITHM_SPELL_LEVEL_UP_AMOUNT_INCREASE);
        }
        super.setActiveTime(super.getActiveTime() + HARDCORE_ALGORITHM_SPELL_LEVEL_UP_ACTIVE_TIME_INCREASE);
    }
    
    private double getDamageIncrease() {
        return damageIncrease;
    }

}
