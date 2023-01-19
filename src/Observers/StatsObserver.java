package Observers;

import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.InventoryPanel;
import GUI.Panels.ButtonPanels.StatsPanel;

public class StatsObserver implements Observer{
    private Game game;
    private BottomPanel bottomPanel;

    public StatsObserver(BottomPanel bottomPanel){
        this.bottomPanel = bottomPanel;
        this.bottomPanel.getStatsPanel().registerObserver(this);
    }

    @Override
    public void update(Game game) {
        this.game = game;
        refresh();
    }

    @Override
    public void refresh() {
        if(game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.STATS) {
            bottomPanel.getStatsPanel().removeObserver(this);
            bottomPanel.removeAll();
            bottomPanel.setStatsPanel(new StatsPanel(game));
            bottomPanel.getStatsPanel().registerObserver(this);
            bottomPanel.add(bottomPanel.getStatsPanel());
        }
        else {
            bottomPanel.getStatsPanel().removeObserver(this);
            bottomPanel.removeAll();
            bottomPanel.add(bottomPanel.getEmptyRoomPanel());
        }
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
}
