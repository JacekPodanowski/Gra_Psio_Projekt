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
        switch (r.nextInt(1,3)) {
            case 1 -> this.profession = new Warrior();
            case 2 -> this.profession = new Mage();
            case 3 -> this.profession = new Archer();
        }
        this.profession.attributesInitiation(this);
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
