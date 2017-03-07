package game.entities.playerProperties;


import game.entities.playerProperties.spells.abstractions.Spell;
import game.entities.playerProperties.spells.implementations.SpellFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static constants.Constants.*;

public class Stats implements Serializable {

    private String playerName;

    private double health;
    private double currentHealth;
    private double healthRegenerationRate;

    private double mana;
    private double currentMana;
    private double manaRegenerationRate;

    private int regenerationTimer;

    private double armor;

    private int level;

    private int currentExperience;
    private int totalLevelExperience;

    private Map<String, Spell> spells;
    private int spellLevelUpPoints;
    private SpellFactory spellFactory;

    public Stats(String playerName) {
        this.playerName = playerName;
        this.init();
    }

    private void init() {
        this.health = this.currentHealth = INITIAL_HEALTH;
        this.healthRegenerationRate = INITIAL_HEALTH_REGENERATION_RATE;

        this.mana = this.currentMana = INITIAL_MANA;
        this.manaRegenerationRate = INITIAL_MANA_REGENERATION_RATE;

        this.regenerationTimer = 0;

        this.armor = INITIAL_ARMOR;

        this.level = INITIAL_PLAYER_LEVEL;
        this.currentExperience = INITIAL_PLAYER_EXPERIENCE;
        this.totalLevelExperience = INITIAL_LEVEL_TOTAL_EXPERIENCE;

        this.spells = new HashMap<>();
        this.spellFactory = new SpellFactory();
    }

    public void tick() {

        calculateLevel();

        regenerationTimer++;

        if (regenerationTimer >= REGENERATION_TIME) {
            regenerateHealth();
            regenerateMana();

            regenerationTimer = 0;
        }

        this.spells.values().forEach(Spell::tick);
    }

    public void learnSpell(String spellType) {
        if (this.spellLevelUpPoints >= 1) {
            this.spells.putIfAbsent(spellType, this.spellFactory.getSpell(spellType, this));
            this.spellLevelUpPoints--;
        } else {
            System.out.println(INSUFFICIENT_POINTS);
        }
    }

    public void levelUpSpell(String spellType) {
        if (this.spellLevelUpPoints >= 1) {
            this.spells.get(spellType).levelUp();
        } else {
            System.out.println(INSUFFICIENT_POINTS);
        }
    }

    public void gainExperience(int experienceAmount) {
        this.currentExperience += experienceAmount;
        System.out.println("Gained " + experienceAmount + " experience.");
        System.out.println("Current experience: " + currentExperience);
        System.out.println(totalLevelExperience - currentExperience + " experience left to level up.");
    }

    public void gainHealth(int healthAmount) {
        this.currentHealth += healthAmount;
        System.out.println("Gained " + healthAmount + " health.");
        System.out.println("Current experience: " + currentHealth);
    }

    public void gainMana(int manaAmount) {
        this.currentMana += manaAmount;
        System.out.println("Gained " + manaAmount + " mana.");
        System.out.println("Current mana: " + currentMana);
    }

    public void calculateLevel() {
        if (this.currentExperience >= this.totalLevelExperience) {
            this.levelUp();
        }
    }

    private void levelUp() {
        level++;
        totalLevelExperience *= TOTAL_LEVEL_EXPERIENCE_INCREMENT_RATE;
        health += LEVEL_UP_HEALTH_INCREASE;
        mana += LEVEL_UP_MANA_INCREASE;

        // We will later decide wether currents stats will fully regenerate on level up
        currentHealth = health;
        currentMana = mana;

        this.spellLevelUpPoints++;

        System.out.println("Leveled up to level " + level);
        System.out.println("Next level total exp needed: " + totalLevelExperience);
    }

    public void regenerateHealth() {
        this.currentHealth += (this.currentHealth * this.healthRegenerationRate);
    }

    public void regenerateMana() {
        this.currentMana += (this.currentMana * this.manaRegenerationRate);
    }

    public void takeDamage(double damage) {
        this.currentHealth -= (damage - this.armor * DAMAGE_REDUCE_RATE);
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(double currentMana) {
        this.currentMana = currentMana;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }
}