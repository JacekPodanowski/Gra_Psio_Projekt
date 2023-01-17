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

    private JButton bierzLootButton;
    private JButton nieBierzLootButton;

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

        bierzLootButton = bierzLootButton(this);
        this.add(bierzLootButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        nieBierzLootButton = nieBierzLootButton(this);
        this.add(nieBierzLootButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private JButton bierzLootButton(Component parent){
        JButton bierzLootButton = new JButton();
        bierzLootButton.setMinimumSize(new Dimension(300, 50));
        bierzLootButton.setPreferredSize(new Dimension(300, 50));
        bierzLootButton.setMaximumSize(new Dimension(300, 50));
        bierzLootButton.setLocation(MainWindow.centerLocation(parent,bierzLootButton));
        bierzLootButton.setText("Weź przedmiot");
        bierzLootButton.setFont(new Font("ButtonFont", Font.BOLD, 30));
        
        bierzLootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLootButtonDisabled();
                game.setUserWantToAddItem(true);
                notifyObservers();
            }});
        return bierzLootButton;
    }

    private JButton nieBierzLootButton(Component parent){
        JButton nieBierzLootButton = new JButton();

        nieBierzLootButton.setMinimumSize(new Dimension(300, 50));
        nieBierzLootButton.setPreferredSize(new Dimension(300, 50));
        nieBierzLootButton.setMaximumSize(new Dimension(300, 50));

        nieBierzLootButton.setLocation(MainWindow.centerLocation(parent,nieBierzLootButton));
        nieBierzLootButton.setText("Zostaw przedmiot");
        nieBierzLootButton.setFont(new Font("ButtonFont", Font.BOLD, 30));
        nieBierzLootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLootButtonDisabled();
                game.setUserWantToAddItem(false);
                notifyObservers();
            }});
        return nieBierzLootButton;
    }

    public void setGame(Game game) {
        this.game = game;
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

    public void setLootButtonDisabled(){

        bierzLootButton.setEnabled(false);
        nieBierzLootButton.setEnabled(false);
    }

    public void setLootButtonActive(){

        bierzLootButton.setEnabled(true);
        nieBierzLootButton.setEnabled(true);
    }
}
