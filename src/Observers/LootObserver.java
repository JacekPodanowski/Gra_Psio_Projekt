package Observers;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Event.EmptyRoom;
import BackEnd.Game.Event.Loot;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.EmptyRoomPanel;
import GUI.Panels.ButtonPanels.LootPanel;

public class LootObserver implements Observer{

    private Player player;
    private Game game;
    private LootPanel lootPanel;
    private Loot loot;
    private BottomPanel bottomPanel;

    public LootObserver(BottomPanel bottomPanel){
        this.lootPanel = bottomPanel.getLootPanel();
        this.bottomPanel = bottomPanel;
        this.bottomPanel.registerObserver(this);
    }


    @Override
    public void update(Game game) {
        this.game = game;
        this.player = game.getPlayer();
        this.loot = (Loot) game.getMap().getPlayerLocation(game.getPlayer()).getEvent();
        refresh();
    }

    public void refresh(){
        if (loot.countItems() > 0){
            lootPanel.remove(lootPanel.getItemName());
            lootPanel.setItemName(lootPanel.setTitle());
            lootPanel.add(lootPanel.getItemName());
            lootPanel.setLootButtonActive();
            lootPanel.revalidate();
            lootPanel.repaint();
        }
        else {
            bottomPanel.removeAll();
            bottomPanel.getGame().getMap().getPlayerLocation(game.getPlayer()).setEvent(new EmptyRoom());
            bottomPanel.setEmptyRoomPanel(new EmptyRoomPanel(game));
            bottomPanel.getEmptyRoomPanel().registerObserver(new EmptyRoomObserver(bottomPanel));
            bottomPanel.add(bottomPanel.getEmptyRoomPanel());
            bottomPanel.revalidate();
            bottomPanel.repaint();
        }
    }
}
