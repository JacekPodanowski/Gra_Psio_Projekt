package GUI.Panels.ButtonPanels;

import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LvlUpPanel extends JPanel implements Subject {
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    public LvlUpPanel(Game game){
        this.game = game;
        //Ustawiam wielkości
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(new JLabel("Wygrywasz! Ta walka wiele cię nauczyła, możesz rozwinąć jedną ze swoich statystyk."));


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));

        //Dodaje komponenty
        buttonsPanel.add(addStat('S'));
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonsPanel.add(addStat('I'));
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonsPanel.add(addStat('A'));
        this.add(buttonsPanel);
    }

    private JButton addStat(char stat){
        JButton button = new JButton();
        switch (stat){
            case 'A':
                button.setText("Dodaj zręczność");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.getPlayer().setAgility(game.getPlayer().getAgility()+5);
                        notifyObservers();
                    }
                });
                break;
            case 'I':
                button.setText("Dodaj inteligencję");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.getPlayer().setIntelligence(game.getPlayer().getIntelligence()+5);
                        notifyObservers();
                    }
                });
                break;
            case 'S':
                button.setText("Dodaj siłę");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.getPlayer().setStrength(game.getPlayer().getStrength()+5);
                        notifyObservers();
                    }
                });
                break;
        }
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
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(game);
    }
}
