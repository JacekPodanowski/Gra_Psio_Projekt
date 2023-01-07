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
        this.getInventory()[0]=new Potion("Woda",0,"Zwykła",10);
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
        character.setHealth(character.getHealth() - abilities[skillNumber].use(this, character));
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

    public void displayInventoryAndUse() {
        System.out.println("W ekipunku masz: ");
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                System.out.println(i+1 + ". " + inventory[i].toString());
            }
            else {
                System.out.println(i+1 +". -----");
            }
        }
        System.out.println("6. Wyjdź\n");
        System.out.println("Czego chcesz użyć ?");

        try {
            while (true){
                int choice = Game.askForChoice(6)-1;
                if(inventory[choice]!=null){

                    System.out.println("Użyłeś " + inventory[choice].toString());

                    if(inventory[choice] instanceof Potion){
                        health=health+((Potion) inventory[choice]).getHealing();
                        System.out.println("Mikstura uleczeła cie o : "+ ((Potion) inventory[choice]).getHealing());
                        inventory[choice]=null;
                    }
                    if(inventory[choice] instanceof Weapon){
                        Weapon temp = weapon;
                        weapon= (Weapon) inventory[choice];
                        inventory[choice]=temp;
                        System.out.println("Wyposazyłes "+weapon.toString());
                    }
                    if(inventory[choice] instanceof Armor){
                        Armor temp = armor;
                        armor= (Armor) inventory[choice];
                        inventory[choice]=temp;
                        System.out.println("Wyposazyłes "+armor.toString());
                    }
                    System.out.println();
                    break;
                }else {
                    System.out.println("Wybierz właściwy numer");
                }
            }
        }catch (IndexOutOfBoundsException e){}
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
