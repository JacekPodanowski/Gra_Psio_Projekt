package Observers;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.EmptyRoomPanel;
import GUI.Panels.ButtonPanels.InventoryPanel;
import GUI.Panels.ButtonPanels.LootPanel;

public class LootObserver implements Observer{

    private Player player;
    private Game game;
    private LootPanel lootPanel;
    private BottomPanel bottomPanel;

    public LootObserver(BottomPanel bottomPanel){
        this.lootPanel = bottomPanel.getLootPanel();
        this.bottomPanel = bottomPanel;
    }


    @Override
    public void update(Game game) {
        this.game = game;
        this.player = game.getPlayer();
        refresh();
    }

    public void refresh(){
        if(game.getMap().getPlayerLocation(game.getPlayer()).getEvent1()==RoomEvent.FULLEQ) {

            bottomPanel.setInventoryPanel(new InventoryPanel(game));
            bottomPanel.getInventoryPanel().registerObserver(new InventoryObserver(bottomPanel));
            bottomPanel.add(bottomPanel.getInventoryPanel());
            bottomPanel.revalidate();
            bottomPanel.repaint();

        }else {

            if(game.getMap().getPlayerLocation(game.getPlayer()).countItems() > 0){
            lootPanel.removeAll();
            lootPanel.setItemName(lootPanel.setTitle());
            lootPanel.setStaty(lootPanel.staty());
            lootPanel.add(lootPanel.initiateComponents());
            lootPanel.setLootButtonActive();
            lootPanel.revalidate();
            lootPanel.repaint();
        }else {
            bottomPanel.removeAll();
            bottomPanel.getGame().getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.EMPTYROOM);
            bottomPanel.setEmptyRoomPanel(new EmptyRoomPanel(game));
            bottomPanel.getEmptyRoomPanel().registerObserver(new EmptyRoomObserver(bottomPanel));
            bottomPanel.add(bottomPanel.getEmptyRoomPanel());
            bottomPanel.getLootPanel().removeObserver(this);
            bottomPanel.notifyObservers();
            bottomPanel.revalidate();
            bottomPanel.repaint();
        }
    }
}
}
