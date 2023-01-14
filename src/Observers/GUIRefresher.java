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

    public void refresh() {
        mainWindow.getMainPanel().removeAll();
        mainWindow.remove(mainWindow.getMainPanel());
        if(game.getPlayer().getProfession() == null) {
            mainWindow.getMainPanel().getStartGamePanel().removeObserver(this);
            mainWindow.setMainPanel(new MainPanel(this.game, WindowStates.GAMESTART));
            mainWindow.add(mainWindow.getMainPanel());
            mainWindow.getMainPanel().getProfessionChoosePanel().registerObserver(this);
        }
        else {
            mainWindow.getMainPanel().getProfessionChoosePanel().removeObserver(this);
            mainWindow.setMainPanel(new MainPanel(this.game, WindowStates.GAME));
            mainWindow.add(mainWindow.getMainPanel());
            mainWindow.getMainPanel().getBottomPanel().registerObserver(this);
            //mainWindow.getMainPanel().getTopPanel().registerObserver(this);
        }
        mainWindow.getMainPanel().revalidate();
        mainWindow.getMainPanel().repaint();
    }
    @Override
    public void update(Game game) {
        this.game = game;
        refresh();
    }
}
