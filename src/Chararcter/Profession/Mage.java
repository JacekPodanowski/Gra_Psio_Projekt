package Chararcter.Profession;

import Chararcter.Character;
import Chararcter.Item.Weapon;

public class Mage implements Profession{



    @Override
    public void attributesInitiation(Character character) {
        character.setAgility(5);
        character.setIntelligence(6);
        character.setStrength(4);
        character.setWeapon(new Weapon("Magiczny kij", 6, "pospolity", 'I', 8, 5));
    }

    @Override
    public void specialAbility(Character character) {

    }
}
