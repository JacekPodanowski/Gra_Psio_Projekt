package Observers;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import GUI.Panels.ButtonPanels.ProfessionChoosePanel;
import GUI.Panels.MainPanel;
import GUI.Panels.StartGamePanel;
import GUI.Panels.WindowStates;
import GUI.View.MainWindow;


public class GUIRefresher implements Observer{
    private final MainWindow mainWindow;
    private Game game;
    public GUIRefresher(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.game = mainWindow.getGame();
        this.mainWindow.getMainPanel().getStartGamePanel().registerObserver(this);
    }

    public void refresh(MainWindow mainWindow) {
        mainWindow.getMainPanel().removeAll();
        mainWindow.remove(mainWindow.getMainPanel());
        if(game.getPlayer().getProfession() == null) {
            mainWindow.add(new MainPanel(this.game, WindowStates.GAMESTART));
        }
        else {
            mainWindow.add(new MainPanel(this.game, WindowStates.GAME));
        }
        mainWindow.getMainPanel().revalidate();
        mainWindow.getMainPanel().repaint();
    }
    @Override
    public void update(Game game) {
        this.game = game;
        refresh(mainWindow);
    }
}
