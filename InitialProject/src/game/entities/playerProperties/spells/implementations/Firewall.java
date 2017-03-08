package game.entities.playerProperties.spells.implementations;


import game.entities.playerProperties.Stats;
import game.entities.playerProperties.spells.abstractions.BuffingSpell;

import static constants.Constants.*;

public class Firewall extends BuffingSpell {

    private double armorIncrease;

    public Firewall(Stats playerStats) {
        super(playerStats);
        this.armorIncrease = FIREWALL_SPELL_INITIAL_ARMOR_INCREASE;
        super.setActiveTime(FIREWALL_SPELL_INITIAL_ACTIVE_TIME);
        super.setManaRequired(FIREWALL_SPELL_INITIAL_MANA_REQUIRED);
    }

    @Override
    public void cast() {
        if (super.getPlayerStats().getCurrentMana() >= super.getManaRequired()) {
            if (!super.isActive()) {
                super.getPlayerStats().setArmor(getPlayerStats().getArmor() + this.getArmorIncrease());
                super.setActive(true);
            }
            super.setTimeCounter(super.getActiveTime());
            System.out.println("Firewall installed. Trial period activated!");
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
    public void levelUp() {
        super.levelUp();
        this.armorIncrease += FIREWALL_SPELL_LEVEL_UP_AMOUNT_INCREASE;
        if (isActive()) {
            super.getPlayerStats().setArmor(getPlayerStats().getArmor() + FIREWALL_SPELL_LEVEL_UP_AMOUNT_INCREASE);
        }
        super.setActiveTime(super.getActiveTime() + FIREWALL_SPELL_LEVEL_UP_ACTIVE_TIME_INCREASE);
    }

    @Override
    protected void debuff() {
        super.getPlayerStats().setArmor(getPlayerStats().getArmor() - this.getArmorIncrease());
        super.setActive(false);
        System.out.println("Firewall trial period over! Buy license key to continue using Firewall!");
    }

    private double getArmorIncrease() {
        return armorIncrease;
    }
}
