package Observers;

import BackEnd.Game.Event.EmptyRoom;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.EmptyRoomPanel;

public class RestObserver implements Observer{

    private Game game;

    private BottomPanel bottomPanel;

    public RestObserver(BottomPanel bottomPanel){
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
        bottomPanel.removeAll();
        bottomPanel.getGame().getMap().getPlayerLocation(game.getPlayer()).setEvent(new EmptyRoom());
        bottomPanel.setEmptyRoomPanel(new EmptyRoomPanel(game));
        bottomPanel.getEmptyRoomPanel().registerObserver(new EmptyRoomObserver(bottomPanel));
        bottomPanel.add(bottomPanel.getEmptyRoomPanel());
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
}
