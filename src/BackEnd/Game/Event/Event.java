package BackEnd.Game.Event;

import BackEnd.Chararcter.Player;

import java.io.Serializable;

public interface Event extends Serializable {

    Event event(Player player, int choice);
    Event event(Player player);
}
