package Chararcter.Profession;

import Chararcter.Character;
import Chararcter.Item.Weapon;
import Chararcter.Skill;

public class Warrior implements Profession{
    @Override
    public void attributesInitiation(Character character) {
        character.setAgility(4);
        character.setIntelligence(5);
        character.setStrength(6);
        character.setWeapon(new Weapon("Kij", 6, "pospolity", 'S', 8, 5));
        this.specialAbility(character);
    }

    @Override
    public void specialAbility(Character character) {
        character.setAbility(new Skill("Ogłuszające uderzenie", 1, 60, 1), 3);
    }
}
