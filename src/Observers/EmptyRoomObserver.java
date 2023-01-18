package Observers;

import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;

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

    }
}
