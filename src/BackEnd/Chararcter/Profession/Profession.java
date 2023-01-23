package BackEnd.Chararcter.Profession;

import BackEnd.Chararcter.Character;

import java.io.Serializable;

public interface Profession extends Serializable {
    void attributesInitiation(Character character);
    void specialAbility(Character character);
}
