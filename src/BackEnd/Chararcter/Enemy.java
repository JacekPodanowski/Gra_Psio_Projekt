package BackEnd.Chararcter;

import BackEnd.Chararcter.Item.Armor;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Chararcter.Profession.Archer;
import BackEnd.Chararcter.Profession.Mage;
import BackEnd.Game.Game;


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

    public Enemy(int lvl) {
        super();
        Random r = new Random();
        switch (r.nextInt(1,3)) {
            case 1 -> this.profession = new Warrior();
            case 2 -> this.profession = new Mage();
            case 3 -> this.profession = new Archer();
        }
        this.profession.attributesInitiation(this);

        switch (r.nextInt(lvl)) {
            case 0:
                break;
            case 1 :
                weapon= (Weapon) Game.generateItem('W',1);
                armor= (Armor) Game.generateItem('A',1);
                break;
            case 2:
                weapon= (Weapon) Game.generateItem('W',2);
                armor= (Armor) Game.generateItem('A',2);
                break;
            case 3:
                weapon= (Weapon) Game.generateItem('W',3);
                armor= (Armor) Game.generateItem('A',3);
                break;
            case 4:
                weapon= (Weapon) Game.generateItem('W',4);
                armor= (Armor) Game.generateItem('A',4);
                break;
            case 5:
                weapon= (Weapon) Game.generateItem('W',5);
                armor= (Armor) Game.generateItem('A',5);
                break;
        }
        if(!weapon.getName().equals("Dłoń"))
            inventory[0]=weapon;
        if(!armor.getName().equals("Nic"))
            inventory[1]=armor;
        if(r.nextBoolean())
            inventory[3]= Game.generateItem('P');
    }
    @Override
    public void attack(Character target, int skillNumber) {
        target.setHealth(target.getHealth() - this.abilities[skillNumber].use(this, target));
    }

    @Override
    public void setStrength(int strength){
        this.strength = strength;
        this.health = 75+ strength * 0.5;
    }
}
