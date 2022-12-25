package Game.Event;

import Chararcter.Player;

public interface Event {
    public abstract Event event(Player player);
}
