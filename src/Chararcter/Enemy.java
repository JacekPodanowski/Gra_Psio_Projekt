package Chararcter;

import Chararcter.Item.Armor;
import Chararcter.Item.Weapon;
import Chararcter.Profession.Archer;
import Chararcter.Profession.Mage;
import Chararcter.Profession.Warrior;
import Game.Game;


import java.util.Random;

public class Enemy extends Character{

    public Player player;

//    public Enemy() {
//        super();
//        Random r = new Random();
//        switch (r.nextInt(1,3)) {
//            case 1 -> this.profession = new Warrior();
//            case 2 -> this.profession = new Mage();
//            case 3 -> this.profession = new Archer();
//        }
//        this.profession.attributesInitiation(this);
//    }

    public Enemy() {
        super();
        Random r = new Random();
        switch (r.nextInt(1,3)) {
            case 1 -> this.profession = new Warrior();
            case 2 -> this.profession = new Mage();
            case 3 -> this.profession = new Archer();
        }
        this.profession.attributesInitiation(this);

        switch (r.nextInt(1,5)) {
            case 1 :
                //weapon=Game.generateItem('W',1);
                //armor=Game.generateItem('A',1);
            case 2:
                //weapon=Game.generateItem('W',2);
                //armor=Game.generateItem('A',2);
            case 3:
                //weapon=Game.generateItem('W',3);
                //armor=Game.generateItem('A',3);
            case 4:
                //weapon=Game.generateItem('W',4);
                //armor=Game.generateItem('A',4);
            case 5:
                //weapon=Game.generateItem('W',5);
                //armor=Game.generateItem('A',5);
        }

        
    }


    @Override
    public void death() {

    }
    @Override
    public void attack(Character character, int skillNumber) {
        character.setHealth(character.getHealth() - this.abilities[skillNumber].use(this, character));
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
        this.health = 75;
    }
}
