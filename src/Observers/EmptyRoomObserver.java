package Observers;

import BackEnd.Game.Event.EmptyRoom;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.EmptyRoomPanel;
import GUI.Panels.ButtonPanels.InventoryPanel;

public class EmptyRoomObserver implements Observer{
    private Game game;
    private BottomPanel bottomPanel;


    public EmptyRoomObserver(BottomPanel bottomPanel){
        this.bottomPanel = bottomPanel;
        this.bottomPanel.getEmptyRoomPanel().registerObserver(this);
    }

    @Override
    public void update(Game game) {
        this.game = game;
        refresh();
    }

    @Override
    public void refresh() {
        bottomPanel.removeAll();
        switch(game.getMap().getPlayerLocation(game.getPlayer()).getEvent1()){
            case INVENTORY -> {
                bottomPanel.setInventoryPanel(new InventoryPanel(game));
                bottomPanel.getInventoryPanel().registerObserver(new InventoryObserver(bottomPanel));
                bottomPanel.add(bottomPanel.getInventoryPanel());
                bottomPanel.revalidate();
                bottomPanel.repaint();
            }
            case REST -> {

            }
        }
    }
}
