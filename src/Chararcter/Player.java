package Chararcter;

import Chararcter.Item.Armor;
import Chararcter.Item.Weapon;

public class Player extends Character{
    private long exp;
    public Player(int health, int strength, int agility, int intelligence, int gold, int level) {
        super(health, strength, agility, intelligence, gold, level);
    }
}
