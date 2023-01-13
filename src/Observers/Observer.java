package Observers;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import GUI.Panels.StartGamePanel;
import GUI.View.MainWindow;

public interface Observer {
    public void update(MainWindow mainWindow);
    public void update(Player player);
    public void update(Game game);
    public void update(StartGamePanel startGamePanel);
}
