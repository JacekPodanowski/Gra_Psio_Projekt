package Observers;

import BackEnd.Game.Event.EmptyRoom;
import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.EmptyRoomPanel;
import GUI.Panels.ButtonPanels.InventoryPanel;
import GUI.Panels.ButtonPanels.RestPanel;

public class RestObserver implements Observer{

    private Game game;

    private BottomPanel bottomPanel;

    public RestObserver(BottomPanel bottomPanel){
        this.bottomPanel = bottomPanel;
        this.bottomPanel.getRestPanel().registerObserver(this);
    }

    @Override
    public void update(Game game) {
        this.game = game;
        refresh();
    }

    @Override
    public void refresh() {
        if(game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.REST) {
            bottomPanel.getRestPanel().removeObserver(this);
            bottomPanel.removeAll();
            bottomPanel.setRestPanel((new RestPanel(game)));
            bottomPanel.getRestPanel().registerObserver(this);
            bottomPanel.add(bottomPanel.getRestPanel());
        }
        else {
            bottomPanel.getRestPanel().removeObserver(this);
            bottomPanel.removeAll();
            bottomPanel.add(bottomPanel.getEmptyRoomPanel());
            bottomPanel.getEmptyRoomPanel().getRestButton().setEnabled(false);
        }
        bottomPanel.notifyObservers();
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
}
