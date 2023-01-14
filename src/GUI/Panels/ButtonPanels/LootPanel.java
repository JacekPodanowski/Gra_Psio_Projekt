package GUI.Panels.ButtonPanels;



import BackEnd.Game.Event.Loot;
import BackEnd.Game.Game;
import GUI.View.MainWindow;
import Observable.Subject;
import Observers.GUIRefresher;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LootPanel extends JPanel implements Subject {

    private Game game;

    private Loot loot;



    private ArrayList<Observer> observers = new ArrayList<>();


    public LootPanel(Game game){
        this.game=game;

        //Ustawiam wielkości
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));

        //Layout
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        //Dodaje komponenty
        this.add(bierzLootButton(this));
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(nieBierzLootButton(this));
    }

    private JButton bierzLootButton(Component parent){
        JButton bierzLootButton = new JButton();
        bierzLootButton.setMinimumSize(new Dimension(700, 50));
        bierzLootButton.setPreferredSize(new Dimension(700, 50));
        bierzLootButton.setMaximumSize(new Dimension(700, 50));
        bierzLootButton.setLocation(MainWindow.centerLocation(parent,bierzLootButton));
        bierzLootButton.setText("Weź przedmiot");
        bierzLootButton.setFont(new Font("ButtonFont", Font.BOLD, 30));
        bierzLootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game();
                notifyObservers();
            }});
        return bierzLootButton;
    }

    private JButton nieBierzLootButton(Component parent){
        JButton nieBierzLootButton = new JButton();
        nieBierzLootButton.setMinimumSize(new Dimension(700, 50));
        nieBierzLootButton.setPreferredSize(new Dimension(700, 50));
        nieBierzLootButton.setMaximumSize(new Dimension(700, 50));
        nieBierzLootButton.setLocation(MainWindow.centerLocation(parent,nieBierzLootButton));
        nieBierzLootButton.setText("Zostaw przedmiot");
        nieBierzLootButton.setFont(new Font("ButtonFont", Font.BOLD, 30));
        nieBierzLootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game();
                notifyObservers();
            }});
        return nieBierzLootButton;
    }

    @Override
    public void registerObserver(Observer observer) {observers.add(observer);}

    @Override
    public void removeObserver(Observer observer) {observers.remove(observer);}

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(this.getGame());
    }

    public Game getGame() {
        return game;
    }
}
