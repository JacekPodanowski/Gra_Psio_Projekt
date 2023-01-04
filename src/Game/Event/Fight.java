package Game.Event;

import Chararcter.*;
import Game.*;

import java.util.Random;

public class Fight implements Event{

    //================================================= ATRYBUTY KLASY =================================================
    private String name = "Walka";
    private Enemy enemy;
    //==================================================================================================================



    //============================================= GETTERY I SETTERY ==================================================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    //==================================================================================================================



    //================================================== KONSTRUKTORY ==================================================
    public Fight(){
        enemy = new Enemy();
    }
    //==================================================================================================================



    //============================================= METODY KLASY =======================================================
    public Event event(Player player){
        System.out.println("\n\nSpotkałeś na swojej drodze przeciwnika!");
        Random generate = new Random();
        if (this.enemy.getAgility() > player.getAgility())  // kto zaczyna walkę
            player.setPlayerTurn(false);
        while(this.enemy.getHealth() > 0 && player.getHealth() > 0) {
            if(player.isPlayerTurn()){
                System.out.println("Wybierz umiejętność, którą chcesz go zaatakować: \n" +
                        "1. Umiejętność 1\t\t2. Umiejętność 2\t\t3. Umiejętność 3\t\t 4. Umiejętność 4");
                int wybor = Game.askForChoice();
                double enemyHealth = this.enemy.getHealth();
                System.out.print("Użyłeś umięjętności " + wybor);
                player.attack(enemy, wybor);
                System.out.println(" i zadałeś " + (this.enemy.getHealth() - enemyHealth) + " obrażeń.");
                //===== DO TESTOW ======
//                if(wybor == 1)
//                    player.setHealth(0);
//                if(wybor == 2)
//                    this.enemy.setHealth(0);
                //uzywa umiejetnosci zaleznie od returna metody wyzej
            }
            else {
                enemy.attack(player, generate.nextInt(1, 5));//dana umiejetnosc ma zakres od liczb losowych i tutaj można ja wywolac
                System.out.println("Przeciwnik atakuje cię i zadaje " + "x" + " obrażeń!\n");
            }
        }
        if(player.getHealth() > 0){
            System.out.println("Wygrywasz!");
            if(enemy.getInventory() != null)
                return new Loot(enemy.getInventory());
            return new EmptyRoom();
        }
        return null;
    }
    public String toString() {return name;}

    //==================================================================================================================
}
