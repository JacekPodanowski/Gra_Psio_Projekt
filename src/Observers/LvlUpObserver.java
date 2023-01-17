package Observers;

import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.LootPanel;

public class LvlUpObserver implements Observer{
    private BottomPanel bottomPanel;
    private Game game;

    public LvlUpObserver(BottomPanel bottomPanel){
        this.bottomPanel = bottomPanel;
        this.bottomPanel.registerObserver(this);
    }

    @Override
    public void update(Game game) {
        this.game = game;
        refresh();
    }

    public void refresh(){
        bottomPanel.removeAll();
        bottomPanel.setLootPanel(new LootPanel(game));
        //bottomPanel.getLootPanel().registerObserver(); - zarejestrowac LootPanelObserver
        bottomPanel.add(bottomPanel.getLootPanel());
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
}
