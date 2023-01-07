package Game.Event;

import Chararcter.Player;
import Game.Game;

public class EmptyRoom implements Event {
    private String name = "Pusty";

    @Override
    public Event event(Player player) {
        System.out.println("\n\nZnalazłeś się w pustym pokoju, masz chwilę dla siebie.");
        int wybor;
        do{
            System.out.println("Co chcesz zrobić?\n 1. Przejdź do następnego pokoju.\t 2. Odpocznij.\t 3. Użyj przedmiotu.");
            wybor = Game.askForChoice();
            switch(wybor) {
                case 1:
                    //nic
                    break;
                case 2:
                    System.out.println("Odpoczywasz.");
                    //trzeba zdefiniować odpoczynek
                    break;
                case 3:
                    System.out.println("Jakiego przedmiotu chcesz użyć?");
                    player.displayInventoryAndUse();
                    int itemNumber = Game.askForChoice();
                    //player.getInventory()[itemNumber].useItem();
                    System.out.println("Użyto przedmiotu " + player.getInventory()[itemNumber].toString());
                    break;
                default:
                    System.out.println("Niepoprawna opcja!");
                    break;
            }
        }while (wybor != 1);
        return null;
    }
    public String toString() {return name;}
}
