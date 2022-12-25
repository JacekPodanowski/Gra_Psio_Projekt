package Game.Event;

import Chararcter.Item.Item;
import Chararcter.Player;
import Game.Game;

public class Loot implements Event{
    private Item[] lootTab;

    public Loot(Item[] lootTab){
        this.lootTab = lootTab;
    }
    public Loot(){
        //losowanie lootu
    }
    @Override
    public Event event(Player player) {
        for(int i = 0; i < lootTab.length; i++) {
            System.out.println("Czy chcesz podnieść " + lootTab[i].getName() + "?");
            System.out.println("1. Tak" + '\t' + "2. Nie");
            int wybor = Game.askForChoice();
            if(wybor == 1){
                player.pickUpItem(lootTab[i]);
            }
            else if (wybor != 2) {
                System.out.println("Niepoprawny wybór!");
            }
        }
        return new EmptyRoom();
    }
}
