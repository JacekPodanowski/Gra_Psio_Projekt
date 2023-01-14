package GUI.Panels.ButtonPanels;

import BackEnd.Game.Game;
import GUI.View.MainWindow;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FightPanel extends JPanel implements Subject {

    private Game game;

    // cos tu powinno byc



    private ArrayList<Observer> observers = new ArrayList<>();


    public FightPanel(Game game){
        this.game=game;

        //Ustawiam wielkości
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));

        //Layout
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        //Dodaje komponenty
        this.add(skill_1(this));
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(skill_2(this));
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(skill_3(this));
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(skill_4(this));

    }

    private JButton skill_1(Component parent){
        JButton skill_1 = new JButton();
        skill_1.setMinimumSize(new Dimension(200, 50));
        skill_1.setPreferredSize(new Dimension(200, 50));
        skill_1.setMaximumSize(new Dimension(200, 50));
        skill_1.setLocation(MainWindow.centerLocation(parent,skill_1));
        skill_1.setText("Umiejętność 1");
        skill_1.setFont(new Font("ButtonFont", Font.BOLD, 20));
        skill_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game();
                notifyObservers();
            }});
        return skill_1;
    }

    private JButton skill_2(Component parent){
        JButton skill_2 = new JButton();
        skill_2.setMinimumSize(new Dimension(200, 50));
        skill_2.setPreferredSize(new Dimension(200, 50));
        skill_2.setMaximumSize(new Dimension(200, 50));
        skill_2.setLocation(MainWindow.centerLocation(parent,skill_2));
        skill_2.setText("Umiejętność 2");
        skill_2.setFont(new Font("ButtonFont", Font.BOLD, 20));
        skill_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game();
                notifyObservers();
            }});
        return skill_2;
    }

    private JButton skill_3(Component parent){
        JButton skill_3 = new JButton();
        skill_3.setMinimumSize(new Dimension(200, 50));
        skill_3.setPreferredSize(new Dimension(200, 50));
        skill_3.setMaximumSize(new Dimension(200, 50));
        skill_3.setLocation(MainWindow.centerLocation(parent,skill_3));
        skill_3.setText("Umiejętność 3");
        skill_3.setFont(new Font("ButtonFont", Font.BOLD, 20));
        skill_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game();
                notifyObservers();
            }});
        return skill_3;
    }

    private JButton skill_4(Component parent){
        JButton skill_4 = new JButton();
        skill_4.setMinimumSize(new Dimension(200, 50));
        skill_4.setPreferredSize(new Dimension(200, 50));
        skill_4.setMaximumSize(new Dimension(200, 50));
        skill_4.setLocation(MainWindow.centerLocation(parent,skill_4));
        skill_4.setText("Umiejętność 4");
        skill_4.setFont(new Font("ButtonFont", Font.BOLD, 20));
        skill_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game();
                notifyObservers();
            }});
        return skill_4;
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
