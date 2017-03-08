package game.entities.playerProperties.spells.implementations;


import game.entities.playerProperties.Stats;
import game.entities.playerProperties.spells.abstractions.Spell;

import static constants.Constants.FIREWALL_SPELL_NAME;
import static constants.Constants.HEAL_SPELL_NAME;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpellFactory implements Serializable {

    public Spell getSpell(String spellType, Stats playerStats) {

        switch (spellType) {
            case HEAL_SPELL_NAME:
                return new Heal(playerStats);
            case FIREWALL_SPELL_NAME:
                return new Firewall(playerStats);
            case "HardcoreAlgorithm":
                return new HardcoreAlgorithm(playerStats);
            default:
                return null;
        }
    }
}
