package Game.Event;

import Chararcter.Player;

public class EmptyRoom implements Event {
    @Override
    public Event event(Player player) {
        return null;
    }
}
