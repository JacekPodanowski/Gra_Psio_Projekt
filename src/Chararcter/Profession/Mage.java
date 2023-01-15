package Chararcter.Profession;

import Chararcter.Character;
import Chararcter.Item.Weapon;
import Chararcter.Skill;

public class Mage implements Profession{



    @Override
    public void attributesInitiation(Character character) {
        character.setAgility(35);
        character.setIntelligence(60);
        character.setStrength(20);
        character.setWeapon(new Weapon("Magiczny kij", 1,"Zwykły",'I',  30, 5, 0));
        this.specialAbility(character);
    }

    @Override
    public void specialAbility(Character character) {
        character.setAbility(new Skill("Leczenie", 0, 100, 3), 3);
    }
}
