package game.entities;


import constants.Constants;

import static constants.Constants.*;

public class Stats {

    private Player player;

    private double health;
    private double currentHealth;
    private double healthRegeneraionRate;

    private double mana;
    private double currentMana;
    private double manaRegenerationRate;

    private int regenerationTimer;

    private double armor;

    private int level;

    private int currentExperience;
    private int totalLevelExperience;

    public Stats(Player player) {
        this.player = player;

        this.init();
    }

    private void init() {
        this.health = this.currentHealth = INITIAL_HEALTH;
        this.healthRegeneraionRate = INITIAL_HEALTH_REGENERATION_RATE;

        this.mana = this.currentMana = INITIAL_MANA;
        this.manaRegenerationRate = INITIAL_MANA_REGENERATION_RATE;

        this.regenerationTimer = 0;

        this.armor = INITIAL_ARMOR;

        this.level = INITIAL_PLAYER_LEVEL;
        this.currentExperience = INITIAL_PLAYER_EXPERIENCE;
        this.totalLevelExperience = INITIAL_LEVEL_TOTAL_EXPERIENCE;
    }

    public void tick() {

        calculateLevel();

        regenerationTimer++;

        if (regenerationTimer >= REGENERATION_TIME) {
            regenerateHealth();
            regenerateMana();

            regenerationTimer = 0;
        }

    }

    public void calculateLevel() {
        if (this.currentExperience >= this.totalLevelExperience) {
            level++;
            totalLevelExperience *= TOTAL_LEVEL_EXPERIENCE_INCREMENT_RATE;
            health += LEVEL_UP_HEALTH_INCREASE;
            mana += LEVEL_UP_MANA_INCREASE;

            // We will later decide wether currents stats will fully regenerate on level up
            currentHealth = health;
            currentMana = mana;
        }
    }

    public void regenerateHealth() {
        this.currentHealth += (this.currentHealth * this.healthRegeneraionRate);
    }

    public void regenerateMana() {
        this.currentMana += (this.currentMana * this.manaRegenerationRate);
    }

    public void takeDamage(double damage) {
        this.currentHealth -= (damage - this.armor * DAMAGE_REDUCE_RATE);
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
