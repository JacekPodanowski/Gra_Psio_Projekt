package Observers;

import BackEnd.Game.Game;
import GUI.Panels.MainPanel;
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
            this.game.getMap().setRoomTypes(1, this.game.getPlayer());
            mainWindow.setMainPanel(new MainPanel(this.game, WindowStates.GAME));
            mainWindow.add(mainWindow.getMainPanel());
            if(mainWindow.getMainPanel().getBottomPanel().getObservers().size() == 0)
                mainWindow.getMainPanel().getBottomPanel().registerObserver(this);
            if(mainWindow.getMainPanel().getTopPanel().getMapPanel().getObservers().size() == 0)
                mainWindow.getMainPanel().getTopPanel().getMapPanel().registerObserver(this);
            if(mainWindow.getMainPanel().getTopPanel().getGamePanel().getObservers().size() == 0)
                mainWindow.getMainPanel().getTopPanel().getGamePanel().registerObserver(this);
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
