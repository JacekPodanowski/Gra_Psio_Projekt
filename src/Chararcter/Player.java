package Chararcter;

import Chararcter.Item.*;
import Chararcter.Profession.Archer;
import Chararcter.Profession.Mage;
import Chararcter.Profession.Warrior;
import Game.Game;

public class Player extends Character {

    private int location_Y;
    private int location_X;
    private long exp;
    private boolean alive = true;

    public Player(int size) {
        super();
        location_Y=0;
        location_X=size-1;
        this.exp = 0;
        System.out.println("Wybierz profesję: ");
        System.out.println("1. Wojownik\t\t 2. Mag\t\t 3. Łucznik");
        switch(Game.askForChoice(3)){
            case 1:
                this.profession = new Warrior();
                break;
            case 2:
                this.profession = new Mage();
                break;
            case 3:
                this.profession = new Archer();
                break;
        }
        this.profession.attributesInitiation(this);
    }

    @Override
    public void death() {
        alive=false;
        System.out.println("No coz, umarles");
        //wyswietyla statystyki

    }

    @Override
    public void attack(Character character, int skillNumber) {
        character.setHealth(getHealth() - abilities[skillNumber].use(this, character));
        //umiejetnosc[skillNumber].use(this.weapon.calculatedmg());
        //character.setHealth(getHealth() - umiejetnosc.use[skillNumber](this.weapon.calculatedmg);
    }

    public void pickUpItem(Item item){
        boolean succes= false;
        for (int i = 0; i < inventory.length; i++) {
            if(inventory[i]==null){
                inventory[i]=item;
                System.out.println("Podniesiono " + "nazwa przedmiotu "+ "na " + i + "miejsce w eq");
                succes = true;
            }
        }
        if(!succes){
            System.out.println("Ekwipunek pelny");
        }
    }


//===========================================================SETERY I GETERY=====================================================================================
    public int getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(int location_Y) {
        this.location_Y = location_Y;
    }

    public int getLocation_X() {
        return location_X;
    }

    public void setLocation_X(int location_X) {
        this.location_X = location_X;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    @Override
    public void setStrength(int strength){
        this.strength = strength;
        this.health = strength * 20;
    }
}
