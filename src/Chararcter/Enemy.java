package Chararcter;

import Chararcter.Item.Armor;
import Chararcter.Item.Weapon;
import Chararcter.Profession.Archer;
import Chararcter.Profession.Mage;
import Chararcter.Profession.Warrior;
import Game.Game;

import java.util.Random;

public class Enemy extends Character{
    public Enemy() {
        super();
        Random r = new Random();
        switch(r.nextInt(4)){
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

    }
    @Override
    public void attack(Character character, int skillNumber) {
        Random r = new Random();
        this.abilities[r.nextInt(4)].use(this, character);
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
        this.health = strength * 18;
    }
}
