package Test;

import BackEnd.Chararcter.Character;
import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Player;
import BackEnd.Chararcter.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CharacterTest {

    private Player player =  new Player(5);;
    private int procent = 5;


    @Test
    void healMissingHealth() {
        Player player1 = Mockito.mock(Player.class);
        verify(player1).healMissingHealth(5);
    }
}