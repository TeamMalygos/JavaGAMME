package game.entities.playerProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import game.entities.playerProperties.spells.abstractions.Spell;
import game.entities.playerProperties.spells.implementations.SpellFactory;
import utils.LootBag;

import static constants.Constants.*;

@SuppressWarnings("serial")
public class Stats implements Serializable {

	private LootBag bag;
	
    private String playerName;

    private double damage;

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

    private Map<String, Spell> spells;
    private int spellLevelUpPoints;
    private SpellFactory spellFactory;

    public Stats(String playerName) {
        this.playerName = playerName;
        this.bag = new LootBag();
        this.init();
    }

    private void init() {
        this.damage = INITIAL_DAMAGE;
        this.health = this.currentHealth = INITIAL_HEALTH;
        this.healthRegeneraionRate = INITIAL_HEALTH_REGENERATION_RATE;

        this.mana = this.currentMana = INITIAL_MANA;
        this.manaRegenerationRate = INITIAL_MANA_REGENERATION_RATE;

        this.regenerationTimer = 0;

        this.armor = INITIAL_ARMOR;

        this.level = INITIAL_PLAYER_LEVEL;
        this.currentExperience = INITIAL_PLAYER_EXPERIENCE;
        this.totalLevelExperience = INITIAL_LEVEL_TOTAL_EXPERIENCE;

        this.spells = new HashMap<>();
        this.spellFactory = new SpellFactory();
        this.spellLevelUpPoints = 5;
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
        if (this.spells.containsKey(spellType)) {
            this.levelUpSpell(spellType);
            return;
        }
        if (this.spellLevelUpPoints >= 1) {
            this.spells.putIfAbsent(spellType, this.spellFactory.getSpell(spellType, this));
            this.spellLevelUpPoints--;
            System.out.println(spellType + " spell learned!");
        } else {
            System.out.println(INSUFFICIENT_POINTS);
        }
    }

    public void levelUpSpell(String spellType) {
        if (this.spellLevelUpPoints >= 1) {
            this.spells.get(spellType).levelUp();
            this.spellLevelUpPoints--;
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
        if (this.currentHealth + healthAmount > this.health) {
            this.currentHealth = this.health;
        } else {
            this.currentHealth += healthAmount;
        }
        System.out.println("Gained " + healthAmount + " health.");
        System.out.println("Current health: " + currentHealth);
    }

    public void gainMana(int manaAmount) {
        if (this.currentMana + manaAmount > this.mana) {
            this.currentMana = this.mana;
        } else {
            this.currentMana += manaAmount;
        }
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
        this.currentHealth += (this.currentHealth * this.healthRegeneraionRate);
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
    public void setBag(LootBag b){
    	this.bag = b;
    }
    public LootBag getBag(){
    	return this.bag;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
    
    public int getLevel(){
    	return this.level;
    }

    public Map<String, Spell> getSpells() {
        return spells;
    }
}