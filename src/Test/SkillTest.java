package Test;

import BackEnd.Chararcter.Character;
import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Player;
import BackEnd.Chararcter.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SkillTest {

    private Skill skill;
    private Skill skill1;
    private Player player;
    private Enemy enemy;



    @BeforeEach
    void setUp() {
        skill = new Skill("Ogłuszające uderzenie", 2, 70, 1);

        player = new Player(1);
        enemy = new Enemy(3);
    }

    @Test
    void calcDmg() {
        assertEquals(8, skill.calcDmg(player,enemy));
    }


    @Test
    void reqDmgMultiplier() {
        assertEquals(1.005, skill.reqDmgMultiplier(player, 1));
    }
    
//    @Test
//    void turnSetter() {
//        Skill skill1 = Mockito.mock(Skill.class);
//        verify(skill1).turnSetter(player);
//    }
}