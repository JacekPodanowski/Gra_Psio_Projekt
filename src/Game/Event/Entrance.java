package Game.Event;

import Chararcter.Player;
import Game.Game;

public class Entrance implements Event{
    private String name = "WEJŚCIE";
    @Override
    public Event event(Player player) {
        System.out.println("Budzisz się w dziwnym, pustym sześciennym pokoju. Nie jesteś pewien co się wydarzyło, jedyne co widzisz to drzwi w ścianie.");
        int wybor;
        do{
            System.out.println("Co chcesz zrobić?\n 1. Przejdź przez tajemnicze drzwi.\t 2. Sprawdź torbę.");
            wybor = Game.askForChoice();
            switch(wybor){
                case 1:
                    System.out.println("Niepewny co czeka cię za nimi otwierasz je i przez nie przechodzisz.");
                    break;
                case 2:
                    player.displayInventory();
                    break;
                default:
                    System.out.println("Niepoprawna opcja!");
                    break;
            }
        }while(wybor != 1);
        return null;
    }
    public String toString() {return name;}
}
