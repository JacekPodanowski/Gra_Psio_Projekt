package Game.Event;

import Chararcter.Item.Item;
import Chararcter.Player;
import Game.Game;

public class Loot implements Event{

    private String name = "Loot";
    private Item[] lootTab;
    public Loot(Item[] lootTab){
        this.lootTab = lootTab;
    }
    public Loot(){
        //losowanie lootu
    }
    @Override
    public Event event(Player player) {
        System.out.println("\n\nNatrafiasz na skarb!");
        if (this.lootTab != null) {
            for (int i = 0; i < lootTab.length; i++) {
                System.out.println("Czy chcesz podnieść " + lootTab[i].getName() + "?");
                System.out.println("1. Tak" + '\t' + "2. Nie");
                int wybor = Game.askForChoice(2);
                if (wybor == 1) {
                    player.pickUpItem(lootTab[i]);
                }
            }
        }
        return new EmptyRoom();
    }



    @Override
    public String toString() {return name;}
}
