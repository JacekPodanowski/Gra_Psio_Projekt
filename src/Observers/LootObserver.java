package Observers;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Event.Loot;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.LootPanel;

public class LootObserver implements Observer{

    private Player player;
    private Game game;
    private LootPanel lootPanel;
    private Loot loot;
    private BottomPanel bottomPanel;

    public LootObserver(LootPanel lootPanel, BottomPanel bottomPanel){
        this.lootPanel = lootPanel;
        this.bottomPanel = bottomPanel;
        this.bottomPanel.registerObserver(this);
    }


    @Override
    public void update(Game game) {

        this.game = game;
        this.player = game.getPlayer();
        this.loot = (Loot) game.getMap().getPlayerLocation(game.getPlayer()).getEvent();
        refresh();
    }

    public void refresh(){
        if (loot.countItems() >0){
            if (game.isUserWantToAddItem()){
                player.pickUpItem(loot.getItem());
                lootPanel.setLootButtonActive();
            }
        }
        else {
            //pojawia się możliwość przejścia do innego pokoju
        }
    }
}
