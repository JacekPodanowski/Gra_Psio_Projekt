package BackEnd.Game.Event;

import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import GUI.Panels.IConsolePanel;
import Observers.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fight implements Event {






    //================================================= ATRYBUTY KLASY =================================================
    private String name = "Walka";
    private Enemy enemy;
    IConsolePanel consolePanel;
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

    public void setConsolePanel(IConsolePanel consolePanel) {
        this.consolePanel = consolePanel;
    }

    //==================================================================================================================
/*
    public void attach(IFightObserver observer)
    {
        observers.add(observer);
    }

    public void detach(IFightObserver observer)
    {
        observers.remove(observer);
    }
  */


    //============================================= METODY KLASY =======================================================



    public void rozpocznijWalke()
    {

    }

    public Event event(Player player){
        consolePanel.setMessage("Spotkałeś na swojej drodze przeciwnika!");


        /*
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
                System.out.println(", zadałeś " + (int)(health - this.enemy.getHealth()) + " obrażeń.\n");
            }
            else {
                health = player.getHealth();
                System.out.print("Przeciwnik atakuje ");
                enemy.attack(player, generate.nextInt(0, 4));//dana umiejetnosc ma zakres od liczb losowych i tutaj można ja wywolac
                System.out.println(", zadaje " + (int)(health - player.getHealth()) + " obrażeń!\n");
            }
            System.out.println();
        }
        if(player.getHealth() > 0){
            System.out.println("Wygrywasz!");
            if(enemy.getInventory() != null)
                return new Loot(enemy.getInventory());
            return new EmptyRoom();
        }

         */
        return null;
    }
    public String toString() {return name;}

    }

    //==================================================================================================================

