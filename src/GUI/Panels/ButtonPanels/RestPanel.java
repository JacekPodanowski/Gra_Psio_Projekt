package GUI.Panels.ButtonPanels;

import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class RestPanel extends JPanel implements Subject {

    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();

    public RestPanel(Game game){
        this.game = game;
        this.add(generateLabel());
    }

    public JPanel generateLabel(){
      JPanel panel = new JPanel();

      switch (game.generateRestEvent()){
        case 0 :
            panel.add(new JLabel("Wstajesz czując się nieco lepeij"));
            break;
        case 1 :
            panel.add(new JLabel("Wstajesz czując się znacznie lepeij"));
            break;
        case 2 :
            panel.add(new JLabel("Budzą cie 3 szybkie lecz bolesne ciosy kijem ale pokój wydaje się być pusty"));
            panel.add(new JLabel("|Zycie zmiejszone o 15 punktów|"));
            break;
        case 3 :
            panel.add(new JLabel("Wstajesz i ruszasz dalej"));
            break;
        case 4 :
            panel.add(new JLabel("Budzą się wypoczęty lecz nigdzie nie możesz znaleść swojej torby"));
            break;
        case 5 :
            panel.add(new JLabel("Budzisz się kompletnie nagi a po twoim sprzęcie ani śladu, ciekawe jako to sie stało ?"));
            break;
    }
    return panel;}

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(game);
    }

}
