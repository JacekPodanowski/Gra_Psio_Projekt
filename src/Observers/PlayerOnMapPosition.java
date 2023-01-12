package Observers;

import BackEnd.Game.Game;
import GUI.View.MainWindow;

public class PlayerOnMapPosition implements Observer{
    private MainWindow mainWindow;
    private Game game;
    public PlayerOnMapPosition(Game game, MainWindow mainWindow){
        if(game != null) {
            this.game = game;
            this.game.getObservers().add(this);
            this.game.setMainWindow(mainWindow);
        }
        this.mainWindow = mainWindow;
        this.mainWindow.getObservers().add(this);
    }

    @Override
    public void update(Game game) {
        this.game = game;
        if(this.game.getObservers().size() == 0) {
            this.game.getObservers().add(this);
            this.game.setMainWindow(this.mainWindow);
        }
        else {
            this.mainWindow = this.game.getMainWindow();
            print();
        }
    }

    @Override
    public void update(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        update(mainWindow.getGame());
    }

    public void print(){
        mainWindow.println(game.getText());
    }
}
