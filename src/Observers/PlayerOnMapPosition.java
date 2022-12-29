package Observers;

import Game.Game;

public class PlayerOnMapPosition implements Observer{
    private Game game;

    @Override
    public void update(Game game) {
        this.game = game;
    }

    public void displayCurrentMap(){

    }

}
