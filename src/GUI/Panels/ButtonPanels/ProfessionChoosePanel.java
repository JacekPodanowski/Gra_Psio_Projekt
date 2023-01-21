package GUI.Panels.ButtonPanels;

import BackEnd.Chararcter.Profession.Archer;
import BackEnd.Chararcter.Profession.Mage;
import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfessionChoosePanel extends JPanel implements Subject {
    private JButton warrior;
    private JButton mage;
    private JButton archer;
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();


    public ProfessionChoosePanel(Game game){
        this.game = game;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(new Label("Aby rozpocząc grę, wybierz swoją profesję: ", Label.CENTER));
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        initiateWarrior(game);
        initiateMage(game);
        initiateArcher(game);
        buttonPanel.add(warrior);
        buttonPanel.add(mage);
        buttonPanel.add(archer);
        this.add(buttonPanel);
    }
    private void initiateWarrior(Game game){
        warrior = new JButton();
        warrior.setText("Wojownik");
        warrior.setBackground(Color.lightGray);
        warrior.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 50));
        warrior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Warrior());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                System.out.println(game.getPlayer().getProfession());
                game.setLocationChanged(true);
                notifyObservers();
                removeObserver(observers.get(0));
            }
        });
    }
    private void initiateMage(Game game){
        mage = new JButton();
        mage.setText("Mag");
        mage.setBackground(Color.lightGray);
        mage.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 50));
        mage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Mage());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                game.setLocationChanged(true);
                notifyObservers();
                removeObserver(observers.get(0));
            }
        });
    }
    private void initiateArcher(Game game){
        archer = new JButton();
        archer.setText("Łucznik");
        archer.setBackground(Color.lightGray);
        archer.setFont(new Font("Sans Serif", Font.CENTER_BASELINE, 50));
        archer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Archer());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                game.setLocationChanged(true);
                notifyObservers();
                removeObserver(observers.get(0));
            }
        });
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
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
            observers.get(i).update(this.getGame());
    }
}
