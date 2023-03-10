package BackEnd.Game.Event;

import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;

public class Fight implements Event {






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
        enemy = new Enemy(1);
    }

    //============================================= METODY KLASY =======================================================


    public Event event(Player player, int choice){

        enemy = new Enemy(player.getLevel());


        if(this.enemy.getHealth() > 0 && player.getHealth() > 0) {
            if (this.enemy.getAgility() > player.getAgility())  // kto zaczyna walkę
                player.setPlayerTurn(false);
            if(player.isPlayerTurn()){
                double health;

            }
        }
        else {
            if(this.enemy.getHealth() > 0){
                //lose
            }
            else{
                //win
            }
        }
        while(this.enemy.getHealth() > 0 && player.getHealth() > 0) {
            if(player.isPlayerTurn()){
                System.out.println("Wybierz umiejętność, którą chcesz go zaatakować: \n" +
                        "1. " + player.getAbilities()[0].toString() + "\t\t" +
                        "2. " + player.getAbilities()[1].toString() + "\t\t" +
                        "3. " + player.getAbilities()[2].toString() + "\t\t" +
                        "4. " + player.getAbilities()[3].toString());

//                health = this.enemy.getHealth();
//                consolePanel.setMessage("Użyłeś umiejętności " + player.getAbilities()[choice - 1].toString());
//                player.attack(enemy, choice - 1);
//                System.out.println(", zadałeś " + (int)(health - this.enemy.getHealth()) + " obrażeń.\n");
//                System.out.println("Twoje życie : "+player.getHealth());
//                System.out.println("Życie przeciwnika : "+this.enemy.getHealth());

            }
            else {

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
            int lvlchoice = Game.askForChoice(3);
            switch (lvlchoice){
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
            System.out.print("Przy ciele znajdujesz "+enemy.getWeapon().getValue()+" złotych monet");
            player.setGold(player.getGold()+enemy.getWeapon().getValue());
            if(enemy.getInventory() != null) {
                System.out.println(" oraz pare przedmitów: ");
               // return new Loot(enemy.getInventory());
            }
            return new EmptyRoom();
        }


        return null;
    }

    @Override
    public Event event(Player player) {
        return null;
    }

    public String toString() {return name;}
    }

    //==================================================================================================================

