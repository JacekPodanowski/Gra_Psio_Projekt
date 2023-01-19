package GUI.Panels.ButtonPanels;

import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmptyRoomPanel extends JPanel implements Subject {
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    public EmptyRoomPanel(Game game){
        this.game = game;
        Dimension d = new Dimension(900, 300);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);
        this.add(restButton());
        this.add(useItemButton());
        this.add(statsDisplayButton());
    }
    public JButton restButton(){
        JButton restButton = setBasicButton("Odpocznij");
        if(game.getMap().getTabOfRoom()[game.getPlayer().getLocation_X()][game.getPlayer().getLocation_Y()].isRested()){
            restButton.setEnabled(false);
        }
        restButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.REST);
                notifyObservers();
            }
        });
        return restButton;
    }
    public JButton useItemButton(){
        JButton useItemButton = setBasicButton("Użyj przedmiotu");
        useItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.INVENTORY);
                notifyObservers();
            }
        });
        return useItemButton;
    }

    public JButton statsDisplayButton(){
        JButton useItemButton = setBasicButton("Wyświetl statystyki");
        useItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.STATS);
                notifyObservers();
            }
        });
        return useItemButton;
    }

    private JButton setBasicButton(String title){
        JButton button = new JButton(title);
        button.setFont(new Font("czcionka", Font.BOLD|Font.ITALIC, 10));
        Dimension d = new Dimension(100, 30);
        button.setMinimumSize(d);
        button.setMaximumSize(d);
        button.setPreferredSize(d);
        return button;
    }

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
        for(int i = 0 ; i < observers.size(); i++)
            observers.get(i).update(game);
    }
}
