package GUI.Panels.ButtonPanels;



import BackEnd.Game.Event.Loot;
import BackEnd.Game.Game;
import GUI.View.MainWindow;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
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
        this.loot = (Loot) game.getMap().getPlayerLocation(game.getPlayer()).getEvent();

        //Ustawiam wielkości
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));

        //Layout
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        //Dodaje komponenty

        bierzLootButton = bierzLootButton(buttonPanel);
        buttonPanel.add(bierzLootButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        nieBierzLootButton = nieBierzLootButton(buttonPanel);
        buttonPanel.add(nieBierzLootButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        this.add(Box.createRigidArea(new Dimension(0, 100)));

        this.add(setTitle());
        this.add(Box.createRigidArea(new Dimension(0, 25)));
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        buttonPanel.setBorder(blackLine);
        this.add(buttonPanel);
    }

    public JLabel setTitle(){
        JLabel entranceText = new JLabel("Dotarłeś do dziwnego pustego pokoju z przedmiotem " +loot.getLootTab().get(0).toString()+"\n"+" Zdecyduj czy jest Ci potrzebny");
        return entranceText;
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
