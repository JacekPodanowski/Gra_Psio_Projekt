package Test;

import BackEnd.Chararcter.Item.Armor;
import BackEnd.Chararcter.Item.Potion;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Game.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;


    @Test
    void generateRestEvent() {
        game = new Game();
        assertEquals(4, game.generateRestEvent());
    }

    @Test
    public void testWeaponClass() {
        Weapon weapon = new Weapon();
        assertTrue(weapon instanceof Weapon);
    }

    @Test
    public void testPotion() {
        Potion potion = new Potion("aa",5,5);
        assertTrue(potion instanceof Potion);
    }
    @Test
    public void testArmorClass() {
        Armor armor = new Armor();
        assertTrue(armor instanceof Armor);
    }

}