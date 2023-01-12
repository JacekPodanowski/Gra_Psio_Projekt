package BackEnd.Chararcter.Profession;

import BackEnd.Chararcter.Character;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Chararcter.Skill;

public class Mage implements Profession{



    @Override
    public void attributesInitiation(Character character) {
        character.setAgility(35);
        character.setIntelligence(60);
        character.setStrength(20);
        character.setWeapon(new Weapon("Magiczny kij", 1, "Zwykły", 'I', 50, 5));
        this.specialAbility(character);
    }

    @Override
    public void specialAbility(Character character) {
        character.setAbility(new Skill("Leczenie", 0, 100, 3), 3);
    }
}
