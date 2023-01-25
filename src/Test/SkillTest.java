package Test;

import BackEnd.Chararcter.Character;
import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Player;
import BackEnd.Chararcter.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    private Skill skill;
    private Player player;
    private Enemy enemy;



    @BeforeEach
    void setUp() {
        skill = new Skill("Krytyczny strzał", 3, 100, 1);
        player = new Player(1);
        enemy = new Enemy(3);
    }

    @Test
    void calcDmg() {
        assertEquals(15, skill.calcDmg(player,enemy));
    }


    @Test
    void reqDmgMultiplier() {
        assertEquals(13, skill.reqDmgMultiplier(player, 1));
    }

    @Test
    void turnSetter() {

    }
}