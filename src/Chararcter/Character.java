package Chararcter;

import Chararcter.Item.Armor;
import Chararcter.Item.Weapon;

public abstract class Character {
    protected int health;
    protected int strength;
    protected int agility;
    protected int intelligence;
    //tu powinny być umiejętności
    protected int gold;
    protected int level;
    private Weapon weapon;
    private Armor armor;


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Character(int health, int strength, int agility, int intelligence, int gold, int level, Weapon weapon, Armor armor) {
        this.health = 0;
        this.strength = 0;
        this.agility = 0;
        this.intelligence = 0;
        this.gold = 0;
        this.level = 0;
        this.weapon = weapon;//zadeklaruj w weapon
        this.armor = armor;//zadeklaruj w armor
    }


    //tu ma być metoda śmierci


    //zamysł uniku jest taki aby liczyć unik co 10 pkt zręcznosci dodawać szanse na unik
    public static void chanse_to_dodge(int agility){
        double dodge=0.5;



    }





}
