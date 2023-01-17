package Game.Event;

import Chararcter.Item.Armor;
import Chararcter.Item.Item;
import Chararcter.Item.Weapon;
import Chararcter.Player;
import Game.Game;

import java.util.Random;

public class Up implements Event {
    private String name = "GÓRA";

    @Override
    public Event event(Player player) {
        System.out.println("\n\nZnalazłeś portal na wyższy poziom." +
                "Na ścianie wyryto ++. Co chcesz zrobić?");
        int wybor;
        do{
            System.out.print("Co chcesz zrobić?\n 1. Przejdź do następnego pokoju.\t 2. Użyj przedmiotu.\t3. Zobacz swoje statystyki.");
            System.out.println("\t4. Wejdź do portalu.");
            wybor = Game.askForChoice(4);

            switch(wybor) {
                case 1:
                    return null;
                case 2:
                    System.out.println("Jakiego przedmiotu chcesz użyć?");
                    player.displayInventoryAndUse();
                    break;
                case 3:
                    player.showStats();
                    break;
                case 4:
                    System.out.println("Dostałeś się na piętro: "+player.getLocation_H()+1);
                    System.out.println("\n");
                    break;
                default:
                    System.out.println("Niepoprawna opcja!");
                    break;
            }
        }while (wybor != 4);
        return null;
    }
    public String toString() {return name;}
}
