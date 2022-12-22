package Game.Event;

import Chararcter.Item.Item;
import Chararcter.Player;

public class Loot implements Event{
    private Item[] lootTab;

    public Loot(Item[] lootTab){
        this.lootTab = lootTab;
    }
    @Override
    public Event event(Player player) {
        return null;
    }
}
