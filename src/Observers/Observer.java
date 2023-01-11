package Observers;

import Game.Game;
import View.MainWindow;

public interface Observer {
    public void update(Game game);
    public void update(MainWindow mainWindow);
}
