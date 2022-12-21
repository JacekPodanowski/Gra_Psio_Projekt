package Game.Event;

import Chararcter.*;
import Game.*;

public class Walka implements Event{
    private Enemy enemy;
    private boolean playerTurn = true; //Określa czyja tura jest wykonywana

    public Walka(){
        enemy = new Enemy();
    }

    public void event(Player player){
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
            else{
                enemy.attack(player);
                this.playerTurn = true;
            }
        }
    }
}
