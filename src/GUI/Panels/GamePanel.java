package GUI.Panels;

import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Subject {
    private ArrayList<Observer> observers = new ArrayList<>();
    public GamePanel(){
        this.setPreferredSize(new Dimension(450, 400));
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
        /*
        if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Entrance) {

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof EmptyRoom) {

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Exit) {

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Fight) {
            for (int i = 0; i < game.getPlayer().getAbilities().length; i++);

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Loot) {

        }
        return gamePanel;
         */
    }

    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }
}
