package Game.Event;

import Chararcter.*;
import Game.*;

import java.util.Random;

public class Fight implements Event{
    private Enemy enemy;
    private boolean playerTurn = true; //Określa czyja tura jest wykonywana

    public Fight(){
        enemy = new Enemy();
    }

    public void event(Player player){
        Random generate = new Random();
        if (this.enemy.getAgility() > player.getAgility())
            this.playerTurn = false;
        while(this.enemy.getHealth() > 0 && player.getHealth() > 0) {
            if(playerTurn){
                System.out.println("Wybierz umiejętność: \n" +
                        "1. player.umiejetnosc[0].getName() itd. \n");
                System.out.println("Użyłeś umięjętności " + Game.askForChoice());
                //uzywa umiejetnosci zaleznie od returna metody wyzej
                this.playerTurn = false;
            }
            else {
                enemy.attack(player, generate.nextInt(1, 5));
                this.playerTurn = true;
            }
        }
    }
}
