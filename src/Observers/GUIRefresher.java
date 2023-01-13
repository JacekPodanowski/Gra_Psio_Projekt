package Observers;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import GUI.Panels.MainPanel;
import GUI.Panels.WindowStates;
import GUI.View.MainWindow;


public class GUIRefresher implements Observer{
    private MainWindow mainWindow;
    public GUIRefresher(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.mainWindow.setObserver(this);
    }

    @Override
    public void update(MainWindow mainWindow) {
        if(mainWindow.getGame().getPlayer().getProfession() == null) {
            mainWindow.getMainPanel().setState(WindowStates.GAMESTART);
            mainWindow.setMainPanel(new MainPanel(mainWindow.getGame(), this));
            mainWindow.getMainPanel().revalidate();
        }
    }

    @Override
    public void update(Player player) {
        mainWindow.getGame().setPlayer(player);
        update(mainWindow);
    }

    @Override
    public void update(Game game) {
        mainWindow.setGame(game);
        update(mainWindow);
    }
}
