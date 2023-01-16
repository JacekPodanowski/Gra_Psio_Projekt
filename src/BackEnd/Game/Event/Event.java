package BackEnd.Game.Event;

import BackEnd.Chararcter.Player;

public interface Event {

    Event event(Player player, int choice);
    Event event(Player player);


}
