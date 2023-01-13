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
        setLevel(1);
        location_Y=0;
        location_X=size-1;
        this.exp = 0;
        armor=new Armor();
        weapon=new Weapon();
        this.getInventory()[0]=new Potion("Woda",0,"Zwykły",10);
        this.getInventory()[1]=Game.generateItem('W');

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
                System.out.println("Podniesiono " + item.toString());
                return;
            }
        }

        System.out.println("Brak miejsca w ekwipunku ! ");
        System.out.println("Wybierz co wymienić : ");
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                System.out.println(i+1 + ". " + inventory[i].toString());
            }
            else {
                System.out.println(i+1 +". -----");
            }
        }
        System.out.println("\n6. Zostaw Przedmiot\n");

        int choice = Game.askForChoice(6)-1;
        System.out.println("Wymieniono "+ inventory[choice].toString()+ " na "+ item.toString());
        inventory[choice]=item;
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
        System.out.println("Czego chcesz użyć ?");

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

    public void showStats(){
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
        this.health = 100+ strength * 0.5;
    }
}
