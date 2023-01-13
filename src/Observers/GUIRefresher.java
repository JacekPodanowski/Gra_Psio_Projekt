package Observers;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import GUI.Panels.MainPanel;
import GUI.Panels.StartGamePanel;
import GUI.Panels.WindowStates;
import GUI.View.MainWindow;


public class GUIRefresher implements Observer{
    private MainWindow mainWindow;
    private Game game;
    public GUIRefresher(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.game = mainWindow.getGame();
        this.mainWindow.getMainPanel().getStartGamePanel().registerObserver(this);
    }

    @Override
    public void update(MainWindow mainWindow) {
        mainWindow.getMainPanel().removeAll();
        mainWindow.getMainPanel().revalidate();
        mainWindow.getMainPanel().repaint();
        mainWindow.add(new MainPanel(this.game, WindowStates.GAMESTART));
        mainWindow.getMainPanel().revalidate();
        mainWindow.getMainPanel().repaint();
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

    @Override
    public void update(StartGamePanel startGamePanel) {
        this.game = startGamePanel.getGame();
        update(mainWindow);
    }
}
