package Chararcter;

import Chararcter.Item.*;

public class Player extends Character{
    private long exp;
    public Player(int health, int strength, int agility, int intelligence, int gold, int level) {
        super(health, strength, agility, intelligence, gold, level);
    }

    @Override
    public void death() {

    }

    @Override
    public void attack(Character character, int skillNumber) {
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
}
