package BackEnd.Chararcter.Profession;

import BackEnd.Chararcter.Character;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Chararcter.Skill;

public class Warrior implements Profession{
    @Override
    public void attributesInitiation(Character character) {
        character.setAgility(35);
        character.setIntelligence(15);
        character.setStrength(60);
        character.setWeapon(new Weapon("Pałka", 1, "Zwykły", 'S', 20, 5));
        this.specialAbility(character);
    }

    @Override
    public void specialAbility(Character character) {
        character.setAbility(new Skill("Ogłuszające uderzenie", 1, 60, 1), 3);
    }
}