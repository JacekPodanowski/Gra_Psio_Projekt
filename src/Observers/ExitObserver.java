package Observers;

import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;

public class ExitObserver implements Observer{
    private Game game;
    private BottomPanel bottomPanel;

    public ExitObserver(BottomPanel bottomPanel){
        this.bottomPanel = bottomPanel;
        this.game = bottomPanel.getGame();
    }

    @Override
    public void update(Game game) {
        this.game = game;
        refresh();
    }

    @Override
    public void refresh() {
        bottomPanel.notifyObservers();
    }
}
