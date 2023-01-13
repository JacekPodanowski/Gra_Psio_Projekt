package Observers;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import GUI.Panels.MainPanel;
import GUI.View.MainWindow;


public class GUIRefresher implements Observer{
    private MainWindow mainWindow;
    public GUIRefresher(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
    @Override
    public void update(Game game) {
        if(game.getPlayer().getProfession() == null)
            mainWindow.setMainPanel(new MainPanel(game));
    }

    @Override
    public void update(MainWindow mainWindow) {

    }
    public void update(Player player){


    }
}
