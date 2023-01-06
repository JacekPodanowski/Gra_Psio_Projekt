package Chararcter.Profession;

import Chararcter.Character;
import Chararcter.Item.Weapon;
import Chararcter.Skill;

public class Archer implements Profession{
    @Override
    public void attributesInitiation(Character character) {
        character.setAgility(6);
        character.setIntelligence(5);
        character.setStrength(4);
        character.setWeapon(new Weapon("Prosty łuk", 6, "pospolity", 'A', 8, 5));
        this.specialAbility(character);
    }

    @Override
    public void specialAbility(Character character) {
        character.setAbility(new Skill("Podwójny strzał", 1, 100, 1), 3);
    }
}
