package Game.Event;

import Chararcter.*;
import Game.*;
import Observable.Subject;
import Observers.Observer;

import java.util.ArrayList;
import java.util.Random;

public class Fight implements Event {

    //================================================= ATRYBUTY KLASY =================================================
    private String name = "Walka";
    private Enemy enemy;
    private ArrayList<Observer> observers = new ArrayList<>();
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
            double health;
            if(player.isPlayerTurn()){
                System.out.println("Wybierz umiejętność, którą chcesz go zaatakować: \n" +
                        "1. " + player.getAbilities()[0].toString() + "\t\t" +
                        "2. " + player.getAbilities()[1].toString() + "\t\t" +
                        "3. " + player.getAbilities()[2].toString() + "\t\t" +
                        "4. " + player.getAbilities()[3].toString());
                int wybor = Game.askForChoice();
                health = this.enemy.getHealth();
                System.out.print("Użyłeś umiejętności " + player.getAbilities()[wybor - 1].toString());
                player.attack(enemy, wybor - 1);
                System.out.println(", zadałeś " + (health - this.enemy.getHealth()) + " obrażeń.");
            }
            else {
                health = player.getHealth();
                System.out.println("Przeciwnik atakuje ");
                enemy.attack(player, generate.nextInt(0, 4));//dana umiejetnosc ma zakres od liczb losowych i tutaj można ja wywolac
                System.out.println(", zadaje " + (health - player.getHealth()) + " obrażeń!\n");
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
