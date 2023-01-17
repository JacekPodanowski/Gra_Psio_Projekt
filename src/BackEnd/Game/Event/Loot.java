package BackEnd.Game.Event;

import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Loot implements Event{

    private String name = "Loot";
    private List<Item> lootTab;
    public Loot(List<Item> lootTab){

        this.lootTab.addAll((lootTab));
    }
    public Loot(){
        Random R = new Random();
        this.lootTab = new ArrayList<>();
        if(R.nextBoolean()) {
            this.lootTab.add(Game.generateItem());
        }else{
            this.lootTab.add(Game.generateItem());
        }
    }

    @Override
    public Event event(Player player, int choice) {
        return null;
    }

    @Override
    public Event event(Player player) {
        System.out.println("\nNatrafiasz na skarb!");
        if (this.lootTab != null) {
            for (int i = 0; i < lootTab.size(); i++) {
                if (this.lootTab.get(i) != null) {
                    System.out.println("Czy chcesz podnieść " + lootTab.get(i).toString() + "?");
                    System.out.println("1. Tak" + '\t' + "2. Nie");
                    int wybor = Game.askForChoice(2);
                    if (wybor == 1) {
                        player.pickUpItem(lootTab.get(i));
                    }
                }
            }
        }
        return new EmptyRoom();
    }



    @Override
    public String toString() {return name;}

    public int countItems(){
        return this.lootTab.size();
    }

    public Item getItem(){
        Item item = lootTab.get(0);
        lootTab.remove(0);
        return item;
    }
}
