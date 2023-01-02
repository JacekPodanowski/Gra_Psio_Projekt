package Game.Event;

import Chararcter.Player;
import Game.Game;

public class Exit implements Event{
    private String name = "WYJŚCIE";
    @Override
    public Event event(Player player) {
        System.out.println("Widzisz świetlisty tunel na końcu tego pokoju");
        int wybor;
        do{
            System.out.println("Co chcesz zrobić?\n 1. Przejdź przez tajemnicze drzwi.");
            wybor = Game.askForChoice(1);
            switch(wybor){
                case 1:
                    System.out.println("Niepewny co czeka wchodzisz w magiczna dziure.");
                    System.out.println("\n  GRATULACJE ");
                    System.out.println("Udało ci się uciec z kostki ");
                    System.exit(420);
                    break;
            }
        }while(wybor != 1);
        return null;
    }
    public String toString() {return name;}
}