package Test;

import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Chararcter.Player;
import BackEnd.Chararcter.Skill;
import BackEnd.Game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;


    @Test
    void generateRestEvent() {
        game = new Game();
        assertEquals(4, game.generateRestEvent());
    }

}