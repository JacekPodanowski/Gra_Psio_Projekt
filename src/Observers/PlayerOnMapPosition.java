package Observers;

import Game.Game;

public class PlayerOnMapPosition implements Observer{
    private Game game;

    @Override
    public void update(Game game) {
        this.game = game;
        displayCurrentMap();
    }

    public void displayCurrentMap(){
        this.game.getMap().displayCurrentMapFloor(1, this.game.getPlayer());
    }

}
