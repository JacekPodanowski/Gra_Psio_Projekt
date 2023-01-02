package Observers;

import Game.*;
import Game.Event.EmptyRoom;

public class PlayerStatus implements Observer{

    private Game game;

    @Override
    public void update(Game game) {
        this.game = game;
        this.checkLifeStatus();
    }

    public void checkLifeStatus(){
        if(this.game.getPlayer().getHealth() < 20) {
            if(this.game.getMap().getTabOfRoom()[this.game.getPlayer().getLocation_X()][this.game.getPlayer().getLocation_Y()].getEvent() instanceof EmptyRoom)
                System.out.println("Jesteś mocno ranny, pownieneś odpocząć lub uleczyć się przed następną walką.");
        }
    }
}
