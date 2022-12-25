package Game.Event;

import Chararcter.Item.Item;
import Chararcter.Player;
import Game.Game;

public class Loot implements Event{

    private String name = "Loot";
    private Item[] lootTab;
    public Loot(Item[] lootTab){
        this.lootTab = lootTab;
        System.out.println("Pokonany przeciwnik był zaopatrzony w przedmioty.");
    }
    public Loot(){
        //losowanie lootu
        System.out.println("Znalazłeś skarb!");
    }
    @Override
    public Event event(Player player) {
        if (this.lootTab != null) {
            for (int i = 0; i < lootTab.length; i++) {
                System.out.println("Czy chcesz podnieść " + lootTab[i].getName() + "?");
                System.out.println("1. Tak" + '\t' + "2. Nie");
                int wybor = Game.askForChoice();
                if (wybor == 1) {
                    player.pickUpItem(lootTab[i]);
                } else if (wybor != 2) {
                    System.out.println("Niepoprawny wybór!");
                }
            }
        }
        return new EmptyRoom();
    }



    @Override
    public String toString() {return name;}
}
