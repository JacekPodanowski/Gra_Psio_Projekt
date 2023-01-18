package Observers;

import BackEnd.Chararcter.Player;
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
            lootPanel.setTheTitle();
            if (game.isUserWantToAddItem()){
                player.pickUpItem(loot.getItem());
            }
            else {
                loot.getItem();
            }
            lootPanel.setLootButtonActive();
        }
        else {
            bottomPanel.removeAll();
            bottomPanel.setEmptyRoomPanel(new EmptyRoomPanel(game));
            //bottomPanel.getEmptyRoomPanel().registerObserver(new LootObserver(bottomPanel));
            bottomPanel.add(bottomPanel.getEmptyRoomPanel());
            bottomPanel.revalidate();
            bottomPanel.repaint();
        }
    }
}
