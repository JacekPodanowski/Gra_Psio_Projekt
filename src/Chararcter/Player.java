package Chararcter;

import Chararcter.Item.*;
import Chararcter.Profession.Profession;
import Game.Game;
import Game.Map;

public class Player extends Character {

    private int location_Y;
    private int location_X;
    private long exp;
    private boolean alive = true;
    //private ability Abilities[];


    public Player(int size) {
        super();
        location_Y=0;
        location_X=size-1;
        System.out.println("Wybierz profesje : ");
        System.out.println("1- Pracownik fizyczny ");
        //System.out.println("2- mag ognia (spawacz) ");
        //System.out.println("3- jakis dzikus inny ");
        Game.askForChoice(1);
    }

    public Player(int health, int strength, int agility, int intelligence, int gold, int level) {
        super(health, strength, agility, intelligence, gold, level);
    }

    @Override
    public void death() {
        alive=false;
        System.out.println("No coz, umarles");
        //wyswietyla statystyki

    }

    @Override
    public void attack(Character character, int skillNumber) {
        int choiceOfSkill = skillNumber;

        System.out.println("Wybierz sposób ataku");
        System.out.print("1 - Normalny atak \t 2 - Szybki atak \t 3 - Potężny atak");
        switch (choiceOfSkill){
            case 0:
                character.setHealth(getHealth() - this.weapon.calculatedmg());

        }
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


}
