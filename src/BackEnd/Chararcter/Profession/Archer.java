package BackEnd.Chararcter.Profession;

import BackEnd.Chararcter.Character;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Chararcter.Player;
import BackEnd.Chararcter.Skill;

public class Archer implements Profession{
    @Override
    public void attributesInitiation(Character character) {
        character.setAgility(50);
        character.setIntelligence(30);
        character.setStrength(35);
        character.setWeapon(new Weapon("Prosty łuk", 1, "Zwykły", 'A',30, 5, 0));
        this.specialAbility(character);
    }

    @Override
    public void specialAbility(Character character) {
        character.setAbility(new Skill("Krytyczny strzał", 3, 100, 1), 3);
    }
}
