package Observers;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import GUI.Panels.ButtonPanels.ProfessionChoosePanel;
import GUI.Panels.StartGamePanel;
import GUI.View.MainWindow;

public interface Observer {
    public void update(MainWindow mainWindow);
    public void update(StartGamePanel startGamePanel);
    public void update(ProfessionChoosePanel professionChoosePanel);
}
