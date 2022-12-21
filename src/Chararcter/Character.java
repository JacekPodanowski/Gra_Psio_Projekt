package Chararcter;

import Chararcter.Item.*;

public abstract class Character {
    protected int health;
    protected int strength;
    protected int intelligence;
    protected int agility;
    protected int gold;
    protected int level;
    private Weapon weapon;
    private Armor armor;
    private Item[] inventory;

    public Character() {
        this.health = 100;
        this.inventory = new Item[5];
        this.agility = 1;
    }

    public Character(int health, int strength, int agility, int intelligence, int gold, int level) { //do gracza
        this.inventory = new Item[5];
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.gold = gold;
        this.level = level;
        //this.weapon = mlotek; //początkowa bron to mlotek
        //this.armor = null; //początkowy armor to brak
    }


    public Character(int health, int strength, int agility, int intelligence, int gold, int level, Weapon weapon, Armor armor) { // do wroga
        this.inventory = new Item[5];
        this.health = health;
        this.strength = 0;//kurwa kondziu po co te zera tu wpisujesz, 10 min kminiłem co nie działa. (health = health a nie 0)
        this.agility = 0;//zamieniaj sam to sie nauczysz
        this.intelligence = 0;
        this.gold = 0;
        this.level = 0;
        this.weapon = weapon;
        this.armor = armor;
    }




    //public abstract void death();



    //zamysł uniku jest taki aby dać go do walki tam sie to będzie liczyć, tak uważam. Z poważaniemm jacek podanowksi




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

    public abstract void attack(Character character);

}
