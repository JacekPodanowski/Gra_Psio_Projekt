package Observers;

import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import GUI.Panels.ButtonPanels.ProfessionChoosePanel;
import GUI.Panels.MainPanel;
import GUI.Panels.MapPanel;
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
        if (this.game == null) {
            this.game = game;
            initiate();
        } else if (this.game.isLocationChanged()) {
            this.game = game;
            refresh();
            this.game.setLocationChanged(false);
        }else if (this.game.isGameFinished()) {
            this.game = game;
            startMenu();
        } else if (this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.EXIT) {
            this.game = game;
            endGame();
        } else {
            this.game = game;
            mapRefresh();
        }
    }
    public void mapRefresh(){
        mainWindow.getMainPanel().getTopPanel().remove(mainWindow.getMainPanel().getTopPanel().getMapPanel());
        mainWindow.getMainPanel().getTopPanel().setMapPanel(new MapPanel(game));
        mainWindow.getMainPanel().getTopPanel().add(mainWindow.getMainPanel().getTopPanel().getMapPanel());
        mainWindow.getMainPanel().getTopPanel().getMapPanel().registerObserver(this);
        mainWindow.getMainPanel().getTopPanel().revalidate();
        mainWindow.getMainPanel().getTopPanel().repaint();
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

    public void endGame(){
        mainWindow.getMainPanel().removeAll();
        mainWindow.remove(mainWindow.getMainPanel());
        mainWindow.setMainPanel(new MainPanel(game, WindowStates.ENDGAME));
        mainWindow.add(mainWindow.getMainPanel());
        mainWindow.getMainPanel().getEndGamePanel().registerObserver(this);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public void startMenu(){
        mainWindow.getMainPanel().removeAll();
        mainWindow.remove(mainWindow.getMainPanel());
        mainWindow.setMainPanel(new MainPanel(game, WindowStates.STARTMENU));
        mainWindow.add(mainWindow.getMainPanel());
        mainWindow.revalidate();
        mainWindow.repaint();
    }

}
