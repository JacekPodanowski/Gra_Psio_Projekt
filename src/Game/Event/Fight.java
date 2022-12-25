package Game.Event;

import Chararcter.*;
import Game.*;

import java.util.Random;

public class Fight implements Event{
    private String name = "Walka";
    private Enemy enemy;
    private boolean playerTurn = true; //Określa czyja tura jest wykonywana

    public Fight(){
        enemy = new Enemy();
        System.out.println("Spotkałeś na swojej drodze przeciwnika!");
    }

    public Event event(Player player){
        Random generate = new Random();
        if (this.enemy.getAgility() > player.getAgility())  // kto zaczyna walkę
            this.playerTurn = false;
        while(this.enemy.getHealth() > 0 && player.getHealth() > 0) {
            if(playerTurn){
                System.out.println("Wybierz umiejętność: \n" +
                        "1. player.umiejetnosc[0].getName() itd. \n");
                int wybor = Game.askForChoice();
                System.out.println("Użyłeś umięjętności " + wybor);
                player.attack(enemy, wybor);
                //===== DO TESTOW ======
                if(wybor == 1)
                    player.setHealth(0);
                if(wybor == 2)
                    this.enemy.setHealth(0);
                //
                //uzywa umiejetnosci zaleznie od returna metody wyzej
                this.playerTurn = false;
            }
            else {
                enemy.attack(player, generate.nextInt(1, 5));//dana umiejetnosc ma zakres od liczb losowych i tutaj można ja wywolac
                this.playerTurn = true;
            }
        }
        if(player.getHealth() <= 0){
            player.death();
            return null;
        }
        else{
            return new Loot(enemy.getInventory());
        }
    }
    public String toString() {return name;}
}
