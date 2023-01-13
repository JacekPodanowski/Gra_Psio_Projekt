package BackEnd.Chararcter;

import BackEnd.Chararcter.Item.Armor;
import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Item.Potion;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import java.util.ArrayList;

public class Player extends Character implements Subject {

    private int location_Y;
    private int location_X;
    private long exp;
    private boolean alive = true;
    private ArrayList<Observer> observers = new ArrayList<>();

    public Player(int size, Observer observer) {
        super();
        location_Y=0;
        location_X=size-1;
        this.exp = 0;
        this.getInventory()[0]=new Potion("Woda",0,"Zwykła",10);
        this.observers.add(observer);
        notifyObservers();
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

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(this);
    }
}
