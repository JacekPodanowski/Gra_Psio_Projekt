package Observers;

import BackEnd.Game.Game;
import BackEnd.Game.Room;
import GUI.Panels.ButtonPanels.ProfessionChoosePanel;
import GUI.Panels.MainPanel;
import GUI.Panels.MapPanel;
import GUI.Panels.TopPanel;
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
        this.game.getMap().setRoomTypes(1, this.game.getPlayer());
        mainWindow.setMainPanel(new MainPanel(this.game, WindowStates.GAME));
        mainWindow.add(mainWindow.getMainPanel());

        //Observers register
        if(mainWindow.getMainPanel().getBottomPanel().getObservers().size() == 0)
            mainWindow.getMainPanel().getBottomPanel().registerObserver(this);
        if(mainWindow.getMainPanel().getTopPanel().getMapPanel().getObservers().size() == 0)
            mainWindow.getMainPanel().getTopPanel().getMapPanel().registerObserver(this);
        if(mainWindow.getMainPanel().getTopPanel().getGamePanel().getObservers().size() == 0)
            mainWindow.getMainPanel().getTopPanel().getGamePanel().registerObserver(this);

        mainWindow.getMainPanel().revalidate();
        mainWindow.getMainPanel().repaint();
    }
    @Override
    public void update(Game game) {
        if(this.game == null) {
            this.game = game;
            initiate();
        } else if (this.game.isLocationChanged()) {
            this.game = game;
            refresh();
            this.game.setLocationChanged(false);
        } else {
            this.game = game;
            mapRefresh();
        }
    }
    public void mapRefresh(){
        mainWindow.getMainPanel().getTopPanel().removeAll();
        mainWindow.getMainPanel().setTopPanel(new TopPanel(game));
        mainWindow.getMainPanel().add(mainWindow.getMainPanel().getTopPanel());
        mainWindow.getMainPanel().revalidate();
        mainWindow.getMainPanel().repaint();
    }
    public void initiate(){
        mainWindow.getMainPanel().removeAll();
        mainWindow.getMainPanel().getStartGamePanel().removeObserver(this);
        mainWindow.getMainPanel().setProfessionChoosePanel(new ProfessionChoosePanel(game));
        mainWindow.getMainPanel().add(mainWindow.getMainPanel().getProfessionChoosePanel());
        mainWindow.getMainPanel().getProfessionChoosePanel().registerObserver(this);
        mainWindow.getMainPanel().revalidate();
        mainWindow.getMainPanel().repaint();
    }
}
