package Chararcter;

import Chararcter.Item.*;
import Chararcter.Profession.Profession;
import Game.Game;

import java.util.Arrays;

public abstract class Character {
    protected double health;
    protected int strength;
    protected int intelligence;
    protected int agility;
    protected int gold;
    protected int level;
    protected Weapon weapon;
    protected Armor armor;
    protected Item[] inventory;
    protected Skill[] abilities;
    protected Profession profession;
    private boolean playerTurn;


    public Character() {
        this.inventory = null;
        this.gold = 0;
        this.level = 1;
        addBasicAttacks();
    }


    public abstract void death();
    public abstract void attack(Character character, int skillNumber);
    public void displayInventory(){
        for(int i = 0; i < this.inventory.length; i++)
            if(this.inventory[i] != null)
                System.out.println(this.inventory[i].toString());
    }

    public void addBasicAttacks(){
        abilities = new Skill[4];
        abilities[0] = new Skill("Precyzyjny atak",0.5,90,0);
        abilities[1] = new Skill("Zwykły atak",1,75,0);
        abilities[2] = new Skill("Potężny atak",2,50,0);
    }


//================================================================================================================================================
    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public abstract void setStrength(int strength);

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



    public Item[] getInventory() {
        return inventory;
    }

    public Weapon weapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Skill[] getAbilities() {
        return abilities;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }

    public void setAbilities(Skill[] abilities) {
        this.abilities = abilities;
    }
    public void setAbility(Skill ability, int abilityNumber) {
        this.abilities[abilityNumber] = ability;
    }
    public boolean isPlayerTurn() {
        return playerTurn;
    }
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
}
