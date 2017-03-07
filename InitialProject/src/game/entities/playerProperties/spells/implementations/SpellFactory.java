package game.entities.playerProperties.spells.implementations;


import game.entities.playerProperties.Stats;
import game.entities.playerProperties.spells.abstractions.Spell;

public class SpellFactory {

    public Spell getSpell(String spellType, Stats playerStats) {

        switch (spellType) {
            case "Heal":
                return new Heal(playerStats);
            case "Firewall":
                return new Firewall(playerStats);
            default:
                return null;
        }
    }
}
