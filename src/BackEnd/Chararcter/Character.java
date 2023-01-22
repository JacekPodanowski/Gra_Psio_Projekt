package BackEnd.Chararcter;

import BackEnd.Chararcter.Item.Armor;
import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Chararcter.Profession.Profession;

public abstract class Character {
    protected double health=100;
    public double maxHealth=150.0;
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
    private boolean playerTurn = true;
    private boolean usedSpecial = false;
    private boolean attackAvoided = false;


    public Character() {
        this.armor = new Armor();
        this.weapon = new Weapon();
        this.inventory = new Item[5];
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

    public void healMissingHealth(int procent){
        double diff = maxHealth-health;
        health = health+diff*procent/100;
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

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
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
    public boolean getUsedSpecial() {
        return usedSpecial;
    }
    public void setUsedSpecial(boolean usedSpecial) {
        this.usedSpecial = usedSpecial;
    }
    public boolean isAttackAvoided() {
        return attackAvoided;
    }
    public void setAttackAvoided(boolean attackAvoided) {
        this.attackAvoided = attackAvoided;
    }
}
