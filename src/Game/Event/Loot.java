package Game.Event;

import Chararcter.Item.Item;
import Chararcter.Player;
import Game.Game;

import java.util.Random;

public class Loot implements Event{

    private String name = "Loot";
    private Item[] lootTab;
    public Loot(Item[] lootTab){
        this.lootTab = lootTab;
    }
    public Loot(){
        Random R = new Random();
        if(R.nextBoolean()) {
            this.lootTab = new Item[1];
            this.lootTab[0] = Game.generateItem();//moga byc 2 czasem
        }else{
            this.lootTab = new Item[2];
            this.lootTab[0] = Game.generateItem();
            this.lootTab[1] = Game.generateItem();
        }
    }

    @Override
    public Event event(Player player) {
        System.out.println("\nNatrafiasz na skarb!");
        if (this.lootTab != null) {
            for (int i = 0; i < lootTab.length; i++) {
                if (this.lootTab[i] != null) {
                    System.out.println("Czy chcesz podnieść " + lootTab[i].toString() + "?");
                    System.out.println("1. Tak" + '\t' + "2. Nie");
                    int wybor = Game.askForChoice(2);
                    if (wybor == 1) {
                        player.pickUpItem(lootTab[i]);
                    }
                }
            }
        }
        return new EmptyRoom();
    }



    @Override
    public String toString() {return name;}
}
