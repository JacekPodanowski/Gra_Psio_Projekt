package Chararcter;

import Chararcter.Item.Armor;
import Chararcter.Item.Weapon;

public class Enemy extends Character{
    public Enemy() {
        super();
    }

    public Enemy(int health, int strength, int agility, int intelligence, int gold, int level) {
        super(health, strength, agility, intelligence, gold, level);
    }

    public Enemy(int health, int strength, int agility, int intelligence, int gold, int level, Weapon weapon, Armor armor) {
        super(health, strength, agility, intelligence, gold, level, weapon, armor);
    }

    @Override
    public void attack(Character character) {

    }
}
