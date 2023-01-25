package BackEnd.Chararcter;

import BackEnd.Chararcter.Item.Armor;
import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Item.Potion;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Chararcter.Profession.Archer;
import BackEnd.Chararcter.Profession.Mage;
import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import java.util.ArrayList;

public class Player extends Character {

    private int location_Y;
    private int location_X;
    private long exp;
    private boolean alive = true;
    private boolean fullEq = false;

    public Player(int size) {
        super();
        setLevel(1);
        location_Y=0;
        location_X=size-1;
        this.exp = 0;
        this.getInventory()[0]=new Potion("Woda",0,"Zwykła",10);
        armor=new Armor();
        weapon=new Weapon();
        this.getInventory()[0]=new Potion("Woda",0,"Zwykły",10);
        this.getInventory()[1]=Game.generateItem('W');
    }

    @Override
    public void death() {
        alive=false;
        System.out.println("No coz, umarles");
        //wyswietyla statystyki

    }

    @Override
    public void attack(Character target, int skillNumber) {
        target.setHealth(target.getHealth() - abilities[skillNumber].use(this, target));
        //umiejetnosc[skillNumber].use(this.weapon.calculatedmg());
        //character.setHealth(getHealth() - umiejetnosc.use[skillNumber](this.weapon.calculatedmg);
    }

    public boolean isEqFull() {

        boolean full = false;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                return false;
            }
        }
        return true;
    }

    public void pickUpItem(Item item){

        boolean succes= false;
        for (int i = 0; i < inventory.length; i++) {
            if(inventory[i]==null){
                inventory[i]=item;
                System.out.println("Podniesiono " + item.toString());
                return;
            }
        }
// tu ma byc event

//        System.out.println("Brak miejsca w ekwipunku ! ");
//        System.out.println("Wybierz co wymienić : ");
//        for (int i = 0; i < inventory.length; i++) {
//            if (inventory[i] != null) {
//                System.out.println(i+1 + ". " + inventory[i].toString());
//            }
//            else {
//                System.out.println(i+1 +". -----");
//            }
//        }
//        System.out.println("\n6. Zostaw Przedmiot\n");
//
//        int choice = Game.askForChoice(6)-1;
//        System.out.println("Wymieniono "+ inventory[choice].toString()+ " na "+ item.toString());
//        inventory[choice]=item;
    }

    public void useItem(int choice){
        if (inventory[choice] instanceof Potion) {
            health = health + ((Potion) inventory[choice]).getHealing();
            inventory[choice] = null;
        }
        if (inventory[choice] instanceof Weapon) {
            Weapon temp = weapon;
            weapon = (Weapon) inventory[choice];

            if (!temp.getName().equals("Dłoń")) {
                inventory[choice] = temp;
            } else {
                inventory[choice] = null;
            }
        }
        if (inventory[choice] instanceof Armor) {
            Armor temp = armor;
            armor = (Armor) inventory[choice];
            if (!temp.getName().equals("Nic")) {
                inventory[choice] = temp;
            } else {
                inventory[choice] = null;
            }
        }
    }

    public void displayInventoryAndUse() {
        System.out.println("Aktualnie używasz : ");
        System.out.println("Broń - "+weapon.toString());
        System.out.println("Zbroja - "+armor.toString());
        System.out.println();
        System.out.println("W ekipunku masz: ");
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                System.out.println(i+1 + ". " + inventory[i].toString());
            }
            else {
                System.out.println(i+1 +". -----------");
            }
        }
        System.out.println("6. Wyjdź\n");
        System.out.println("Czego chcesz użyć?");

        try {
            while (true){
                int choice = Game.askForChoice(6)-1;
                if(inventory[choice]!=null){

                    if(inventory[choice] instanceof Potion){
                        health=health+((Potion) inventory[choice]).getHealing();
                        System.out.println("Użyłeś " + inventory[choice].toString());
                        System.out.println("Mikstura uleczeła cie o : "+ ((Potion) inventory[choice]).getHealing());
                        inventory[choice]=null;
                        break;
                    }
                    if(inventory[choice] instanceof Weapon){
                        Weapon temp = weapon;
                        weapon= (Weapon) inventory[choice];

                        if(!temp.getName().equals("Dłoń")) {
                            inventory[choice] = temp;
                        }else{
                            inventory[choice] = null;
                        }
                        System.out.println("Wyposazyłes "+weapon.toString());
                        break;
                    }
                    if(inventory[choice] instanceof Armor){
                        Armor temp = armor;
                        armor= (Armor) inventory[choice];
                        if(!temp.getName().equals("Nic")) {
                            inventory[choice] = temp;
                        }else{
                            inventory[choice] = null;
                        }
                        System.out.println("Wyposazyłes "+armor.toString());
                        break;
                    }

                }else {
                    System.out.println("Wybierz właściwy numer");
                }
            }
        }catch (IndexOutOfBoundsException e){}
    }

    public void showStats() {
        System.out.println("Twój poziom : "+level);
        System.out.println("Twoje życie : "+health);
        System.out.println("Twoja siła  : "+strength);
        System.out.println("Twoja inteligencja  : "+intelligence);
        System.out.println("Twoja zwinnosc  : "+agility);
        System.out.println("Twoje złoto  : "+gold);
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

    public boolean getFullEq() {
        return fullEq;
    }

    public void setFullEq(boolean fullEq) {
        this.fullEq = fullEq;
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
        this.health = health+ strength * 0.5;
    }
}
