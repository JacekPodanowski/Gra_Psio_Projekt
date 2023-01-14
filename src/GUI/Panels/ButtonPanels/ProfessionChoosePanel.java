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
        this.setLayout(new GridLayout(1, 3));
        initiateWarrior(game);
        initiateMage(game);
        initiateArcher(game);
        this.add(warrior);
        this.add(mage);
        this.add(archer);
    }
    private void initiateWarrior(Game game){
        warrior = new JButton();
        warrior.setText("Wojownik");
        warrior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Warrior());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                System.out.println(game.getPlayer().getProfession());
                notifyObservers();
            }
        });
    }
    private void initiateMage(Game game){
        mage = new JButton();
        mage.setText("Mag");
        mage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Mage());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                notifyObservers();
            }
        });
    }
    private void initiateArcher(Game game){
        archer = new JButton();
        archer.setText("Łucznik");
        archer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Archer());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                notifyObservers();
            }
        });
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
