package Observers;

import BackEnd.Game.Event.Loot;
import BackEnd.Game.Event.RoomEvent;
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
        game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.LOOT);
        bottomPanel.setLootPanel(new LootPanel(game));
        
        bottomPanel.getLootPanel().registerObserver(new LootObserver(bottomPanel));
        bottomPanel.add(bottomPanel.getLootPanel());
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
}
