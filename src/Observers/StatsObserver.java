package Observers;

import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.InventoryPanel;

public class StatsObserver implements Observer{
    private Game game;
    private BottomPanel bottomPanel;

    public StatsObserver(BottomPanel bottomPanel){
        this.bottomPanel = bottomPanel;
        this.bottomPanel.getInventoryPanel().registerObserver(this);
    }

    @Override
    public void update(Game game) {
        this.game = game;
        refresh();
    }

    @Override
    public void refresh() {
        if(game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.STATS) {
            bottomPanel.getInventoryPanel().removeObserver(this);
            bottomPanel.removeAll();
            bottomPanel.setInventoryPanel(new InventoryPanel(game));
            bottomPanel.getInventoryPanel().registerObserver(this);
            bottomPanel.add(bottomPanel.getInventoryPanel());
        }
        else {
            bottomPanel.getInventoryPanel().removeObserver(this);
            bottomPanel.removeAll();
            bottomPanel.add(bottomPanel.getEmptyRoomPanel());
        }
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
}
