package BackEnd.Game.Event;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;

public class Down implements Event {
    private String name = "DÓŁ";

    @Override
    public Event event(Player player, int choice) {
        return null;
    }

    @Override
    public Event event(Player player) {
        System.out.println("\n\nTu kiedys bedziesz mogl pojsc w dol ale teraz nie.");
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
                    for(int i = 0; i < player.getInventory().length; i++)
                        System.out.println(player.getInventory()[i].toString());
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