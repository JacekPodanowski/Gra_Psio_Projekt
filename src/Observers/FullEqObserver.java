package Observers;

import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.FullEqPanel;
import GUI.Panels.ButtonPanels.InventoryPanel;

public class FullEqObserver implements Observer{
    private Game game;
    private BottomPanel bottomPanel;

    public FullEqObserver(BottomPanel bottomPanel){
        this.bottomPanel = bottomPanel;
        this.bottomPanel.getFullEqPanel().registerObserver(this);
    }

    @Override
    public void update(Game game) {
        this.game = game;
        refresh();
    }

    @Override
    public void refresh() {
        if(game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.FULLEQ) {
            bottomPanel.getFullEqPanel().removeObserver(this);
            bottomPanel.removeAll();
            bottomPanel.setFullEqPanel(new FullEqPanel(game));
            bottomPanel.getFullEqPanel().registerObserver(this);
            bottomPanel.add(bottomPanel.getFullEqPanel());
        }
        else {
            bottomPanel.getFullEqPanel().removeObserver(this);
            bottomPanel.removeAll();
            bottomPanel.add(bottomPanel.getEmptyRoomPanel());
        }
        bottomPanel.notifyObservers();
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
}
