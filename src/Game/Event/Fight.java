package Game.Event;

import Chararcter.*;
import Chararcter.Item.Armor;
import Chararcter.Item.Potion;
import Chararcter.Item.Weapon;
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
    public Fight() {
//        enemy = new Enemy();
//        enemy.setWeapon((Weapon) Game.generateItem('W'));
//        enemy.setArmor((Armor) Game.generateItem('A'));
//        enemy.getInventory()[0] = enemy.getWeapon();
//        enemy.getInventory()[1] = enemy.getArmor();
//        Random R = new Random();
//        if (R.nextBoolean()) {
//            enemy.getInventory()[2]=Game.generateItem('P');
//        }
    }
    //==================================================================================================================



    //============================================= METODY KLASY =======================================================
    public Event event(Player player){

//        if(player.getLevel()<=3){
//            enemy = new Enemy();
//            enemy.getInventory()[0]=Game.generateItem('P');
//        }

        //enemy = new Enemy(player.getLevel());

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
                System.out.println("Twoje życie : "+player.getHealth());
                System.out.println("Życie przeciwnika : "+this.enemy.getHealth());

            }
            else {
                health = player.getHealth();
                System.out.print("Przeciwnik atakuje ");
                enemy.attack(player, generate.nextInt(0, 4));//dana umiejetnosc ma zakres od liczb losowych i tutaj można ja wywolac
                System.out.println(", zadaje " + (int)(health - player.getHealth()) + " obrażeń!\n");
                System.out.println("Twoje życie : "+player.getHealth());
                System.out.println("Życie przeciwnika : "+this.enemy.getHealth());
            }
            System.out.println();
        }
        if(player.getHealth() > 0){
            System.out.println("Wygrywasz!\n");
            System.out.println("Ta walka wiele cie nauczyła\nCo chciałbyś rozwinąć ?\n");
            player.setLevel(player.getLevel()+1);
            System.out.println("1.Siła");
            System.out.println("2.Inteligencja");
            System.out.println("3.Zwinność");
            int choice = Game.askForChoice(3);
            switch (choice){
                case 1:
                    player.setStrength(player.getStrength()+5);
                    System.out.println("Teraz będziesz silniejszy");
                    break;
                case 2:
                    player.setIntelligence(player.getIntelligence()+5);
                    System.out.println("Teraz będziesz mądzrejszy");
                    break;
                case 3:
                    player.setAgility(player.getAgility()+5);
                    System.out.println("Teraz będziesz szybszy");
                    break;
            }
            System.out.println();
            System.out.println("Przy ciele znajdujesz "+enemy.getWeapon().getValue()+" złotych monet jak i pare przedmiotów: ");
            player.setGold(player.getGold()+enemy.getWeapon().getValue());
            if(enemy.getInventory() != null)
                return new Loot(enemy.getInventory());
            return new EmptyRoom();
        }
        return null;
    }
    public String toString() {return name;}

    //==================================================================================================================
}
