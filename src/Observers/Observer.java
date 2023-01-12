package Observers;

import BackEnd.Game.Game;
import GUI.View.MainWindow;

public interface Observer {
    public void update(Game game);
    public void update(MainWindow mainWindow);
}
